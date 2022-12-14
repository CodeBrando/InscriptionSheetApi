package com.brandocode.inscriptionsheetapi.controllers;

import com.brandocode.inscriptionsheetapi.controllers.mappers.CareerApiMapper;
import com.brandocode.inscriptionsheetapi.controllers.services.ApiService;
import com.brandocode.inscriptionsheetapi.controllers.to.CareerTO;
import com.brandocode.inscriptionsheetapi.controllers.to.ResponseTO;
import com.brandocode.inscriptionsheetapi.exceptions.AssignmentDoesNotExistByName;
import com.brandocode.inscriptionsheetapi.services.CareerService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.UUID;

@RestController
@Log4j2
public class CareerController implements ICareerController{

    @Autowired
    CareerService careerService;

    @Autowired
    ApiService apiService;

    @Override
    public ResponseEntity<ResponseTO> createCareer(CareerTO careerTO, String assignment1, String assignment2, String assignment3){
        log.info("STARTING TO CREATE CAREER...");
        ResponseEntity<ResponseTO> response;
        try{
            apiService.setAssignmentList(careerTO, assignment1, assignment2, assignment3);
            careerTO.setCareerCode(UUID.randomUUID().toString());
            careerService.saveCareer(CareerApiMapper.convertTOToBO(careerTO));
            response = new ResponseEntity<>(ResponseTO.builder().message(HttpStatus.OK.name()).build(), HttpStatus.OK);
        } catch (AssignmentDoesNotExistByName e) {
            log.error("ASSIGNMENT WITH GIVEN NAME DOES NOT EXIST");
            response = new ResponseEntity<>(ResponseTO.builder().message(e.getMessage()).build(), HttpStatus.BAD_REQUEST);
        }catch (EntityExistsException e){
            log.error("SOMETHING HAS GONE WRONG WHILE CREATING CAREER");
            response = new ResponseEntity<>(ResponseTO.builder().message(e.getMessage()).build(), HttpStatus.BAD_REQUEST);
        }
        return response;
    }

    @Override
    public ResponseEntity<?> findCareerByCareerCode(String careerCode){
        log.info("STARTING TO LOOK FOT THE DESIRED CAREER...");
        ResponseEntity<?> response;
        try{
            CareerTO career = CareerApiMapper.convertBOToTO(careerService.findCareerByCareerCode(careerCode));
            response = new ResponseEntity<>(career, HttpStatus.OK);
            log.info("CAREER RETRIEVED SUCCESSFULLY!");
        } catch(EntityNotFoundException e){
            log.error("SOMETHING HAS GONE WRONG WHILE RETRIEVING CAREER");
            response = new ResponseEntity<>(ResponseTO.builder().message(e.getMessage()).build(), HttpStatus.NOT_FOUND);
        }
        return response;
    }

    @Override
    public ResponseEntity<?> findAllCareers(){
        log.info("STARTING TO FIND ALL CAREERS...");
        ResponseEntity<?> response;
        try{
            List<CareerTO> careers = CareerApiMapper.convertBOListToTOList((careerService.findAllCareers()));
            response = new ResponseEntity<>(careers, HttpStatus.OK);
            log.info("ALL CAREERS RETRIEVED SUCCESSFULLY!");
        }catch(EntityNotFoundException e){
            log.error("SOMETHING HAS GONE WRONG WHILE RETRIEVING ALL CAREERS");
            response = new ResponseEntity<>(ResponseTO.builder().message(e.getMessage()).build(), HttpStatus.NOT_FOUND);
        }
        return response;
    }

    @Override
    public ResponseEntity<ResponseTO> updateCareer(CareerTO careerTO, String careerCode){
        log.info("STARTING TO UPDATE CAREER...");
        ResponseEntity<ResponseTO> response;
        try{
            careerService.updateCareer(CareerApiMapper.convertTOToBO(careerTO), careerCode);
            response = new ResponseEntity<>(ResponseTO.builder().message(HttpStatus.OK.name()).build(), HttpStatus.OK);
            log.info("CAREER UPDATED SUCCESSFULLY!");
        } catch(EntityNotFoundException e){
            log.info("SOMETHING HAS GONE WRONG WHILE UPDATING CAREER");
            response = new ResponseEntity<>(ResponseTO.builder().message(e.getMessage()).build(), HttpStatus.NOT_FOUND);
        }
        return response;
    }

    @Override
    public ResponseEntity<ResponseTO> deleteCareer(String careerCode){
        log.info("STARTING TO DELETE CAREER...");
        ResponseEntity<ResponseTO> response;
        try{
            careerService.deleteCareer(careerCode);
            response = new ResponseEntity<>(ResponseTO.builder().message(HttpStatus.OK.name()).build(), HttpStatus.OK);
        }catch(EntityNotFoundException e){
            log.error("SOMETHING HAS GONE WRONG WHILE DELETING CAREER");
            response = new ResponseEntity<>(ResponseTO.builder().message(e.getMessage()).build(), HttpStatus.NOT_FOUND);
        }
        return response;
    }

}
