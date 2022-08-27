package com.java.timesheet.repository;

import java.util.Optional;

import com.java.timesheet.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ImageRepository extends JpaRepository<Image, Long> {
    Optional<Image> findByName(String name);
}
