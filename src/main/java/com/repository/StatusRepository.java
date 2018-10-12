package com.repository;


import com.entity.Status;
import org.springframework.data.repository.CrudRepository;

public interface StatusRepository  extends CrudRepository<Status, Long> {
}
