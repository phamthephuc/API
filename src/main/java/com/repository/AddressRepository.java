package com.repository;

import com.entity.Address;
import com.entity.PlaceType;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface AddressRepository extends CrudRepository<Address, Long> {
    public  Address findByName(String name);

    @Query(value = "SELECT address.* from address order BY address.id DESC  limit(1);", nativeQuery = true)
    public Address findLastestAddress();





}
