package com.controller;

import com.dto.APIResponseDTO;
import com.entity.Note;
import com.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.PUT;

@RestController
@CrossOrigin(origins = "*", maxAge = 1800)
public class NoteController {
    @Autowired
    NoteService noteService;

    @PostMapping("/api/app/add-note-location")
    public APIResponseDTO addNoteLocation(HttpServletRequest request, @RequestBody Note note){
        Note noteAdded = noteService.addNoteForLocation(note, request);
        return new APIResponseDTO(200,"OK", noteAdded);
    }

    @GetMapping("/api/app/location-note/{idLocation}")
    public APIResponseDTO getNoteLocation(HttpServletRequest request, @PathVariable Long idLocation){
        Note note = noteService.getNoteLocation(request,idLocation);
        if (note != null){
            return new APIResponseDTO(200,"Ok", note.getContent() );
        } else {
            return new APIResponseDTO(500,"Note isn't exist", null );
        }
    }

    @PutMapping("/api/app/edit-note-location")
    public APIResponseDTO editNoteLocation(HttpServletRequest request, @RequestBody Note note){

        if (noteService.editNoteLocation(request,note)){
            return new APIResponseDTO(200,"Edited", null );
        } else {
            return  new APIResponseDTO(500, "Note isn't exist", null);
        }

    }

    @GetMapping(value = "/api/notes")
    public APIResponseDTO getAllNotesOfUser(HttpServletRequest request){
        return new APIResponseDTO(200,"OK", noteService.getAllNoteOfUser(request));
    }

    @GetMapping(value = "/api/all-notes")
    public APIResponseDTO getAllNotes(){
        return new APIResponseDTO(200, "Ok", noteService.getAllNote());
    }

    @DeleteMapping(value = "/api/delete-note/{idNote}")
    public APIResponseDTO deleteNoteById(@PathVariable Long idNote){
        if (noteService.deleteNote(idNote)){
            return new APIResponseDTO(200,"Deleted", null);
        } else
        {
            return new APIResponseDTO(500,"Note isn't exist! ", null);
        }
    }

    @DeleteMapping(value = "/api/delete-note-by-idLocation/{idLocation}")
    public APIResponseDTO deleteNoteByIdLocation(@PathVariable Long idLocation){
        if (noteService.deleteNoteByIdLocation(idLocation)){
            return new APIResponseDTO(200,"Deleted", null);
        } else {
            return new APIResponseDTO(500,"Note isn't exist! ", null);

        }
    }


}
