package com.repository;

import com.entity.Note;
import com.entity.PlaceType;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface NoteRepository extends CrudRepository<Note, Long> {

    public  Note findNoteByIdUserAndIdLocation(Long idUser, Long idLocation);

    public List<Note> findAllByIdUser(Long idUser);

    public List<Note>  findAllByIdLocation(Long idLocation);


}
