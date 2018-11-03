package com.repository;

import com.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface RoleRespository extends JpaRepository<Role, Long> {
    Optional<Role> findById(Long id);
}
