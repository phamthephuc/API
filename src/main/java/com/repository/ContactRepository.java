package com.repository;

import com.entity.Contact;
import com.entity.PlaceType;
import org.springframework.data.repository.CrudRepository;

public interface ContactRepository extends CrudRepository<Contact, Long> {

}
