package com.brandocode.inscriptionsheetapi.services;

import com.brandocode.inscriptionsheetapi.TestUtils;
import com.brandocode.inscriptionsheetapi.models.de.AssignmentDE;
import com.brandocode.inscriptionsheetapi.models.mappers.AssignmentMapper;
import com.brandocode.inscriptionsheetapi.repo.IAssignmentRepository;
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
public class AssignmentServiceTest {

    @Mock
    IAssignmentRepository assignmentRepository;

    @InjectMocks
    AssignmentService underTest;


    @Test
    void itShouldFindAllAssignmentsTest() {
        //when
        underTest.findAllAssignments();
        //then
        verify(assignmentRepository).findAll();
    }

    @Test
    void itShouldSaveAssignmentTest() {
        //given
        AssignmentDE assignment = AssignmentMapper.convertBOToDE(TestUtils.getAssignmentBO());
        //when
        underTest.saveAssignment(AssignmentMapper.convertDEToBO(assignment));
        //then
        ArgumentCaptor<AssignmentDE> assignmentDEArgumentCaptor =
                ArgumentCaptor.forClass(AssignmentDE.class);

        verify(assignmentRepository).save(assignmentDEArgumentCaptor.capture());

        AssignmentDE capturedAssignment = assignmentDEArgumentCaptor.getValue();
        capturedAssignment.setAssignmentCode("assignmentCode");

        assertThat(capturedAssignment).isEqualTo(assignment);
    }

    @Test
    void itShouldThrowWhenAssignmentExistsTest() {
        //given
        AssignmentDE assignment = AssignmentMapper.convertBOToDE(TestUtils.getAssignmentBO());

        given(assignmentRepository.existsByAssignmentCode(assignment.getAssignmentCode())).willReturn(true);
        //when
        //then
        assertThatThrownBy(() -> underTest.saveAssignment(AssignmentMapper.convertDEToBO(assignment)))
                .isInstanceOf(EntityExistsException.class)
                .hasMessageContaining("Assignment with assignment code " + assignment.getAssignmentCode() + " already exists");

    }

    @Test
    void itShouldFindAssignmentByAssignmentCodeIfAssignmentCodeIsCorrectAndAssignmentExistsTest() {
        //given
        AssignmentDE assignment = AssignmentMapper.convertBOToDE(TestUtils.getAssignmentBO());
        //when
        given(assignmentRepository.findByAssignmentCode(assignment.getAssignmentCode())).willReturn(Optional.of(assignment));
        underTest.findAssignmentByAssignmentCode(assignment.getAssignmentCode());
        //then
        ArgumentCaptor<String> assignmentCodeArgumentCaptor = ArgumentCaptor.forClass(String.class);
        verify(assignmentRepository).findByAssignmentCode(assignmentCodeArgumentCaptor.capture());
        String capturedAssignmentCode = assignmentCodeArgumentCaptor.getValue();
        assertThat(capturedAssignmentCode).isEqualTo(assignment.getAssignmentCode());
        verify(assignmentRepository).findByAssignmentCode(assignment.getAssignmentCode());
    }

    @Test
    void itShouldFindAssignmentByNameTest(){
        //given
        AssignmentDE assignment = TestUtils.getAssignmentDE();
        //when
        given(assignmentRepository.findByName(assignment.getName())).willReturn(Optional.of(assignment));
        underTest.findAssignmentByName(assignment.getName());
        //then
        ArgumentCaptor<String> assignmentNameArgumentCaptor = ArgumentCaptor.forClass(String.class);
        verify(assignmentRepository).findByName(assignmentNameArgumentCaptor.capture());
        String capturedName = assignmentNameArgumentCaptor.getValue();
        assertThat(capturedName).isEqualTo(assignment.getName());
        verify(assignmentRepository).findByName(assignment.getName());
    }

    @Test
    void itShouldThrowWhenAssignmentWithProvidedAssignmentCodeDoesNotExistTest() {
        //given
        AssignmentDE assignment = AssignmentMapper.convertBOToDE(TestUtils.getAssignmentBO());
        //when
        //then
        assertThrows(EntityNotFoundException.class, ()-> underTest.findAssignmentByAssignmentCode(assignment.getAssignmentCode()),
                "Assignment with assignment code " +assignment.getAssignmentCode()+ " does not exist.");
    }

    @Test
    void itShouldThrowWhenAssignmentWithProvidedNameDoesNotExistTest() {
        //given
        AssignmentDE assignment = TestUtils.getAssignmentDE();
        //when
        //then
        assertThrows(EntityNotFoundException.class, ()-> underTest.findAssignmentByName(assignment.getName()),
                "Assignment with name " +assignment.getName()+ " does not exist.");
    }

    @Test
    void canItUpdateAssignmentWithCorrectIdAndAssignmentCodeFromPreviouslySavedAssignmentTest() {
        //given
        AssignmentDE assignment = AssignmentMapper.convertBOToDE(TestUtils.getAssignmentBO());
        AssignmentDE assignmentToUpdate = AssignmentMapper.convertBOToDE(TestUtils.getAssignmentBO());
        assignmentToUpdate.setAssignmentCode("assignmentCodeTest");
        String assignmentCode = "assignmentCodeTest";
        //when
        given(assignmentRepository.findByAssignmentCode(assignmentCode)).willReturn(Optional.of(assignmentToUpdate));
        underTest.findAssignmentByAssignmentCode(assignmentCode);
        assignment.setId(assignmentToUpdate.getId());
        assignment.setAssignmentCode(assignmentCode);
        underTest.saveAssignment(AssignmentMapper.convertDEToBO(assignment));
        //then
        assertThat(assignmentToUpdate.getId()).isEqualTo(assignment.getId());
        assertThat(assignmentToUpdate.getAssignmentCode()).isEqualTo(assignment.getAssignmentCode());
        verify(assignmentRepository).findByAssignmentCode(assignmentCode);
        verify(assignmentRepository).save(assignment);
    }

    @Test
    void itShouldDeleteAssignmentTest() {
        //given
        AssignmentDE assignment  = AssignmentMapper.convertBOToDE(TestUtils.getAssignmentBO());
        //when
        given(assignmentRepository.findByAssignmentCode(assignment.getAssignmentCode())).willReturn(Optional.of(assignment));
        underTest.deleteAssignment(assignment.getAssignmentCode());
        //then
        verify(assignmentRepository).delete(assignment);
    }
}
