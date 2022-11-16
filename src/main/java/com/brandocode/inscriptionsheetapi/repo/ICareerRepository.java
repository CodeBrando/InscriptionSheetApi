package com.brandocode.inscriptionsheetapi.repo;

import com.brandocode.inscriptionsheetapi.models.de.CareerDE;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ICareerRepository extends JpaRepository<CareerDE, Long> {
    boolean existsByCareerCode(String careerCode);

    void findCareerByName(String name);

    Optional<CareerDE> findByCareerCode(String careerCode);

}
