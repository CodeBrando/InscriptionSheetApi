package com.brandocode.inscriptionsheetapi.repo;

import com.brandocode.inscriptionsheetapi.models.de.StudentDE;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IStudentRepository extends JpaRepository<StudentDE, Long> {
    boolean existsByStudentCode(String studentCode);

    void findByStudentCode(String studentCode);

}
