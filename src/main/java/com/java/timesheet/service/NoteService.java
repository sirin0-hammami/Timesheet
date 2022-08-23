package com.java.timesheet.service;
import com.java.timesheet.exception.ResourceNotFoundException;
import com.java.timesheet.model.Task;
import com.java.timesheet.model.Note;
import com.java.timesheet.repository.TaskRepository;
import com.java.timesheet.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Set;

@Service
public class NoteService {
    @Autowired
    private NoteRepository noteRepository;
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private TaskService taskService;

    public Note getNoteById(Long id){
        Note note = noteRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("note not exist with id :" + id));
        return note;
    }

    public Set<Note> getNotesByIdTask(Long idtask){
        Task task = taskRepository.findById(idtask).orElseThrow(() -> new ResourceNotFoundException("Task not exist with id :" + idtask));
        return task.getNotes();
    }

    public Note addNote(Note note ,Long idTask){
        Task task = taskRepository.findById(idTask).orElseThrow(() -> new ResourceNotFoundException("Task not exist with"));
        note.setTask(task);
        noteRepository.save(note);
        return note;
    }

    public void updateNote(Long idNote, Note newNote){
        Note note = noteRepository.findById(idNote).orElseThrow( () -> new ResourceNotFoundException("note not found"));
        note.setDescripNote(newNote.getDescripNote());
        noteRepository.save(note);
    }

    public void deleteNote(Long id){
        Note note = noteRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("note not found"));
        noteRepository.delete(note);


    }

}
