package com.repository;

import com.entity.Content;
import com.entity.PlaceType;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ContentRepository extends CrudRepository<Content, Long> {
    public Content findByDetail(String detail);

    @Query(value = "SELECT content.* from content  order BY content.id DESC  limit(1);", nativeQuery = true)
    public Content findLastestContent();


}
