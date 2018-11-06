package com.repository;

import com.entity.Traveler;
import org.springframework.data.repository.CrudRepository;

public interface TravelerResponsitory extends CrudRepository<Traveler, Long> {
    public Traveler findByUsername(String username);
}
