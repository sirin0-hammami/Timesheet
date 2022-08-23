package com.java.timesheet.repository;
import com.java.timesheet.model.Project;
import com.java.timesheet.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.java.timesheet.model.Task;

import java.util.List;

public interface TaskRepository extends CrudRepository<Task, Long>{

    List<Task> findByUser (User user);

}
