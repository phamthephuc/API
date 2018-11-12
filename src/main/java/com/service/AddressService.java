package com.service;

import com.entity.Address;
import com.entity.Location;
import com.entity.PlaceCategory;
import com.repository.AddressRepository;
import com.repository.LocationRepository;
import com.repository.PlaceCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressService {

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    LocationRepository locationRepository;

    public List<Address> findAllAddress(){
        return (List<Address>) addressRepository.findAll();
    }

    public Optional<Address> findById(Long id){
        return addressRepository.findById(id);
    }

    public  void createAddress(Address address){
        addressRepository.save(address);
    }

    public void updateAddress(Address address){
        addressRepository.save(address);
    }

    public void deleteAddress(Long id){
        addressRepository.deleteById(id);
    }

    public Address editAddressOfLocation(Long idLocation, Address address) {
        Location location = locationRepository.findById(idLocation).orElse(new Location());
        Address addressOld = addressRepository.findById(location.getIdAddress()).orElse(new Address());
        addressOld.setName(address.getName());
        addressOld.setLink(address.getLatitude()+"|"+address.getLongitude());
        addressOld.setLatitude(address.getLatitude());
        addressOld.setLongitude(address.getLongitude());
        return addressRepository.save(addressOld);
    }
}
