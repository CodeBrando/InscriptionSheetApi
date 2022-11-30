package com.brandocode.inscriptionsheetapi.controllers;

import com.brandocode.inscriptionsheetapi.TestUtils;
import com.brandocode.inscriptionsheetapi.controllers.mappers.CareerApiMapper;
import com.brandocode.inscriptionsheetapi.controllers.services.ApiService;
import com.brandocode.inscriptionsheetapi.controllers.to.CareerTO;
import com.brandocode.inscriptionsheetapi.exceptions.AssignmentDoesNotExistByName;
import com.brandocode.inscriptionsheetapi.models.bo.CareerBO;
import com.brandocode.inscriptionsheetapi.services.CareerService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

@ExtendWith(MockitoExtension.class)
public class CareerControllerTest {

    @InjectMocks
    CareerController careerController;

    @Mock
    ApiService apiService;

    @Mock
    CareerService careerService;


    @Test
    void itShouldAssignThreeAssignmentsToACareerAndSaveItTest() throws AssignmentDoesNotExistByName {
        //given
        CareerTO career = TestUtils.getCareerTO();
        //when
        Mockito.doNothing().when(apiService).setAssignmentList(career, "firstAssignment", "secondAssignment", "thirdAssignment");
        Mockito.doNothing().when(careerService).saveCareer(any(CareerBO.class));
        //then
        ResponseEntity<?> result = careerController.createCareer(career, "firstAssignment", "secondAssignment", "thirdAssignment");
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void itShouldThrowWhenNoAssignmentIsFoundWithGivenAssignmentCodeTest() throws AssignmentDoesNotExistByName {
        //given
        CareerTO career = TestUtils.getCareerTO();
        //when
        Mockito.doThrow(new AssignmentDoesNotExistByName("firstAssignment"))
                .when(apiService).setAssignmentList(career, "firstAssignment", "secondAssignment", "thirdAssignment");
        //then
        ResponseEntity<?> result = careerController.createCareer(career, "firstAssignment", "secondAssignment", "thirdAssignment");
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    void itShouldThrowWhenCareerAlreadyExistsTest() throws AssignmentDoesNotExistByName {
        //given
        CareerTO career = TestUtils.getCareerTO();
        //when
        Mockito.doNothing()
                .when(apiService).setAssignmentList(career, "firstAssignment", "secondAssignment", "thirdAssignment");
        Mockito.doThrow(new EntityExistsException()).when(careerService).saveCareer(any(CareerBO.class));
        //then
        ResponseEntity<?> result = careerController.createCareer(career, "firstAssignment", "secondAssignment", "thirdAssignment");
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    void itShouldFindCareerByCareerCodeTest(){
        //given
        CareerTO career = TestUtils.getCareerTO();
        //when
        Mockito.doReturn(CareerApiMapper.convertTOToBO(career)).when(careerService).findCareerByCareerCode(anyString());
        //then
        ResponseEntity<?> result = careerController.findCareerByCareerCode(anyString());
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void itShouldThrowWhenThereIsNoCareerWithProvidedCareerCodeTest(){
        //given
        CareerTO career = TestUtils.getCareerTO();
        //when
        Mockito.doThrow(new EntityNotFoundException()).when(careerService).findCareerByCareerCode(anyString());
        //then
        ResponseEntity<?> result = careerController.findCareerByCareerCode("careerCode");
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }


    @Test
    void itShouldFindAllCareersTest(){
        //given
        CareerTO career = TestUtils.getCareerTO();
        List<CareerTO> careers = List.of(career);
        //when
        Mockito.doReturn(CareerApiMapper.convertTOListToBOList(careers)).when(careerService).findAllCareers();
        //then
        ResponseEntity<?> result = careerController.findAllCareers();
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void itShouldThrowWhenThereAreNoCareersToFindTest(){
        //when
        Mockito.doThrow(new EntityNotFoundException()).when(careerService).findAllCareers();
        //then
        ResponseEntity<?> result = careerController.findAllCareers();
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    void itShouldUpdateCareerTest(){
        //given
        CareerTO career = TestUtils.getCareerTO();
        //when
        Mockito.doNothing().when(careerService).updateCareer(CareerApiMapper.convertTOToBO(career), "careerCode");
        //then
        ResponseEntity<?> result = careerController.updateCareer(career, "careerCode");
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void itShouldThrowWhenThereIsNoCareerToUpdateTest(){
        //given
        CareerTO career = TestUtils.getCareerTO();
        //when
        Mockito.doThrow(new EntityNotFoundException()).when(careerService).updateCareer(CareerApiMapper.convertTOToBO(career), "careerCode");
        //then
        ResponseEntity<?> result = careerController.updateCareer(career, "careerCode");
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    void itShouldDeleteCareerTest(){
        //when
        Mockito.doNothing().when(careerService).deleteCareer("careerCode");
        //then
        ResponseEntity<?> result = careerController.deleteCareer("careerCode");
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void itShouldThrowWhenThereIsNoCareerToDeleteTest(){
        //when
        Mockito.doThrow(new EntityNotFoundException()).when(careerService).deleteCareer("careerCode");
        //then
        ResponseEntity<?> result = careerController.deleteCareer("careerCode");
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }
}
