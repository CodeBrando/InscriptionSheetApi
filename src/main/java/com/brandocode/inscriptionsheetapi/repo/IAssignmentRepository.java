package com.brandocode.inscriptionsheetapi.repo;

import com.brandocode.inscriptionsheetapi.models.de.AssignmentDE;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IAssignmentRepository extends JpaRepository<AssignmentDE, Long> {

    Optional<AssignmentDE> findByName(String name);

    Optional<AssignmentDE> findByAssignmentCode(String assignmentCode);

    boolean existsByAssignmentCode(String careerCode);
}

