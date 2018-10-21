package com.repository;

import com.entity.Contact;
import com.entity.PlaceType;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ContactRepository extends CrudRepository<Contact, Long> {
    public Contact findByEmail(String email);

    @Query(value = "SELECT contact.* from contact  order BY contact.id DESC  limit(1);", nativeQuery = true)
    public Contact findLastestContact();

}
