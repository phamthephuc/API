package com.service;

import com.entity.Note;
import com.entity.PlaceCategory;
import com.repository.NoteRepository;
import com.repository.PlaceCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NoteService {

    @Autowired
    NoteRepository noteRepository;

    public List<Note> findAllNote(){
        return (List<Note>) noteRepository.findAll();
    }

    public Optional<Note> findById(Long id){
        return noteRepository.findById(id);
    }

    public  void createNote(Note note){
        noteRepository.save(note);
    }

    public void updateNote(Note note){
        noteRepository.save(note);
    }

    public void deleteNote(Long id){
        noteRepository.deleteById(id);
    }
}
