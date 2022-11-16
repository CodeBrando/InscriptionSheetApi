package com.brandocode.inscriptionsheetapi.services;

import com.brandocode.inscriptionsheetapi.TestUtils;
import com.brandocode.inscriptionsheetapi.models.de.CareerDE;
import com.brandocode.inscriptionsheetapi.models.de.StudentDE;
import com.brandocode.inscriptionsheetapi.models.mappers.CareerMapper;
import com.brandocode.inscriptionsheetapi.models.mappers.StudentMapper;
import com.brandocode.inscriptionsheetapi.repo.ICareerRepository;
import org.junit.jupiter.api.Disabled;
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
public class CareerServiceTest {

    @Mock
    ICareerRepository careerRepository;

    @InjectMocks
    CareerService underTest;

    @Test
    void itShouldFindAllCareersTest() {
        //when
        underTest.findAllCareers();
        //then
        verify(careerRepository).findAll();
    }

    @Test
    void itShouldSaveCareerTest() {
        //given
        CareerDE career = CareerMapper.convertBOToDE(TestUtils.getCareerBO());
        //when
        underTest.saveCareer(CareerMapper.convertDEToBO(career));
        //then
        ArgumentCaptor<CareerDE> careerDEArgumentCaptor =
                ArgumentCaptor.forClass(CareerDE.class);

        verify(careerRepository).save(careerDEArgumentCaptor.capture());

        CareerDE capturedCareer = careerDEArgumentCaptor.getValue();
        capturedCareer.setCareerCode("careerCode");

        assertThat(capturedCareer).isEqualTo(career);
    }

    @Test
    void itShouldThrowWhenCareerExistsTest() {
        //given
        CareerDE career = CareerMapper.convertBOToDE(TestUtils.getCareerBO());

        given(careerRepository.existsByCareerCode(career.getCareerCode())).willReturn(true);
        //when
        //then
        assertThatThrownBy(() -> underTest.saveCareer(CareerMapper.convertDEToBO(career)))
                .isInstanceOf(EntityExistsException.class)
                .hasMessageContaining("Career with code " + career.getCareerCode() + " already exists");

    }

    @Test
    void itShouldFindCareerByCareerCodeIfCareerCodeIsCorrectAndCareerExistsTest() {
        //given
        CareerDE career = CareerMapper.convertBOToDE(TestUtils.getCareerBO());
        //when
        given(careerRepository.findByCareerCode(career.getCareerCode())).willReturn(Optional.of(career));
        underTest.findCareerByCareerCode(career.getCareerCode());
        //then
        ArgumentCaptor<String> careerCodeArgumentCaptor = ArgumentCaptor.forClass(String.class);
        verify(careerRepository).findByCareerCode(careerCodeArgumentCaptor.capture());
        String capturedCareerCode = careerCodeArgumentCaptor.getValue();
        assertThat(capturedCareerCode).isEqualTo(career.getCareerCode());
        verify(careerRepository).findByCareerCode(career.getCareerCode());
    }

    @Test
    void itShouldThrowWhenCareerDoesNotExistTest() {
        //given
        CareerDE career = CareerMapper.convertBOToDE(TestUtils.getCareerBO());
        //when
        //then
        assertThrows(EntityNotFoundException.class, ()-> underTest.findCareerByCareerCode(career.getCareerCode()),
                "Student with student code " +career.getCareerCode()+ " does not exist.");
    }

    @Test
    void canItUpdateCareerWithCorrectIdAndCareerCodeFromPreviouslySavedCareerTest() {
        //given
        CareerDE career = CareerMapper.convertBOToDE(TestUtils.getCareerBO());
        CareerDE careerToUpdate = CareerMapper.convertBOToDE(TestUtils.getCareerBO());
        careerToUpdate.setCareerCode("careerCodeTest");
        String careerCode = "careerCodeTest";
        //when
        given(careerRepository.findByCareerCode(careerCode)).willReturn(Optional.of(careerToUpdate));
        underTest.findCareerByCareerCode(careerCode);
        career.setId(careerToUpdate.getId());
        career.setCareerCode(careerCode);
        underTest.saveCareer(CareerMapper.convertDEToBO(career));
        //then
        assertThat(careerToUpdate.getId()).isEqualTo(career.getId());
        assertThat(careerToUpdate.getCareerCode()).isEqualTo(career.getCareerCode());
        verify(careerRepository).findByCareerCode(careerCode);
        verify(careerRepository).save(career);
    }

    @Test
    void itShouldDeleteCareerTest() {
        //given
        CareerDE career = CareerMapper.convertBOToDE(TestUtils.getCareerBO());
        //when
        given(careerRepository.findByCareerCode(career.getCareerCode())).willReturn(Optional.of(career));
        underTest.deleteCareer(career.getCareerCode());
        //then
        verify(careerRepository).delete(career);
    }

}
