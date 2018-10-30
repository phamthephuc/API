package com.service;

import com.dto.LocationDTO;
import com.dto.LocationProfileDTO;
import com.dto.TypeResponseDTO;
import com.entity.*;
import com.model.LocationRequest;
import com.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class LocationService {
    @Autowired
    LocationRepository locationRepository;

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    ContactRepository contactRepository;

    @Autowired
    ContentRepository contentRepository;

    @Autowired
    DurationRepository durationRepository;

    @Autowired
    PictureRepository pictureRepository;

    @Autowired
    PlaceCategoryRepository placeCategoryRepository;

    @Autowired
    StatusRepository statusRepository;

    @Autowired
    UsersRepository usersRepository;



    public List<LocationProfileDTO> findAllLocationRecommended(Long idUserRecommended, Long idUserRelative) {
        List<Location> listLocation = locationRepository.getLocationRecommend(idUserRecommended, idUserRelative);
        return  getAllLocationProfileDTOWithLocation(listLocation);
    }

    public List<LocationProfileDTO> findAllLocationRecommendedWithHeightScorce() {
        List<Location> listLocation = locationRepository.getLocationRecommendWithHeightScore();
        return  getAllLocationProfileDTOWithLocation(listLocation);
    }

    public List<LocationDTO> getAllLocationDTOWithLocation(List<Location> listLocation) {
        List<LocationDTO> listLocationDTO = new ArrayList<>();
        for (Location location : listLocation){
            LocationDTO locationDTO = new LocationDTO();
            locationDTO.setId(location.getId());
            locationDTO.setName(location.getName());
            locationDTO.setIntroduction(location.getIntroduction());
            locationDTO.setCreatedDate(location.getCreatedDate());

//            PlaceCategory placeCategory;
            PlaceCategory placeCategory = placeCategoryRepository.findById(location.getIdPlaceCategory()).orElse(new PlaceCategory());
//            Content content;
            Content content = contentRepository.findById(location.getIdContent()).orElse(new Content());
//            Status  status;
            Status status = statusRepository.findById(location.getIdStatus()).orElse(new Status());
//            Address address;
            Address address = addressRepository.findById(location.getIdAddress()).orElse(new Address());
//            Contact contact;
            Contact contact = contactRepository.findById(location.getIdContact()).orElse(new Contact());
//            Users users;
            Users users = usersRepository.findById(location.getIdUser()).orElse(new Users());
//            Duration duration;
            Duration duration = durationRepository.findById(location.getIdDuration()).orElse(new Duration());

//            List<Picture> pictureList;
            ArrayList<Picture> pictures = pictureRepository.findByIdLocation(location.getId());

            locationDTO.setPlaceCategory(placeCategory);
            locationDTO.setContent(content);
            locationDTO.setContact(contact);
            locationDTO.setStatus(status);
            locationDTO.setAddress(address);
            locationDTO.setDuration(duration);
            locationDTO.setPictureList(pictures);
            listLocationDTO.add(locationDTO);
        }
        return listLocationDTO;
    }
    public  List<LocationDTO> findAllLocationByCategoryId(Long id){
        List<Location> listLocation = locationRepository.findByIdPlaceCategory(id);
        return  getAllLocationDTOWithLocation(listLocation);
    }

    public List<LocationProfileDTO> getAllLocationProfileDTOWithLocation(List<Location> listLocation) {
        List<LocationProfileDTO> listLocationDTO = new ArrayList<>();
        for (Location location : listLocation){
            LocationProfileDTO locationProfileDTO = new LocationProfileDTO();
            locationProfileDTO.setId(location.getId());
            locationProfileDTO.setName(location.getName());
            locationProfileDTO.setIntroduction(location.getIntroduction());
            locationProfileDTO.setCreatedDate(location.getCreatedDate());

//            PlaceCategory placeCategory;
            PlaceCategory placeCategory = placeCategoryRepository.findById(location.getIdPlaceCategory()).orElse(new PlaceCategory());
            locationProfileDTO.setPlaceCategory(placeCategory.getName());
//            Content content;
            Content content = contentRepository.findById(location.getIdContent()).orElse(new Content());
            locationProfileDTO.setContent(content.getDetail());
//            Status  status;
            Status status = statusRepository.findById(location.getIdStatus()).orElse(new Status());
            locationProfileDTO.setStatus(status.getName());
//            Address address;
            Address address = addressRepository.findById(location.getIdAddress()).orElse(new Address());
            locationProfileDTO.setAddress(address.getName());
//            Contact contact;
            Contact contact = contactRepository.findById(location.getIdContact()).orElse(new Contact());
            locationProfileDTO.setEmail(contact.getEmail());
            locationProfileDTO.setPhone(contact.getPhone());
//            Users users;
            Users users = usersRepository.findById(location.getIdUser()).orElse(new Users());
            locationProfileDTO.setUsersname(users.getUsername());
//            Duration duration;
            Duration duration = durationRepository.findById(location.getIdDuration()).orElse(new Duration());
            locationProfileDTO.setDuration(duration.getTime());

//            List<Picture> pictureList;
            ArrayList<Picture> pictures = pictureRepository.findByIdLocation(location.getId());
            locationProfileDTO.setPictureList(pictures);

            listLocationDTO.add(locationProfileDTO);
        }
        return listLocationDTO;
    }

    public LocationProfileDTO getLocationProfileDTOWithLocation(Location location) {

            LocationProfileDTO locationProfileDTO = new LocationProfileDTO();
            locationProfileDTO.setId(location.getId());
            locationProfileDTO.setName(location.getName());
            locationProfileDTO.setIntroduction(location.getIntroduction());
            locationProfileDTO.setCreatedDate(location.getCreatedDate());

//            PlaceCategory placeCategory;
            PlaceCategory placeCategory = placeCategoryRepository.findById(location.getIdPlaceCategory()).orElse(new PlaceCategory());
            locationProfileDTO.setPlaceCategory(placeCategory.getName());
//            Content content;
            Content content = contentRepository.findById(location.getIdContent()).orElse(new Content());
            locationProfileDTO.setContent(content.getDetail());
//            Status  status;
            Status status = statusRepository.findById(location.getIdStatus()).orElse(new Status());
            locationProfileDTO.setStatus(status.getName());
//            Address address;
            Address address = addressRepository.findById(location.getIdAddress()).orElse(new Address());
            locationProfileDTO.setAddress(address.getName());
//            Contact contact;
            Contact contact = contactRepository.findById(location.getIdContact()).orElse(new Contact());
            locationProfileDTO.setEmail(contact.getEmail());
            locationProfileDTO.setPhone(contact.getPhone());
//            Users users;
            Users users = usersRepository.findById(location.getIdUser()).orElse(new Users());
            locationProfileDTO.setUsersname(users.getUsername());
//            Duration duration;
            Duration duration = durationRepository.findById(location.getIdDuration()).orElse(new Duration());
            locationProfileDTO.setDuration(duration.getTime());

//            List<Picture> pictureList;
            ArrayList<Picture> pictureOfLocation = pictureRepository.findByIdLocation(location.getId());
            locationProfileDTO.setPictureList(pictureOfLocation);

        return locationProfileDTO;
    }

    public  List<LocationProfileDTO> findAllLocationProfileByCategoryId(Long id){
        List<Location> listLocation = locationRepository.findByIdPlaceCategory(id);
        return  getAllLocationProfileDTOWithLocation(listLocation);
    }

    public  List<LocationProfileDTO> findTop10LocationProfileByCategoryId(Long id){
        List<Location> listLocation = locationRepository.findTop10ByIdPlaceCategory(id);
        return  getAllLocationProfileDTOWithLocation(listLocation);
    }

    public  List<Location> findAllLocationOfUserEvaluation(Long id){
        return  locationRepository.getAllLocationByUser(id);
    }

    public List<Location> findAllLocation(){
        List<Location> listLocations = (List<Location>) locationRepository.findAll();
        return  listLocations;
    }

    public LocationProfileDTO findById(Long id){
        Location locationSelected =  locationRepository.findById(id).orElse(new Location());
        return  getLocationProfileDTOWithLocation(locationSelected);
    }

    public LocationProfileDTO findLastestLocationByIdType(Long idType){
        Location locationSelected =  locationRepository.findLastestLocationByIdType(idType);
        return  getLocationProfileDTOWithLocation(locationSelected);
    }

    public  void createLocation(Location location ){
        locationRepository.save(location);
    }

    public void createNewLocation(LocationRequest locationRequest){
        Location location = new Location();
        location.setName(locationRequest.getName());
        location.setIntroduction(locationRequest.getIntroduction());
        location.setCreatedDate(new Date());
        location.setIdPlaceCategory(locationRequest.getIdPlaceCategory());
        location.setIdStatus(locationRequest.getIdStatus());

        Content content = new Content();
        content.setDetail(locationRequest.getContent());
        contentRepository.save(content);
        Content content1 = contentRepository.findLastestContent();
        System.out.print(content1.getId());
        location.setIdContent(contentRepository.findLastestContent().getId());

        Contact contact = new Contact();
        contact.setEmail(locationRequest.getEmail());
        contact.setPhone(locationRequest.getPhone());
        contactRepository.save(contact);
        location.setIdContact(contactRepository.findLastestContact().getId());

        Address address = new Address();
        address.setName(locationRequest.getAddress());
        address.setLink(locationRequest.getAddress());
        addressRepository.save(address);
        location.setIdAddress(addressRepository.findLastestAddress().getId());

        location.setIdUser((long) 1);
        location.setIdDuration(locationRequest.getIdDuration());


        locationRepository.save(location);

    }

    public Long getIdLocationLastest(){
        return locationRepository.findLastestLocation().getId();
    }

    public LocationProfileDTO getLocationLastest(){
        return getLocationProfileDTOWithLocation(locationRepository.findLastestLocation());
    }

    public void updateLocation(Location location)
    {
        locationRepository.save(location);
    }

    public void editLocation(LocationRequest locationRequest, Long idLocation){
        Location location = new Location();
        location.setName(locationRequest.getName());
        location.setIntroduction(locationRequest.getIntroduction());
        location.setCreatedDate(new Date());
        location.setIdPlaceCategory(locationRequest.getIdPlaceCategory());
        location.setIdStatus(locationRequest.getIdStatus());

        Content content = new Content();
        content.setDetail(locationRequest.getContent());
        contentRepository.save(content);
        Content content1 = contentRepository.findLastestContent();
        System.out.print(content1.getId());
        location.setIdContent(contentRepository.findLastestContent().getId());

        Contact contact = new Contact();
        contact.setEmail(locationRequest.getEmail());
        contact.setPhone(locationRequest.getPhone());
        contactRepository.save(contact);
        location.setIdContact(contactRepository.findLastestContact().getId());

        Address address = new Address();
        address.setName(locationRequest.getAddress());
        address.setLink(locationRequest.getAddress());
        addressRepository.save(address);
        location.setIdAddress(addressRepository.findLastestAddress().getId());

        location.setIdUser((long) 1);
        location.setIdDuration(locationRequest.getIdDuration());
        location.setId(idLocation);
        Location locationsaved = locationRepository.save(location);
    }

    public void deleteLocation(Long id){
        locationRepository.deleteById(id);
    }

//    public TypeResponseDTO getAllLocationByPlaceTypeId(Long id){
//
//        TypeResponseDTO typeResponseDTO = new TypeResponseDTO();
//        typeResponseDTO.setId(id);
//        typeResponseDTO.setListCategoryResponse(new ArrayList<>());
//
//
//       return new TypeResponseDTO();
//    }
}
