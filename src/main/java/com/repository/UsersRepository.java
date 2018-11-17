package com.repository;

import com.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface UsersRepository extends JpaRepository<Users, Long> {
    @Query(value = "Select users.* from users inner join evaluation on evaluation.id_user = users.id" +
            " where evaluation.id_location in (select evaluation.id_location from evaluation " +
            "where evaluation.id_user = ?1) AND users.id != ?1 GROUP BY users.id ORDER BY COUNT(evaluation.id_location) " +
            "DESC LIMIT 100;" , nativeQuery = true)
    public List<Users> findAllUserSameLocationWithOnes(Long id);


    public Optional<Users> findById(Long id);

    public Users findByUsername(String username);
}
