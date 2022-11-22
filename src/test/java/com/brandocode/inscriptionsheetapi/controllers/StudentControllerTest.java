package com.brandocode.inscriptionsheetapi.controllers;

import com.brandocode.inscriptionsheetapi.TestUtils;
import com.brandocode.inscriptionsheetapi.controllers.mappers.StudentApiMapper;
import com.brandocode.inscriptionsheetapi.controllers.services.ApiService;
import com.brandocode.inscriptionsheetapi.controllers.to.StudentTO;
import com.brandocode.inscriptionsheetapi.models.bo.StudentBO;
import com.brandocode.inscriptionsheetapi.services.StudentService;
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
public class StudentControllerTest {

    @InjectMocks
    StudentController studentController;

    @Mock
    ApiService apiService;

    @Mock
    StudentService studentService;


    @Test
    void itShouldAssignACareerToAStudentAndSaveItTest(){
        //given
        StudentTO student = TestUtils.getStudentTO();
        //when
        Mockito.doNothing().when(apiService).assignCareer(any(StudentTO.class), anyString());
        Mockito.doNothing().when(studentService).saveStudent(any(StudentBO.class));
        //then
        ResponseEntity<?> result = studentController.createStudent(student, "studentCode");
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }

    @Test
    void itShouldThrowWhenAssignCareerIsNotImplementedCorrectlyTest(){
        //given
        StudentTO student = TestUtils.getStudentTO();
        //when
        Mockito.doThrow(new EntityNotFoundException()).when(apiService).assignCareer(any(StudentTO.class), anyString());
        //then
        ResponseEntity<?> result = studentController.createStudent(student, "studentCode");
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    void itShouldThrowWhenStudentAlreadyExistsTest(){
        //given
        StudentTO student = TestUtils.getStudentTO();
        //when
        Mockito.doNothing().when(apiService).assignCareer(any(StudentTO.class), anyString());
        Mockito.doThrow(new EntityExistsException()).when(studentService).saveStudent(any(StudentBO.class));
        //then
        ResponseEntity<?> result = studentController.createStudent(student, "studentCode");
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    void itShouldFindStudentByStudentCodeTest(){
        //given
        StudentTO student = TestUtils.getStudentTO();
        //when
        Mockito.doReturn(StudentApiMapper.convertTOToBO(student)).when(studentService).findStudentByStudentCode(anyString());
        //then
        ResponseEntity<?> result = studentController.findStudentByStudentCode(anyString());
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void itShouldThrowWhenThereIsNoStudentWithProvidedStudentCodeTest(){
        //given
        StudentTO student = TestUtils.getStudentTO();
        //when
        Mockito.doThrow(new EntityNotFoundException()).when(studentService).findStudentByStudentCode(anyString());
        //then
        ResponseEntity<?> result = studentController.findStudentByStudentCode("studentCode");
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }


    @Test
    void itShouldFindAllStudentsTest(){
        //given
        StudentTO student = TestUtils.getStudentTO();
        List<StudentTO> students = List.of(student);
        //when
        Mockito.doReturn(StudentApiMapper.convertTOListToBOList(students)).when(studentService).findAllStudents();
        //then
        ResponseEntity<?> result = studentController.findAllStudents();
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void itShouldThrowWhenThereAreNoStudentsToFindTest(){
        //when
        Mockito.doThrow(new EntityNotFoundException()).when(studentService).findAllStudents();
        //then
        ResponseEntity<?> result = studentController.findAllStudents();
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    void itShouldUpdateStudentTest(){
        //given
        StudentTO student = TestUtils.getStudentTO();
        //when
        Mockito.doNothing().when(studentService).updateStudent(StudentApiMapper.convertTOToBO(student), "studentCode");
        //then
        ResponseEntity<?> result = studentController.updateStudent(student, "studentCode");
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void itShouldThrowWhenThereIsNoStudentToUpdateTest(){
        //given
        StudentTO student = TestUtils.getStudentTO();
        //when
        Mockito.doThrow(new EntityNotFoundException()).when(studentService).updateStudent(StudentApiMapper.convertTOToBO(student), "studentCode");
        //then
        ResponseEntity<?> result = studentController.updateStudent(student, "studentCode");
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    void itShouldDeleteStudentTest(){
        //when
        Mockito.doNothing().when(studentService).deleteStudent("studentCode");
        //then
        ResponseEntity<?> result = studentController.deleteStudent("studentCode");
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void itShouldThrowWhenThereIsNoStudentToDeleteTest(){
        //when
        Mockito.doThrow(new EntityNotFoundException()).when(studentService).deleteStudent("studentCode");
        //then
        ResponseEntity<?> result = studentController.deleteStudent("studentCode");
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }
}
