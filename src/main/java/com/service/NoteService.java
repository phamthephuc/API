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

    public boolean deleteNote(Long id){
        Optional<Note> note = noteRepository.findById(id);
        if (note.isPresent()){
            noteRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    public Note addNoteForLocation(Note note, HttpServletRequest request) {
        Traveler travelerCurrent = travelerResponsitory.findByUsername(jwtTokenProvider.getUsername(jwtTokenProvider.resolveToken(request)));
        note.setIdUser(travelerCurrent.getId());
        Note noteOld = noteRepository.findNoteByIdUserAndIdLocation(note.getIdUser(), note.getIdLocation());
        if (noteOld == null){
            return  noteRepository.save(note);
        } else {
            noteOld.setContent(note.getContent());
            return noteRepository.save(noteOld);
        }
    }

    public Note getNoteLocation(HttpServletRequest request,Long idLocation) {
        Traveler travelerCurrent = travelerResponsitory.findByUsername(jwtTokenProvider.getUsername(jwtTokenProvider.resolveToken(request)));
        Note note = noteRepository.findNoteByIdUserAndIdLocation(travelerCurrent.getId(), idLocation);
        return note;

    }

    public boolean editNoteLocation(HttpServletRequest request, Note note) {
        Traveler travelerCurrent = travelerResponsitory.findByUsername(jwtTokenProvider.getUsername(jwtTokenProvider.resolveToken(request)));
        Note noteOld = noteRepository.findNoteByIdUserAndIdLocation(travelerCurrent.getId(), note.getIdLocation());
        if (noteOld != null){
            noteOld.setContent(note.getContent());
            noteRepository.save(noteOld);
            return true;
        } else {
            return false;
        }

    }


    public List<Note> getAllNoteOfUser(HttpServletRequest request) {
        Traveler travelerCurrent = travelerResponsitory.findByUsername(jwtTokenProvider.getUsername(jwtTokenProvider.resolveToken(request)));
        return noteRepository.findAllByIdUser(travelerCurrent.getId());
    }

    public boolean deleteNoteByIdLocation(Long idLocation) {
        List<Note> notes = noteRepository.findAllByIdLocation(idLocation);
        if (notes.size()!= 0){
            noteRepository.deleteAll(notes);
            return true;
        } else {
            return false;
        }
    }

    public List<Note> getAllNote() {
        return (List<Note>) noteRepository.findAll();
    }
}
