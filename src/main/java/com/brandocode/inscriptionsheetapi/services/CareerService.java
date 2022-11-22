package com.brandocode.inscriptionsheetapi.services;

import com.brandocode.inscriptionsheetapi.models.bo.CareerBO;
import com.brandocode.inscriptionsheetapi.models.mappers.CareerMapper;
import com.brandocode.inscriptionsheetapi.repo.ICareerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class CareerService {
    @Autowired
    ICareerRepository repository;

    public void saveCareer(CareerBO careerBO) throws EntityExistsException {
        if(repository.existsByCareerCode(careerBO.getCareerCode())){
            throw new EntityExistsException("Career with code " + careerBO.getCareerCode() + " already exists");
        }
        repository.save(CareerMapper.convertBOToDE(careerBO));
    }

    public List<CareerBO> findAllCareers(){
        return CareerMapper.convertDEListToBOList(repository.findAll());
    }

    public CareerBO findCareerByCareerCode(String careerCode) throws EntityNotFoundException {
        return CareerMapper.convertDEToBO(repository.findByCareerCode(careerCode)
                .orElseThrow(() -> new EntityNotFoundException("Career with career code " +careerCode+ " does not exist.")));
    }

    public void updateCareer(CareerBO careerBO, String careerCode){
        CareerBO careerToUpdate = findCareerByCareerCode(careerCode);
        careerBO.setId(careerToUpdate.getId());
        careerBO.setCareerCode(careerCode);
        repository.save(CareerMapper.convertBOToDE(careerBO));
    }

    public void deleteCareer(String careerCode){
        repository.delete(CareerMapper.convertBOToDE(findCareerByCareerCode(careerCode)));
    }
}
