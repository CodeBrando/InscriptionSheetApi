package com.brandocode.inscriptionsheetapi.services;

import com.brandocode.inscriptionsheetapi.TestUtils;
import com.brandocode.inscriptionsheetapi.models.de.CareerDE;
import com.brandocode.inscriptionsheetapi.models.de.StudentDE;
import com.brandocode.inscriptionsheetapi.models.mappers.CareerMapper;
import com.brandocode.inscriptionsheetapi.models.mappers.StudentMapper;
import com.brandocode.inscriptionsheetapi.repo.IStudentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
@ExtendWith(MockitoExtension.class)
class StudentServiceTest {


    @Mock IStudentRepository studentRepository;

    @InjectMocks StudentService underTest;


    @Test
    void itShouldFindAllStudentsTest() {
        //when
        underTest.findAllStudents();
        //then
        verify(studentRepository).findAll();
    }

    @Test
    void itShouldSaveStudentTest() {
        //given
        StudentDE student = StudentMapper.convertBOToDE(TestUtils.getStudentBO());
        //when
        underTest.saveStudent(StudentMapper.convertDEToBO(student));
        //then
        ArgumentCaptor<StudentDE> studentDEArgumentCaptor =
                ArgumentCaptor.forClass(StudentDE.class);

        verify(studentRepository).save(studentDEArgumentCaptor.capture());

        StudentDE capturedStudent = studentDEArgumentCaptor.getValue();
        capturedStudent.setStudentCode("studentCode");

        assertThat(capturedStudent).isEqualTo(student);
    }

    @Test
    void itShouldThrowWhenStudentExistsTest() {
        //given
        StudentDE student = StudentMapper.convertBOToDE(TestUtils.getStudentBO());

        given(studentRepository.existsByStudentCode(student.getStudentCode())).willReturn(true);
        //when
        //then
        assertThatThrownBy(() -> underTest.saveStudent(StudentMapper.convertDEToBO(student)))
                .isInstanceOf(EntityExistsException.class)
                .hasMessageContaining("Student with student code " + student.getStudentCode() + " already exists");

    }

    @Test
    void itShouldFindStudentByStudentCodeIfStudentCodeIsCorrectAndStudentExistsTest() {
        //given
        StudentDE student = StudentMapper.convertBOToDE(TestUtils.getStudentBO());
        //when
        given(studentRepository.findByStudentCode(student.getStudentCode())).willReturn(Optional.of(student));
        underTest.findStudentByStudentCode(student.getStudentCode());
        //then
        ArgumentCaptor<String> studentCodeArgumentCaptor = ArgumentCaptor.forClass(String.class);
        verify(studentRepository).findByStudentCode(studentCodeArgumentCaptor.capture());
        String capturedStudentCode = studentCodeArgumentCaptor.getValue();
        assertThat(capturedStudentCode).isEqualTo(student.getStudentCode());
        verify(studentRepository).findByStudentCode(student.getStudentCode());
    }

    @Test
    void itShouldThrowWhenStudentDoesNotExistTest() {
        //given
        StudentDE student = StudentMapper.convertBOToDE(TestUtils.getStudentBO());
        //when
        //then
        assertThrows(EntityNotFoundException.class, ()-> underTest.findStudentByStudentCode(student.getStudentCode()),
                "Student with student code " +student.getStudentCode()+ " does not exist.");
    }


    @Test
    void canItUpdateStudentWithCorrectIdAndStudentCodeFromPreviouslySavedStudentTest() {
        //given
        StudentDE student = StudentMapper.convertBOToDE(TestUtils.getStudentBO());
        StudentDE studentToUpdate = StudentMapper.convertBOToDE(TestUtils.getStudentBO());
        studentToUpdate.setStudentCode("studentCodeTest");
        String studentCode = "studentCodeTest";
        //when
        given(studentRepository.findByStudentCode(studentCode)).willReturn(Optional.of(studentToUpdate));
        underTest.findStudentByStudentCode(studentCode);
        student.setId(studentToUpdate.getId());
        student.setStudentCode(studentCode);
        underTest.saveStudent(StudentMapper.convertDEToBO(student));
        //then
        assertThat(studentToUpdate.getId()).isEqualTo(student.getId());
        assertThat(studentToUpdate.getStudentCode()).isEqualTo(student.getStudentCode());
        verify(studentRepository).findByStudentCode(studentCode);
        verify(studentRepository).save(student);
    }

    @Test
    void itShouldDeleteStudentTest() {
        //given
        StudentDE student  = StudentMapper.convertBOToDE(TestUtils.getStudentBO());
        //when
        given(studentRepository.findByStudentCode(student.getStudentCode())).willReturn(Optional.of(student));
        underTest.deleteStudent(student.getStudentCode());
        //then
        verify(studentRepository).delete(student);
    }
}