package com.brandocode.inscriptionsheetapi.controllers.services;

import com.brandocode.inscriptionsheetapi.controllers.mappers.AssignmentApiMapper;
import com.brandocode.inscriptionsheetapi.controllers.mappers.CareerApiMapper;
import com.brandocode.inscriptionsheetapi.controllers.to.AssignmentTO;
import com.brandocode.inscriptionsheetapi.controllers.to.CareerTO;
import com.brandocode.inscriptionsheetapi.controllers.to.StudentTO;
import com.brandocode.inscriptionsheetapi.exceptions.AssignmentDoesNotExistByName;
import com.brandocode.inscriptionsheetapi.models.de.AssignmentDE;
import com.brandocode.inscriptionsheetapi.services.AssignmentService;
import com.brandocode.inscriptionsheetapi.services.CareerService;
import com.brandocode.inscriptionsheetapi.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ApiService {

    @Autowired
    CareerService careerService;

    @Autowired
    AssignmentService assignmentService;

    public void assignAssignments(CareerTO careerTO, String assignment1, String assignment2, String assignment3)
    throws AssignmentDoesNotExistByName {
        AssignmentTO firstAssignment = AssignmentApiMapper.convertBOToTO(assignmentService.findAssignment(assignment1));
        AssignmentTO secondAssignment = AssignmentApiMapper.convertBOToTO(assignmentService.findAssignment(assignment2));
        AssignmentTO thirdAssignment = AssignmentApiMapper.convertBOToTO(assignmentService.findAssignment(assignment3));
        careerTO.setAssignments(List.of(firstAssignment, secondAssignment, thirdAssignment));
    }

    public void assignCareer(StudentTO student, String careerCode){
        CareerTO career = CareerApiMapper.convertBOToTO(careerService.findCareerByCareerCode(careerCode));
        student.setCareer(career);
    }
}
