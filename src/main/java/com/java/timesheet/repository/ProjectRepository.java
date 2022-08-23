package com.java.timesheet.repository;
import org.springframework.data.repository.CrudRepository;
import com.java.timesheet.model.Project;

import java.util.List;

public interface ProjectRepository extends CrudRepository<Project, Long> {
    List<Project> findByNomProjet(String nom);
}
