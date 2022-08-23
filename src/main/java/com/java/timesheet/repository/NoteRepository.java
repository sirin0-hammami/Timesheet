package com.java.timesheet.repository;
import org.springframework.data.repository.CrudRepository;
import com.java.timesheet.model.Note;
public interface NoteRepository extends CrudRepository<Note,Long>{
}
