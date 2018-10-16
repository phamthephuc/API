package com.repository;

import com.entity.Note;
import com.entity.PlaceType;
import org.springframework.data.repository.CrudRepository;

public interface NoteRepository extends CrudRepository<Note, Long> {

}
