package com.repository;

import com.entity.Address;
import com.entity.PlaceType;
import org.springframework.data.repository.CrudRepository;

public interface AddressRepository extends CrudRepository<Address, Long> {

}
