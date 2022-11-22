package com.brandocode.inscriptionsheetapi.controllers;

import com.brandocode.inscriptionsheetapi.TestUtils;
import com.brandocode.inscriptionsheetapi.controllers.mappers.AssignmentApiMapper;
import com.brandocode.inscriptionsheetapi.controllers.mappers.StudentApiMapper;
import com.brandocode.inscriptionsheetapi.controllers.services.ApiService;
import com.brandocode.inscriptionsheetapi.controllers.to.AssignmentTO;
import com.brandocode.inscriptionsheetapi.controllers.to.StudentTO;
import com.brandocode.inscriptionsheetapi.models.bo.AssignmentBO;
import com.brandocode.inscriptionsheetapi.models.bo.StudentBO;
import com.brandocode.inscriptionsheetapi.services.AssignmentService;
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
public class AssignmentControllerTest {

    @InjectMocks
    AssignmentController assignmentController;

    @Mock
    AssignmentService assignmentService;

    @Test
    void itShouldCreateANewAssignmentTest(){
        //given
        AssignmentTO assignment = TestUtils.getAssignmentTO();
        //when
        Mockito.doNothing().when(assignmentService).saveAssignment(any(AssignmentBO.class));
        //then
        ResponseEntity<?> result = assignmentController.createAssignment(assignment);
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }

    @Test
    void itShouldThrowWhenAssignmentAlreadyExistsTest(){
        //given
        AssignmentTO assignment = TestUtils.getAssignmentTO();
        //when
        Mockito.doThrow(new EntityExistsException()).when(assignmentService).saveAssignment(any(AssignmentBO.class));
        //then
        ResponseEntity<?> result = assignmentController.createAssignment(assignment);
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    void itShouldFindAssignmentByAssignmentCodeTest(){
        //given
        AssignmentTO assignment = TestUtils.getAssignmentTO();
        //when
        Mockito.doReturn(AssignmentApiMapper.convertTOToBO(assignment)).when(assignmentService).findAssignmentByAssignmentCode(anyString());
        //then
        ResponseEntity<?> result = assignmentController.findAssignmentByAssignmentCode(anyString());
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void itShouldThrowWhenThereIsNoAssignmentWithProvidedAssignmentCodeTest(){
        //given
        AssignmentTO assignment = TestUtils.getAssignmentTO();
        //when
        Mockito.doThrow(new EntityNotFoundException()).when(assignmentService).findAssignmentByAssignmentCode(anyString());
        //then
        ResponseEntity<?> result = assignmentController.findAssignmentByAssignmentCode("assignmentCode");
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    void itShouldFindAllAssignmentsTest(){
        //given
        AssignmentTO assignment = TestUtils.getAssignmentTO();
        List<AssignmentTO> assignments = List.of(assignment);
        //when
        Mockito.doReturn(AssignmentApiMapper.convertTOListToBOList(assignments)).when(assignmentService).findAllAssignments();
        //then
        ResponseEntity<?> result = assignmentController.findAllAssignments();
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void itShouldThrowWhenThereAreNoAssignmentsToFindTest(){
        //when
        Mockito.doThrow(new EntityNotFoundException()).when(assignmentService).findAllAssignments();
        //then
        ResponseEntity<?> result = assignmentController.findAllAssignments();
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    void itShouldUpdateAssignmentTest(){
        //given
        AssignmentTO assignment = TestUtils.getAssignmentTO();
        //when
        Mockito.doNothing().when(assignmentService).updateAssignment(AssignmentApiMapper.convertTOToBO(assignment), "assignmentCode");
        //then
        ResponseEntity<?> result = assignmentController.updateAssignment(assignment, "assignmentCode");
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void itShouldThrowWhenThereIsNoAssignmentToUpdateTest(){
        //given
        AssignmentTO assignment = TestUtils.getAssignmentTO();
        //when
        Mockito.doThrow(new EntityNotFoundException()).when(assignmentService).updateAssignment(AssignmentApiMapper.convertTOToBO(assignment), "assignmentCode");
        //then
        ResponseEntity<?> result = assignmentController.updateAssignment(assignment, "assignmentCode");
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    void itShouldDeleteAssignmentTest(){
        //when
        Mockito.doNothing().when(assignmentService).deleteAssignment("assignmentCode");
        //then
        ResponseEntity<?> result = assignmentController.deleteAssignment("assignmentCode");
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void itShouldThrowWhenThereIsNoAssignmentToDeleteTest(){
        //when
        Mockito.doThrow(new EntityNotFoundException()).when(assignmentService).deleteAssignment("assignmentCode");
        //then
        ResponseEntity<?> result = assignmentController.deleteAssignment("assignmentCode");
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }
}
