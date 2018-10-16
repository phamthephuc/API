package com.service;

import com.dto.LocationDTO;
import com.entity.*;
import com.repository.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    @Autowired
    ModelMapper  modelMapper;


    public List<LocationDTO> findAllLocation(){
        ArrayList<LocationDTO> listLocationDTO = new ArrayList<>();
        ArrayList<Location> listLocations = (ArrayList<Location>) locationRepository.findAll();
        for(Location location: listLocations){
            LocationDTO locationDTO = new LocationDTO();
            locationDTO = modelMapper.map(location,LocationDTO.class);

            //placeCatrgory
            PlaceCategory placeCategory;
            placeCategory = placeCategoryRepository.findById(location.getIdPlaceCategory()).orElse(new PlaceCategory());
            locationDTO.setPlaceCategory(placeCategory);

            //Content
            Content content;
            content = contentRepository.findById(location.getIdContent()).orElse(new Content());
            locationDTO.setContent(content);

            //Status
            Status status;
            status = statusRepository.findById(location.getIdStatus()).orElse(new Status());
            locationDTO.setStatus(status);

            //Address
            Address address;
            address = addressRepository.findById(location.getIdAddress()).orElse(new Address());
            locationDTO.setAddress(address);

            //Contact
            Contact contact;
            contact = contactRepository.findById(location.getIdContact()).orElse(new Contact());
            locationDTO.setContact(contact);

            //User
            Users users;
            users = usersRepository.findById(location.getIdUser()).orElse(new Users());
            locationDTO.setUsers(users);

            //duration
            Duration duration;
            duration = durationRepository.findById(location.getIdDuration()).orElse(new Duration());
            locationDTO.setDuration(duration);

            List<Picture> listPicture = pictureRepository.findById()







        }
        return  listLocationDTO;
    }

    public Optional<Location> findById(Long id){
        return locationRepository.findById(id);
    }

    public  void createLocation(Location location ){
        locationRepository.save(location);
    }

    public void updateLocation(Location location){
        locationRepository.save(location);
    }

    public void deleteLocation(Long id){
        locationRepository.deleteById(id);
    }
}
