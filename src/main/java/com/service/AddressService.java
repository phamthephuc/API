package com.service;

import com.entity.Address;
import com.entity.PlaceCategory;
import com.repository.AddressRepository;
import com.repository.PlaceCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressService {

    @Autowired
    AddressRepository addressRepository;

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
}
