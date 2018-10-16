package com.service;

import com.entity.Contact;
import com.entity.PlaceCategory;
import com.repository.ContactRepository;
import com.repository.PlaceCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContactService {

    @Autowired
    ContactRepository contactRepository;

    public List<Contact> findAllContact(){
        return (List<Contact>) contactRepository.findAll();
    }

    public Optional<Contact> findById(Long id){
        return contactRepository.findById(id);
    }

    public  void createContact(Contact contact){
        contactRepository.save(contact);
    }

    public void updateContact(Contact contact){
        contactRepository.save(contact);
    }

    public void deleteContact(Long id){
        contactRepository.deleteById(id);
    }
}
