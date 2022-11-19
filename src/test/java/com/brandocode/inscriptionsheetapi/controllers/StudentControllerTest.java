package com.brandocode.inscriptionsheetapi.controllers;

import com.brandocode.inscriptionsheetapi.TestUtils;
import com.brandocode.inscriptionsheetapi.controllers.mappers.CareerApiMapper;
import com.brandocode.inscriptionsheetapi.controllers.services.ApiService;
import com.brandocode.inscriptionsheetapi.controllers.to.StudentTO;
import com.brandocode.inscriptionsheetapi.models.bo.CareerBO;
import com.brandocode.inscriptionsheetapi.models.de.CareerDE;
import com.brandocode.inscriptionsheetapi.models.mappers.CareerMapper;
import com.brandocode.inscriptionsheetapi.repo.ICareerRepository;
import com.brandocode.inscriptionsheetapi.repo.IStudentRepository;
import com.brandocode.inscriptionsheetapi.services.CareerService;
import com.brandocode.inscriptionsheetapi.services.StudentService;
import org.apache.coyote.Response;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class StudentControllerTest {

    @Mock
    CareerService careerService;

    @InjectMocks
    ApiService apiService;


    @Test
    void itShouldAssignACareerToAStudentAndSaveIt(){
        //given
        StudentTO student = TestUtils.getStudentTO();
        ResponseEntity<Response> response;
        String careerCode = "careerCode";
        CareerBO career = TestUtils.getCareerBO();
        //when
        given(careerService.findCareerByCareerCode(careerCode)).willReturn(career);
        apiService.assignCareer(student, careerCode);
        //then
        verify(careerService).findCareerByCareerCode(careerCode);
        assertThat(student.getCareer()).isEqualTo(CareerApiMapper.convertBOToTO(careerService.findCareerByCareerCode(careerCode)));
    }

    @Test
    void itShouldFindStudentByStudentCode(){
        //given
        StudentTO student = TestUtils.getStudentTO();
        String studentCode = "studentCode";
        //when

    }
}
