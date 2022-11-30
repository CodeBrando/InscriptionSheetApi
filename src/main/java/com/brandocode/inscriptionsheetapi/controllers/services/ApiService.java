package com.brandocode.inscriptionsheetapi.controllers.services;

import com.brandocode.inscriptionsheetapi.controllers.mappers.AssignmentApiMapper;
import com.brandocode.inscriptionsheetapi.controllers.mappers.CareerApiMapper;
import com.brandocode.inscriptionsheetapi.controllers.to.AssignmentTO;
import com.brandocode.inscriptionsheetapi.controllers.to.CareerTO;
import com.brandocode.inscriptionsheetapi.controllers.to.StudentTO;
import com.brandocode.inscriptionsheetapi.exceptions.AssignmentDoesNotExistByName;
import com.brandocode.inscriptionsheetapi.services.AssignmentService;
import com.brandocode.inscriptionsheetapi.services.CareerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApiService {

    @Autowired
    CareerService careerService;

    @Autowired
    AssignmentService assignmentService;

    public void setAssignmentList(CareerTO careerTO, String assignment1, String assignment2, String assignment3)
    throws AssignmentDoesNotExistByName {
        AssignmentTO firstAssignment = AssignmentApiMapper.convertBOToTO(assignmentService.findAssignmentByName(assignment1));
        AssignmentTO secondAssignment = AssignmentApiMapper.convertBOToTO(assignmentService.findAssignmentByName(assignment2));
        AssignmentTO thirdAssignment = AssignmentApiMapper.convertBOToTO(assignmentService.findAssignmentByName(assignment3));
        careerTO.setAssignments(List.of(firstAssignment, secondAssignment, thirdAssignment));
    }


    public void assignCareer(StudentTO student, String careerCode){
        CareerTO career = CareerApiMapper.convertBOToTO(careerService.findCareerByCareerCode(careerCode));
        student.setCareer(career);
    }
}

