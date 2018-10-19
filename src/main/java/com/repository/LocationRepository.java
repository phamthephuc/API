package com.repository;

import com.entity.Location;
import com.entity.Users;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface LocationRepository extends CrudRepository<Location, Long> {

    @Query(value = "Select * from location inner JOIN evaluation on evaluation.id_location = location.id " +
            "where evaluation.id_user = ?1", nativeQuery = true)
    public List<Location> getAllLocationByUser(Long id);

    @Query(value = "Select users.* from users inner join evaluation on evaluation.id_user = users.id" +
            " where evaluation.id_location in (select evaluation.id_location from evaluation where evaluation.id_user= 1);" , nativeQuery = true)
    public List<Users> getAllUserSameLocationWithOnes(Long id);

    public List<Location> findByIdPlaceCategory(Long id);
}
