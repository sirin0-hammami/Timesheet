package com.java.timesheet.repository;
import com.java.timesheet.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import com.java.timesheet.model.Pointage;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Repository;

import java.util.*;


@Repository
public interface PointageRepository extends JpaRepository<Pointage, Long>{
  Set<Pointage> findByDate (@DateTimeFormat(pattern = "yyyy-MM-dd") Date date);


}
