package com.brandocode.inscriptionsheetapi.services;

import com.brandocode.inscriptionsheetapi.models.bo.CareerBO;
import com.brandocode.inscriptionsheetapi.models.mappers.CareerMapper;
import com.brandocode.inscriptionsheetapi.repo.ICareerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import java.util.List;
import java.util.UUID;

@Service
public class CareerService {
    @Autowired
    ICareerRepository repository;

    public void saveCareer(CareerBO careerBO) throws EntityExistsException {
        careerBO.setCareerCode(UUID.randomUUID().toString());
        if(repository.existsByCareerCode(careerBO.getCareerCode())){
            throw new EntityExistsException("Career with code " + careerBO.getCareerCode() + " already exists");
        }
        repository.save(CareerMapper.convertBOToDE(careerBO));
    }

    public List<CareerBO> findAllCareers(){
        return CareerMapper.convertDEListToBOList(repository.findAll());
    }

    public void findCareerByName(String name){
        repository.findCareerByName(name);
    }
}