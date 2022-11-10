package com.brandocode.inscriptionsheetapi.repo;

import com.brandocode.inscriptionsheetapi.models.de.AssignmentDE;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IAssignmentRepository extends JpaRepository<AssignmentDE, Long> {
    boolean assignmentExistsByAssignmentCode(String assignmentCode);
    Optional<AssignmentDE> findAssignmentByAssignmentCode(String assignmentCode);
}

