package com.service;

import com.dto.LocationDTO;
import com.dto.LocationProfileDTO;
import com.dto.PageLocationDTO;
import com.dto.TypeResponseDTO;
import com.dto.*;
import com.entity.*;
import com.model.LocationRequest;
import com.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class LocationService {

    private static final int PAGE_SIZE = 10;

    @Autowired
    EvaluationService evaluationService;

    @Autowired
    EvaluationRepository evaluationRepository;

    @Autowired
    FavoriteRepository favoriteRepository;

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


    public LocationRequest getLocationRequestById(Long idLocation){
        Location location = locationRepository.findById(idLocation).orElse(new Location());
        return getLocationRequestFromLocation(location);
    }

    public List<LocationProfileForTypeDTO> getLocationProfileForOneCategory(Long id) {
        List<Location> listLocation = locationRepository.findByIdPlaceCategory(id);
        return getAllLocationProfileForTypeDTOWithLocation(listLocation);
    }

    private LocationRequest getLocationRequestFromLocation(Location location) {

        LocationRequest locationRequest= new LocationRequest();
        locationRequest.setName(location.getName());
        locationRequest.setIntroduction(location.getIntroduction());
//            PlaceCategory placeCategory;
        PlaceCategory placeCategory = placeCategoryRepository.findById(location.getIdPlaceCategory()).orElse(new PlaceCategory());
        locationRequest.setIdPlaceCategory(location.getIdPlaceCategory());
        locationRequest.setIdPlaceType(placeCategory.getIdPlaceType());
//            Content content;
        Content content = contentRepository.findById(location.getIdContent()).orElse(new Content());
        locationRequest.setContent(content.getDetail());
//            Status  status;
        Status status = statusRepository.findById(location.getIdStatus()).orElse(new Status());
        locationRequest.setIdStatus(location.getIdStatus());
//            Address address;
        Address address = addressRepository.findById(location.getIdAddress()).orElse(new Address());
        locationRequest.setNameAddress(address.getName());
        locationRequest.setLatitudeAddress(address.getLatitude());
        locationRequest.setLongitudeAddress(address.getLongitude());
//            Contact contact;
        Contact contact = contactRepository.findById(location.getIdContact()).orElse(new Contact());
        locationRequest.setEmail(contact.getEmail());
        locationRequest.setPhone(contact.getPhone());
//            Duration duration;
        locationRequest.setIdDuration(location.getIdDuration());
        return locationRequest;

//

    }

    public PageLocationDTO findAllLocationPagination(int currentPage) {
        PageRequest pageRequest = new PageRequest(currentPage - 1, PAGE_SIZE, Sort.Direction.DESC,"id");
        Page<Location> pageLocation = locationRepository.findAll(pageRequest);
        return getPageLocationDTOFromPageLocation(pageLocation);
    }

    public PageLocationDTO getPageLocationDTOFromPageLocationDTO(Page<Location> pageLocation) {
        List<Location> listLocation = pageLocation.getContent();
        List<LocationProfileDTO> listLocationProfile = getAllLocationProfileDTOWithLocation(listLocation);
        PageLocationDTO pageLocationDTO = new PageLocationDTO();
        pageLocationDTO.setCurrentPage(pageLocation.getNumber() + 1);
        pageLocationDTO.setSumPage(pageLocation.getTotalPages());
        pageLocationDTO.setListLocationProfieDTO(listLocationProfile);
        return pageLocationDTO;
    }



    public List<LocationProfileForTypeDTO> findAllLocationRecommended(Long idUserRecommended, Long idUserRelative) {

        List<Location> listLocation = locationRepository.getLocationRecommend(idUserRecommended, idUserRelative);
        return  getAllLocationProfileForTypeDTOWithLocation(listLocation);
    }

    public List<LocationProfileForTypeDTO> findTop10ByRating() {
        List<Location> listLocation = locationRepository.getTop10ByRating();
        return getAllLocationProfileForTypeDTOWithLocation(listLocation);
    }

    public List<LocationProfileForTypeDTO> findAllLocationRecommendedWithHeightScorce() {
        List<Location> listLocation = locationRepository.getLocationRecommendWithHeightScore();
        return  getAllLocationProfileForTypeDTOWithLocation(listLocation);
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
            locationProfileDTO.setLatitude(address.getLatitude());
            locationProfileDTO.setLongtude(address.getLongitude());
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
            locationProfileDTO.setLongtude(address.getLongitude());
            locationProfileDTO.setLatitude(address.getLatitude());
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

//    public  List<LocationProfileDTO> findTop10LocationProfileByCategoryId(Long id){
//        List<Location> listLocation = locationRepository.findTop10ByIdPlaceCategory(id);
//        return  getAllLocationProfileDTOWithLocation(listLocation);
//    }

    public  List<LocationProfileForTypeDTO> findTop10LocationProfileByCategoryId(Long id){
        List<Location> listLocation = locationRepository.findTop10ByIdPlaceCategory(id);
        return  getAllLocationProfileForTypeDTOWithLocation(listLocation);
    }

    public List<LocationProfileForTypeDTO> getAllLocationProfileForTypeDTOWithLocation(List<Location> listLocation) {
        List<LocationProfileForTypeDTO> listLocationDTO = new ArrayList<LocationProfileForTypeDTO>();
        for (Location location : listLocation){
            LocationProfileForTypeDTO locationProfileDTO = getLocationProfileForTypeDTOWithLocation(location);
            listLocationDTO.add(locationProfileDTO);
        }
        return listLocationDTO;
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

//    public LocationProfileDTO findLastestLocationByIdType(Long idType){
//        Location locationSelected =  locationRepository.findLastestLocationByIdType(idType);
//        return  getLocationProfileDTOWithLocation(locationSelected);
//    }


    public LocationProfileForTypeDTO findLastestLocationByIdType(Long idType){
        Location locationSelected =  locationRepository.findLastestLocationByIdType(idType);
        return  getLocationProfileForTypeDTOWithLocation(locationSelected);
    }

    public DetailLocationDTO findDetailLocationById(Long idLocation, Long idUser) {
        Location location = locationRepository.findById(idLocation).orElse(new Location());
        return getDetailLocationDTOFromLocation(location, idUser);
    }

    public DetailLocationDTO getDetailLocationDTOFromLocation(Location location, Long idUser) {
        DetailLocationDTO detailLocationDTO = new DetailLocationDTO();
        detailLocationDTO.setId(location.getId());
        detailLocationDTO.setName(location.getName());
        detailLocationDTO.setIntroduction(location.getIntroduction());

//            Content content;
        Content content = contentRepository.findById(location.getIdContent()).orElse(new Content());
        detailLocationDTO.setContent(content.getDetail());

//            Address address;
        Address address = addressRepository.findById(location.getIdAddress()).orElse(new Address());
        detailLocationDTO.setAddress(address.getName());
        detailLocationDTO.setLatitude(address.getLatitude());
        detailLocationDTO.setLongitude(address.getLongitude());
//            Contact contact;
        Contact contact = contactRepository.findById(location.getIdContact()).orElse(new Contact());
        detailLocationDTO.setEmail(contact.getEmail());
        detailLocationDTO.setPhone(contact.getPhone());

//            List<Picture> pictureList;
        ArrayList<Picture> pictureOfLocation = pictureRepository.findByIdLocation(location.getId());
        detailLocationDTO.setPictureList(pictureOfLocation);


        long numRating = evaluationRepository.countAllByIdLocation(location.getId());
        detailLocationDTO.setNumRating(numRating);
        if(numRating == 0) {
            detailLocationDTO.setSumRating(new BigDecimal(0));
        } else {
            detailLocationDTO.setSumRating(evaluationRepository.sumAllByIdLocation(location.getId()).get());
        }

        Favorite favorite = favoriteRepository.findByIdUserAndIdLocation(idUser, location.getId());
        if(favorite == null) {
            detailLocationDTO.setFavorite(false);
        } else {
            detailLocationDTO.setFavorite(true);
        }

        detailLocationDTO.setEvaluationPaginationDTO(evaluationService.findAppReviewDTOPagination(location.getId(),1));

        return detailLocationDTO;
    }

    public LocationProfileForTypeDTO getLocationProfileForTypeDTOWithLocation(Location location) {
        LocationProfileForTypeDTO locationProfileDTO = new LocationProfileForTypeDTO();
        locationProfileDTO.setId(location.getId());
        locationProfileDTO.setName(location.getName());

        try {
            locationProfileDTO.setSumRating(evaluationRepository.sumAllByIdLocation(location.getId()).get());
            locationProfileDTO.setNumRating(evaluationRepository.countAllByIdLocation(location.getId()));
        } catch (Exception e) {
//            System.out.println(location.getId());
//            e.printStackTrace();
            locationProfileDTO.setNumRating(0);
            locationProfileDTO.setSumRating(new BigDecimal(0));
        }

        ArrayList<Picture> pictureOfLocation = pictureRepository.findByIdLocation(location.getId());
        locationProfileDTO.setPictureList(pictureOfLocation);
        return locationProfileDTO;
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
        address.setName(locationRequest.getNameAddress());
        address.setLink(locationRequest.getLatitudeAddress() + "|" + locationRequest.getLongitudeAddress());
        address.setLatitude(locationRequest.getLatitudeAddress());
        address.setLongitude(locationRequest.getLongitudeAddress());
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

    public LocationProfileDTO editLocation(LocationRequest locationRequest, Long idLocation){
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
        address.setName(locationRequest.getNameAddress());
        address.setLink(locationRequest.getLatitudeAddress() + "|" + locationRequest.getLongitudeAddress());
        address.setLongitude(locationRequest.getLongitudeAddress());
        address.setLatitude(locationRequest.getLatitudeAddress());
        addressRepository.save(address);
        location.setIdAddress(addressRepository.findLastestAddress().getId());

        location.setIdUser((long) 1);
        location.setIdDuration(locationRequest.getIdDuration());
        location.setId(idLocation);
        Location locationsaved = locationRepository.save(location);
        return getLocationProfileDTOWithLocation(locationsaved);
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



    public PageLocationDTO getPageLocationDTOFromPageLocation(Page<Location> pageLocation) {
        List<Location> listLocation = pageLocation.getContent();
        List<LocationProfileDTO> listLocationProfile = getAllLocationProfileDTOWithLocation(listLocation);
        PageLocationDTO pageLocationDTO = new PageLocationDTO();
        pageLocationDTO.setCurrentPage(pageLocation.getNumber() + 1);
        pageLocationDTO.setSumPage(pageLocation.getTotalPages());
        pageLocationDTO.setListLocationProfieDTO(listLocationProfile);
        return pageLocationDTO;
    }

    public PageLocationDTO findAllLocationInOneCategoryPagination(int currentPage, Long idCategory) {
        System.out.println("crrPage : " + currentPage + " | idCate: " + idCategory);
        PageRequest pageRequest = new PageRequest(currentPage - 1, PAGE_SIZE, Sort.Direction.DESC,"id");
        Page<Location> pageLocation = locationRepository.findAllByIdPlaceCategory(idCategory,pageRequest);
        return getPageLocationDTOFromPageLocation(pageLocation);
    }


    public List<LocationProfileForTypeDTO> getNewLocations() {
        List<Location> listNewLocations =   locationRepository.getNewLocations();
        List<LocationProfileForTypeDTO> newLocations = getAllLocationProfileForTypeDTOWithLocation(listNewLocations);
        return newLocations;
    }

    public void deleteLocation(Long idLocation){
        Location  location = locationRepository.findById(idLocation).orElse(new Location());
        locationRepository.deleteById(idLocation);
        addressRepository.deleteById(location.getIdAddress());
        contactRepository.deleteById(location.getIdContact());
        contentRepository.deleteById(location.getIdContent());
        pictureRepository.deleteByIdLocation(idLocation);
        evaluationRepository.deleteByIdLocation(idLocation);

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
