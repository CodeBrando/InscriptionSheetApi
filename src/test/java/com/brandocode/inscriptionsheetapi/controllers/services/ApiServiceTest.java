package com.brandocode.inscriptionsheetapi.controllers.services;

import com.brandocode.inscriptionsheetapi.TestUtils;
import com.brandocode.inscriptionsheetapi.controllers.mappers.AssignmentApiMapper;
import com.brandocode.inscriptionsheetapi.controllers.mappers.CareerApiMapper;
import com.brandocode.inscriptionsheetapi.controllers.to.CareerTO;
import com.brandocode.inscriptionsheetapi.controllers.to.StudentTO;
import com.brandocode.inscriptionsheetapi.models.bo.AssignmentBO;
import com.brandocode.inscriptionsheetapi.models.bo.CareerBO;
import com.brandocode.inscriptionsheetapi.services.AssignmentService;
import com.brandocode.inscriptionsheetapi.services.CareerService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class ApiServiceTest {

    @InjectMocks
    ApiService apiService;


    @Mock
    CareerService careerService;


    @Mock
    AssignmentService assignmentService;

    @Test
    void itShouldAssignThreeAssignmentsToACareerAndVerifyThatTheAssignmentsAreTheDesiredOnesTest() {
        //given
        CareerTO career = TestUtils.getCareerTO();
        AssignmentBO assignment1 = TestUtils.getFirstAssignmentUtil();
        AssignmentBO assignment2 = TestUtils.getSecondAssignmentUtil();
        AssignmentBO assignment3 = TestUtils.getThirdAssignmentUtil();
        //when
        given(assignmentService.findAssignmentByName(assignment1.getName())).willReturn(assignment1);
        given(assignmentService.findAssignmentByName(assignment2.getName())).willReturn(assignment2);
        given(assignmentService.findAssignmentByName(assignment3.getName())).willReturn(assignment3);
        career.setAssignments(List.of(AssignmentApiMapper.convertBOToTO(assignmentService.findAssignmentByName(assignment1.getName())),
                AssignmentApiMapper.convertBOToTO(assignmentService.findAssignmentByName(assignment2.getName())),
                AssignmentApiMapper.convertBOToTO(assignmentService.findAssignmentByName(assignment3.getName()))));
        //then
        assertThat(career.getAssignments().contains(AssignmentApiMapper.convertBOToTO(assignment1))).isTrue();
        assertThat(career.getAssignments().contains(AssignmentApiMapper.convertBOToTO(assignment2))).isTrue();
        assertThat(career.getAssignments().contains(AssignmentApiMapper.convertBOToTO(assignment3))).isTrue();
    }

    @Test
    void itShouldAssignCareerToAStudent(){
        //given
        StudentTO student = TestUtils.getStudentTO();
        CareerBO career = TestUtils.getCareerBO();
        //when
        given(careerService.findCareerByCareerCode(career.getCareerCode())).willReturn(career);
        student.setCareer(CareerApiMapper.convertBOToTO(careerService.findCareerByCareerCode(career.getCareerCode())));
        //then
        assertThat(student.getCareer()).isEqualTo(CareerApiMapper.convertBOToTO(career));
    }
}
