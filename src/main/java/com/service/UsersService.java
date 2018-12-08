package com.service;


import com.anotherAPI.ResponseOtherApi;
import com.config.JwtTokenProvider;
import com.dto.*;
import com.entity.InforUsers;
import com.entity.Location;
import com.entity.Traveler;
import com.entity.Users;
import com.exception.CustomException;
import com.repository.InforUsersRepository;
import com.repository.RoleRespository;
import com.repository.TravelerResponsitory;
import com.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class UsersService {
    @Autowired
    UsersRepository usersRepository;

    public static final int PAGE_SIZE = 5;

    @Autowired
    TravelerResponsitory travelerResponsitory;

    @Autowired
    InforUsersRepository inforUsersRepository;

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @Autowired
    RoleService roleService;

    @Autowired
    RoleRespository roleRespository;

    @Autowired
    PasswordEncoder passwordEncoder;




    public List<Users> listUserRelative(Long idUser) {
        return usersRepository.findAllUserSameLocationWithOnes(idUser);
    }

    public UsersProfileResponse getUsersProfile(HttpServletRequest request){
        Traveler travelerCurrent = travelerResponsitory.findByUsername(jwtTokenProvider.getUsername(jwtTokenProvider.resolveToken(request)));
        InforUsers inforUsers = inforUsersRepository.getInforUsersByIdUser(travelerCurrent.getId());
        UsersProfileResponse usersProfileResponse = new UsersProfileResponse();
        usersProfileResponse.setUsername(travelerCurrent.getUsername());
        usersProfileResponse.setAddress(inforUsers.getAddress());
        usersProfileResponse.setGender(inforUsers.getGender());
        usersProfileResponse.setPhone(inforUsers.getPhone());
        usersProfileResponse.setFullname(inforUsers.getFullname());
        return usersProfileResponse;

    }

    public Users findUserFromToken(HttpServletRequest request) {
        return usersRepository.findByUsername(jwtTokenProvider.getUsername(jwtTokenProvider.resolveToken(request)));
    }


    public PageUsersDTO findAllUsersPagination(int currentPage) {
        PageRequest pageRequest = new PageRequest(currentPage - 1, PAGE_SIZE, Sort.Direction.DESC,"id");
        Page<Users> pageLocation = usersRepository.findAll(pageRequest);
        return getPageUsersDTOFromPageUsers(pageLocation);
    }


    public PageUsersDTO getPageUsersDTOFromPageUsers(Page<Users> pageUser) {
        List<Users> listUsers = pageUser.getContent();
        List<UsersProfileDTO> listUserProfileDTO  = getAllUsersProfileDTOWithUsers(listUsers);

        PageUsersDTO pageUsersDTO = new PageUsersDTO();
        pageUsersDTO.setCurrentPage(pageUser.getNumber() + 1);
        pageUsersDTO.setSumPage(pageUser.getTotalPages());
        pageUsersDTO.setUsersProfileDTOList(listUserProfileDTO);
        return pageUsersDTO;
    }

    private List<UsersProfileDTO> getAllUsersProfileDTOWithUsers(List<Users> listUsers) {
        List<UsersProfileDTO> listUsersProfileDTOS = new ArrayList<>();
        for (Users users: listUsers){

            UsersProfileDTO usersProfileDTO = new UsersProfileDTO();
            usersProfileDTO.setIdUser(users.getId());
            usersProfileDTO.setUsername(users.getUsername());
            usersProfileDTO.setStatus(users.getStatus());
            Long roleId = users.getRoleId();
            usersProfileDTO.setRoleName(roleService.findById(roleId).getName());
            InforUsers inforUsers= inforUsersRepository.getInforUsersByIdUser(users.getId());

            if (inforUsers != null){
                usersProfileDTO.setAddress(inforUsers.getAddress());
                usersProfileDTO.setFullname(inforUsers.getFullname());
                usersProfileDTO.setGender(inforUsers.getGender());
                usersProfileDTO.setPhone(inforUsers.getPhone());
            }
           if(usersProfileDTO.getRoleName()!="admin"){
               listUsersProfileDTOS.add(usersProfileDTO);
           }
        }
        return listUsersProfileDTOS;
    }

    public Boolean updateStatusOfUser(Long idUser)  {
        Users users = usersRepository.findById(idUser).orElse(new Users());
        if (users.getId() != null){
            if (users.getStatus()!=null){
                if (users.getStatus() == 0){
                    users.setStatus(1L);
                    usersRepository.save(users);
                    return true;
                } else {
                    users.setStatus(0L);
                    usersRepository.save(users);
                    return false;
                }
            } else {
                throw  new CustomException(" Exception : Null Value Status", 500);
            }

        } else {
            throw new NullPointerException();
        }

    }

    public InforUsers editUserProfile(HttpServletRequest request, InforUsers inforUsers) {
        Traveler travelerCurrent = travelerResponsitory.findByUsername(jwtTokenProvider.getUsername(jwtTokenProvider.resolveToken(request)));
        InforUsers inforUsersOld = inforUsersRepository.getInforUsersByIdUser(travelerCurrent.getId());
        if (inforUsersOld != null){
            inforUsersOld.setFullname(inforUsers.getFullname());
            inforUsersOld.setPhone(inforUsers.getPhone());
            inforUsersOld.setGender(inforUsers.getGender());
            inforUsersOld.setAddress(inforUsers.getAddress());
            return  inforUsersRepository.save(inforUsersOld);
        } else throw new NullPointerException();
    }

    public UsersProfileDTO addMod(UserRegisterDTO userRegisterDTO) {
        Users mod = usersRepository.findByUsername(userRegisterDTO.getUsername());
        if (mod == null){

            Users newMod= new Users();
            newMod.setPassword(passwordEncoder.encode(userRegisterDTO.getPassword()));
            newMod.setUsername(userRegisterDTO.getUsername());
            newMod.setStatus(1L);
            newMod.setRoleId(roleRespository.findByName("mod").getId());
            Users addedMod = usersRepository.save(newMod);

            InforUsers inforUsersNew = new InforUsers();
            inforUsersNew.setAddress(userRegisterDTO.getAddress());
            inforUsersNew.setGender(userRegisterDTO.getGender());
            inforUsersNew.setPhone(userRegisterDTO.getPhone());
            inforUsersNew.setIdUser(addedMod.getId());
            inforUsersNew.setFullname(userRegisterDTO.getFullname());
            InforUsers inforUsersAdded = inforUsersRepository.save(inforUsersNew);

            new Thread(new Runnable() {
                @Override
                public void run() {
                    final String uri = ResponseOtherApi.urlRecommendServer + "/addUser";
                    RestTemplate restTemplate = new RestTemplate();
                    HttpHeaders headers = new HttpHeaders();
                    LinkedMultiValueMap<String, Object> params = new LinkedMultiValueMap<>();
                    params.add("id_user", addedMod.getId());

                    headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
                    headers.add("passcode",RecommendService.passcode);

                    HttpEntity<LinkedMultiValueMap<String, Object>> requestEntity =
                            new HttpEntity<>(params, headers);

                    ResponseEntity<ResponseOtherApi> responseEntity = restTemplate.exchange(uri, HttpMethod.POST, requestEntity, ResponseOtherApi.class);
                }
            }).start();

            return getUserProfileDTOfromUser(addedMod);

        }

        else {
            throw new CustomException("Username existed!", 500);
        }
    }

    public UsersProfileDTO getUserProfileDTOfromUser(Users users){

        UsersProfileDTO usersProfileDTO = new UsersProfileDTO();
        usersProfileDTO.setIdUser(users.getId());
        usersProfileDTO.setUsername(users.getUsername());
        usersProfileDTO.setStatus(users.getStatus());
        Long roleId = users.getRoleId();
        usersProfileDTO.setRoleName(roleService.findById(roleId).getName());
        InforUsers inforUsers= inforUsersRepository.getInforUsersByIdUser(users.getId());

        if (inforUsers != null){
            usersProfileDTO.setAddress(inforUsers.getAddress());
            usersProfileDTO.setFullname(inforUsers.getFullname());
            usersProfileDTO.setGender(inforUsers.getGender());
            usersProfileDTO.setPhone(inforUsers.getPhone());
        }
        return usersProfileDTO;
    }
}