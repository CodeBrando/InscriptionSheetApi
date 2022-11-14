package com.brandocode.inscriptionsheetapi.controllers;

import com.brandocode.inscriptionsheetapi.controllers.mappers.AssignmentApiMapper;
import com.brandocode.inscriptionsheetapi.controllers.services.ApiService;
import com.brandocode.inscriptionsheetapi.controllers.to.AssignmentTO;
import com.brandocode.inscriptionsheetapi.controllers.to.ResponseTO;
import com.brandocode.inscriptionsheetapi.services.AssignmentService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.List;

@RestController
@Log4j2
public class AssignmentController implements IAssignmentController{

    @Autowired
    AssignmentService assignmentService;

    ApiService apiService;

    @Override
    public ResponseEntity<ResponseTO> createAssignment(AssignmentTO assignmentTO){
        log.info("STARTING TO CREATE ASSIGNMENT...");
        ResponseEntity<ResponseTO> response;
        try{
            assignmentService.saveAssignment(AssignmentApiMapper.convertTOToBO(assignmentTO));
            response = new ResponseEntity<>(ResponseTO.builder().message(HttpStatus.CREATED.name()).build(), HttpStatus.CREATED);
            log.info("ASSIGNMENT CREATED SUCCESSFULLY!");
        } catch(EntityExistsException e){
            log.error("SOMETHING HAS GONE WRONG WHILE CREATING ASSIGNMENT");
            response = new ResponseEntity<>(ResponseTO.builder().message(e.getMessage()).build(), HttpStatus.BAD_REQUEST);
        }
        return response;
    }

    @Override
    public ResponseEntity<?> findAllAssignments(){
        log.info("STARTING TO RETRIEVE ALL ASSIGNMENTS...");
        ResponseEntity<?> response;
        try{
            List<AssignmentTO> assignments = AssignmentApiMapper.convertBOListToTOList(assignmentService.findAllAssignments());
            response = new ResponseEntity<>(assignments, HttpStatus.OK);
            log.info("ALL ASSIGNMENTS RETRIEVED SUCCESSFULLY!");
        } catch(EntityNotFoundException e){
            log.error("SOMETHING HAS GONE WRONG WHILE RETRIEVING ALL ASSIGNMENTS");
            response = new ResponseEntity<>(ResponseTO.builder().message(e.getMessage()).build(), HttpStatus.NOT_FOUND);
        }
        return response;
    }

    @Override
    public ResponseEntity<?> findAssignmentByAssignmentCode(String assignmentCode){
        log.info("STARTING TO RETRIEVE DESIRED ASSIGNMENT...");
        ResponseEntity<?> response;
        try{
            AssignmentTO assignment = AssignmentApiMapper.convertBOToTO(assignmentService.findAssignmentByAssignmentCode(assignmentCode));
            response = new ResponseEntity<>(assignment, HttpStatus.OK);
        }catch(EntityNotFoundException e){
            log.error("SOMETHING HAS GONE WRONG WHILE RETRIEVING THE DESIRED ASSIGNMENT");
            response = new ResponseEntity<>(ResponseTO.builder().message(e.getMessage()).build(), HttpStatus.NOT_FOUND);
        }
        return response;
    }

    @Override
    public ResponseEntity<ResponseTO> updateAssignment(AssignmentTO assignmentTO, String assignmentCode){
        log.info("STARTING TO UPDATE ASSIGNMENT...");
        ResponseEntity<ResponseTO> response;
        try{
            assignmentService.updateAssignment(AssignmentApiMapper.convertTOToBO(assignmentTO), assignmentCode);
            response = new ResponseEntity<>(ResponseTO.builder().message(HttpStatus.OK.name()).build(), HttpStatus.OK);
            log.info("ASSIGNMENT UPDATED SUCCESSFULLY!");
        }catch(EntityNotFoundException e){
            log.error("SOMETHING HAS GONE WRONG WHILE UPDATING ASSIGNMENT");
            response = new ResponseEntity<>(ResponseTO.builder().message(e.getMessage()).build(), HttpStatus.NOT_FOUND);
        }
        return response;
    }

    @Override
    public ResponseEntity<ResponseTO> deleteAssignment(String assignmentCode){
        log.info("STARTING TO DELETE ASSIGNMENT...");
        ResponseEntity<ResponseTO> response;
        try{
            assignmentService.deleteAssignment(assignmentCode);
            response = new ResponseEntity<>(ResponseTO.builder().message(HttpStatus.OK.name()).build(), HttpStatus.OK);
        }catch(EntityNotFoundException e){
            log.error("SOMETHING HAS GONE WRONG WHILE DELETING ASSIGNMENT");
            response = new ResponseEntity<>(ResponseTO.builder().message(e.getMessage()).build(), HttpStatus.NOT_FOUND);
        }
        return response;
    }

}
