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
        return new APIResponseDTO(200,"Ok", noteService.getNoteLocation(request,idLocation));
    }

    @PutMapping("/api/app/edit-note-location")
    public APIResponseDTO editNoteLocation(HttpServletRequest request, @RequestBody Note note){
        return new APIResponseDTO(200,"OK", noteService.editNoteLocation(request,note));
    }
}
