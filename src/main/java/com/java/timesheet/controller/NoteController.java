package com.java.timesheet.controller;

import com.java.timesheet.model.Note;
import com.java.timesheet.model.Task;
import com.java.timesheet.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping
public class NoteController {
    @Autowired
    private NoteService noteService;

    @RequestMapping(value="/note/{id}")
    public Note getNotebyId(@PathVariable(value = "id") Long id) {
        return noteService.getNoteById(id);
    }

    @RequestMapping(value="/note/task/{id}")
    public Set<Note> getNotesbyIdTask(@PathVariable(value = "id") Long id) {
        return noteService.getNotesByIdTask(id);
    }
    @RequestMapping(value="/addNote/{idTask}" , method = RequestMethod.POST)
    public Note addNote(@PathVariable("idTask") Long idTask , @RequestBody Note note){
        return noteService.addNote(note, idTask);
    }
    @RequestMapping(value="/updateNote/{id}" , method = RequestMethod.PUT)
    public void  updateNote(@PathVariable("id") Long id ,@RequestBody Note note){
        noteService.updateNote(id, note);
    }
    @RequestMapping(value="/deleteNote/{id}")
    public void deleteNote(@PathVariable("id") Long id){
        noteService.deleteNote(id);
    }
}
