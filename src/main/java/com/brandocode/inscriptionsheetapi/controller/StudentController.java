package com.brandocode.inscriptionsheetapi.controller;


import com.brandocode.inscriptionsheetapi.controller.mappers.StudentApiMapper;
import com.brandocode.inscriptionsheetapi.controller.services.ApiService;
import com.brandocode.inscriptionsheetapi.controller.to.ResponseTO;
import com.brandocode.inscriptionsheetapi.controller.to.StudentTO;
import com.brandocode.inscriptionsheetapi.services.StudentService;
import io.swagger.models.Response;
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
public class StudentController implements IStudentController{

    @Autowired
    StudentService studentService;

    @Autowired
    ApiService apiService;

    @Override
    public ResponseEntity<ResponseTO> createStudent(StudentTO student, String careerCode){
        log.info("STARTING TO CREATE USER...");
        ResponseEntity<ResponseTO> response;
        try {
            apiService.assignCareer(student, careerCode);
            response = new ResponseEntity<>(ResponseTO.builder().message(HttpStatus.CREATED.name()).build(), HttpStatus.CREATED);
            log.info("STUDENT CREATED SUCCESSFULLY!");
        } catch(EntityExistsException e){
            log.error("SOMETHING HAS GONE WRONG WHILE CREATING THE STUDENT.");
            response = new ResponseEntity<>(ResponseTO.builder().message(e.getMessage()).build(), HttpStatus.BAD_REQUEST);
        }
        return response;
    }

    @Override
    public ResponseEntity<?> findStudentByStudentCode(String studentCode){
        log.info("STARTING TO LOOK FOR THE DESIRED STUDENT...");
        ResponseEntity<?> response;
        try{
            StudentTO student = StudentApiMapper.convertBOToTO(studentService.findStudentByStudentCode(studentCode));
            response = new ResponseEntity<>(student, HttpStatus.OK);
            log.info("STUDENT FOUND SUCCESSFULLY!");
        } catch(EntityNotFoundException e){
            log.error("SOMETHING HAS GONE WRONG WHILE RETRIEVING THE STUDENT");
            response = new ResponseEntity<>(ResponseTO.builder().message(e.getMessage()).build(), HttpStatus.NOT_FOUND);
        }
        return response;
    }

    @Override
    public ResponseEntity<?> findAllStudents(){
        log.info("STARTING TO LOOK FOR ALL STUDENTS...");
        ResponseEntity<?> response;
        try{
            List<StudentTO> students = StudentApiMapper.convertBOListToTOList(studentService.findAllStudents());
            response = new ResponseEntity<>(students, HttpStatus.OK);
            log.info("ALL STUDENTS FOUND SUCCESSFULLY");
        } catch(EntityNotFoundException e){
            log.error("SOMETHING HAS GONE WRONG WHILE RETRIEVING ALL STUDENTS");
            response = new ResponseEntity<>(ResponseTO.builder().message(e.getMessage()).build(), HttpStatus.NOT_FOUND);
        }
        return response;
    }

    @Override
    public ResponseEntity<ResponseTO> updateStudent(StudentTO studentTO, String studentCode){
        log.info("STARTING TO UPDATE STUDENT...");
        ResponseEntity<ResponseTO> response;
        try{
            studentService.updateStudent(StudentApiMapper.convertTOToBO(studentTO), studentCode);
            response = new ResponseEntity<>(ResponseTO.builder().message(HttpStatus.OK.name()).build(), HttpStatus.OK);
            log.info("STUDENT UPDATED SUCCESSFULLY!");
        } catch(EntityNotFoundException e){
            log.error("SOMETHING HAS GONE WRONG WHILE UPDATING STUDENT");
            response = new ResponseEntity<>(ResponseTO.builder().message(e.getMessage()).build(), HttpStatus.NOT_FOUND);
        }
        return response;
    }

    @Override
    public ResponseEntity<ResponseTO> deleteStudent(String studentCode){
        log.info("STARTING TO DELETE STUDENT...");
        ResponseEntity<ResponseTO> response;
        try{
            studentService.deleteStudent(studentService.findStudentByStudentCode(studentCode));
            response = new ResponseEntity<>(ResponseTO.builder().message(HttpStatus.OK.name()).build(), HttpStatus.OK);
            log.info("STUDENT DELETED SUCCESSFULLY");
        } catch(EntityNotFoundException e){
            log.error("SOMETHING HAS GONE WRONG WHILE DELETING STUDENT");
            response = new ResponseEntity<>(ResponseTO.builder().message(e.getMessage()).build(), HttpStatus.NOT_FOUND);
        }
        return response;
    }
}
