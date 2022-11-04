package com.brandocode.inscriptionsheetapi.controller.services;

import com.brandocode.inscriptionsheetapi.controller.mappers.AssignmentApiMapper;
import com.brandocode.inscriptionsheetapi.controller.to.AssignmentTO;
import com.brandocode.inscriptionsheetapi.controller.to.CareerTO;
import com.brandocode.inscriptionsheetapi.controller.to.StudentTO;
import com.brandocode.inscriptionsheetapi.enums.CareerDoesNotExistByName;
import com.brandocode.inscriptionsheetapi.exceptions.AssignmentDoesNotExistByName;
import com.brandocode.inscriptionsheetapi.services.AssignmentService;
import com.brandocode.inscriptionsheetapi.services.CareerService;
import com.brandocode.inscriptionsheetapi.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ApiService {

    @Autowired
    StudentService studentService;

    @Autowired
    CareerService careerService;

    @Autowired
    AssignmentService assignmentService;

    public CareerTO assignAssignments(CareerTO careerTO, String assignment1, String assignment2, String assignment3)
    throws AssignmentDoesNotExistByName {
        AssignmentTO firstAssignment = AssignmentApiMapper.convertBOToTO(assignmentService.findAssignment(assignment1));
        AssignmentTO secondAssignment = AssignmentApiMapper.convertBOToTO(assignmentService.findAssignment(assignment2));
        AssignmentTO thirdAssignment = AssignmentApiMapper.convertBOToTO(assignmentService.findAssignment(assignment3));
        List<AssignmentTO> assignments = new ArrayList<>();
        assignments.add(firstAssignment);
        assignments.add(secondAssignment);
        assignments.add(thirdAssignment);
        careerTO.setAssignments(assignments);
        return careerTO;
    }

    public StudentTO assignCareer(StudentTO student, CareerTO career) throws CareerDoesNotExistByName {
        student.setCareer(career);
        return student;
    }
}
