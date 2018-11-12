package com.service;

import com.config.JwtTokenProvider;
import com.entity.Note;
import com.entity.PlaceCategory;
import com.entity.Traveler;
import com.repository.NoteRepository;
import com.repository.PlaceCategoryRepository;
import com.repository.TravelerResponsitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@Service
public class NoteService {

    @Autowired
    NoteRepository noteRepository;

    @Autowired
    TravelerResponsitory travelerResponsitory;

    @Autowired
    JwtTokenProvider jwtTokenProvider;

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

    public Note addNoteForLocation(Note note, HttpServletRequest request) {
        Traveler travelerCurrent = travelerResponsitory.findByUsername(jwtTokenProvider.getUsername(jwtTokenProvider.resolveToken(request)));
        note.setIdUser(travelerCurrent.getId());
        return  noteRepository.save(note);
    }

    public Note getNoteLocation(HttpServletRequest request,Long idLocation) {
        Traveler travelerCurrent = travelerResponsitory.findByUsername(jwtTokenProvider.getUsername(jwtTokenProvider.resolveToken(request)));
        Note note = noteRepository.findNoteByIdUserAndIdLocation(travelerCurrent.getId(), idLocation);
        return note;

    }

    public Note editNoteLocation(HttpServletRequest request, Note note) {
        Traveler travelerCurrent = travelerResponsitory.findByUsername(jwtTokenProvider.getUsername(jwtTokenProvider.resolveToken(request)));
        Note noteOld = noteRepository.findNoteByIdUserAndIdLocation(travelerCurrent.getId(), note.getIdLocation());
        noteOld.setContent(note.getContent());
        return noteRepository.save(noteOld);
    }
}
