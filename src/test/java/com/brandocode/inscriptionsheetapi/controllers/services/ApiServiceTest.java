package com.brandocode.inscriptionsheetapi.controllers.services;

import com.brandocode.inscriptionsheetapi.TestUtils;
import com.brandocode.inscriptionsheetapi.controllers.mappers.AssignmentApiMapper;
import com.brandocode.inscriptionsheetapi.controllers.mappers.CareerApiMapper;
import com.brandocode.inscriptionsheetapi.controllers.to.AssignmentTO;
import com.brandocode.inscriptionsheetapi.controllers.to.CareerTO;
import com.brandocode.inscriptionsheetapi.controllers.to.StudentTO;
import com.brandocode.inscriptionsheetapi.models.bo.AssignmentBO;
import com.brandocode.inscriptionsheetapi.models.bo.CareerBO;
import com.brandocode.inscriptionsheetapi.models.de.AssignmentDE;
import com.brandocode.inscriptionsheetapi.models.de.CareerDE;
import com.brandocode.inscriptionsheetapi.models.mappers.AssignmentMapper;
import com.brandocode.inscriptionsheetapi.models.mappers.CareerMapper;
import com.brandocode.inscriptionsheetapi.repo.IAssignmentRepository;
import com.brandocode.inscriptionsheetapi.repo.ICareerRepository;
import com.brandocode.inscriptionsheetapi.repo.IStudentRepository;
import com.brandocode.inscriptionsheetapi.services.AssignmentService;
import com.brandocode.inscriptionsheetapi.services.CareerService;
import com.brandocode.inscriptionsheetapi.services.StudentService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class ApiServiceTest {


    @Mock
    ICareerRepository careerRepository;

    @InjectMocks
    CareerService careerService;

    @Mock
    IAssignmentRepository assignmentRepository;

    @InjectMocks
    AssignmentService assignmentService;

    @Test
    void itShouldAssignThreeAssignmentsToACareerAndVerifyThatTheAssignmentsAreTheDesiredOnesTest() {
        //given
        CareerTO career = TestUtils.getCareerTO();
        AssignmentDE assignment1 = TestUtils.getFirstAssignmentUtil();
        AssignmentDE assignment2 = TestUtils.getSecondAssignmentUtil();
        AssignmentDE assignment3 = TestUtils.getThirdAssignmentUtil();
        //when
        given(assignmentRepository.findAssignmentByName(assignment1.getName())).willReturn(Optional.of(assignment1));
        given(assignmentRepository.findAssignmentByName(assignment2.getName())).willReturn(Optional.of(assignment2));
        given(assignmentRepository.findAssignmentByName(assignment3.getName())).willReturn(Optional.of(assignment3));
        career.setAssignments(List.of(AssignmentApiMapper.convertBOToTO(assignmentService.findAssignment(assignment1.getName())),
                AssignmentApiMapper.convertBOToTO(assignmentService.findAssignment(assignment2.getName())),
                AssignmentApiMapper.convertBOToTO(assignmentService.findAssignment(assignment3.getName()))));
        //then
        assertThat(career.getAssignments().contains(AssignmentApiMapper.convertBOToTO(AssignmentMapper.convertDEToBO(assignment1)))).isTrue();
        assertThat(career.getAssignments().contains(AssignmentApiMapper.convertBOToTO(AssignmentMapper.convertDEToBO(assignment2)))).isTrue();
        assertThat(career.getAssignments().contains(AssignmentApiMapper.convertBOToTO(AssignmentMapper.convertDEToBO(assignment3)))).isTrue();
    }

    @Test
    void itShouldAssignCareerToAStudent(){
        //given
        StudentTO student = TestUtils.getStudentTO();
        CareerDE career = CareerMapper.convertBOToDE(TestUtils.getCareerBO());
        //when
        given(careerRepository.findByCareerCode(career.getCareerCode())).willReturn(Optional.of(career));
        student.setCareer(CareerApiMapper.convertBOToTO(careerService.findCareerByCareerCode(career.getCareerCode())));
        //then
        assertThat(student.getCareer()).isEqualTo(CareerApiMapper.convertBOToTO(CareerMapper.convertDEToBO(career)));
    }
}
