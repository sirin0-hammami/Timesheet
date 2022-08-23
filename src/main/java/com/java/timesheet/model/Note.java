package com.java.timesheet.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
public class Note {
    @GeneratedValue
    @Id
    private long noteId;

    @Column(name ="descripNote")
    private String descripNote;
@JsonIgnore
    @ManyToOne
    @JoinColumn(name="taskId")
    private Task task;

    public Note() {
    }

    public String getDescripNote() {
        return descripNote;
    }

    public void setDescripNote(String descripNote) {
        this.descripNote = descripNote;
    }

    public Task getTask() {
        return task;
    }

    public long getNoteId() {
        return noteId;
    }

    public void setNoteId(long noteId) {
        this.noteId = noteId;
    }

    public void setTask(Task task) {
        this.task = task;
    }

}
