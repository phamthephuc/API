package com.repository;

import com.entity.InforUsers;
import org.springframework.data.repository.CrudRepository;

public interface InforUsersRepository extends CrudRepository<InforUsers, Long> {

    public InforUsers getInforUsersByIdUser(Long idUser);
}
