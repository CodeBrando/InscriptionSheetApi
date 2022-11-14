package com.brandocode.inscriptionsheetapi.controllers.mappers;

import com.brandocode.inscriptionsheetapi.TestUtils;
import com.brandocode.inscriptionsheetapi.controllers.to.StudentTO;
import com.brandocode.inscriptionsheetapi.models.bo.StudentBO;
import com.brandocode.inscriptionsheetapi.models.de.StudentDE;
import com.brandocode.inscriptionsheetapi.models.mappers.CareerMapper;
import com.brandocode.inscriptionsheetapi.models.mappers.StudentMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class StudentApiMapperTest {

    StudentBO expectedBO;

    StudentTO expectedTO;

    @BeforeAll
    public void setUp(){
        expectedBO = TestUtils.getStudentBO();
        expectedTO = TestUtils.getStudentTO();
    }

    @Test
    void convertBOToTOTest(){
        StudentTO resultTO = StudentApiMapper.convertBOToTO(expectedBO);
        assertEquals(expectedBO.getId(), resultTO.getId());
        assertEquals(expectedBO.getName(), resultTO.getName());
        assertEquals(expectedBO.getLastName(), resultTO.getLastName());
        assertEquals(expectedBO.getEmail(), resultTO.getEmail());
        assertEquals(expectedBO.getPhoneNumber(), resultTO.getPhoneNumber());
        assertEquals(expectedBO.getStudentStatus(), resultTO.getStudentStatus());
        assertEquals(expectedBO.getStudentCode(), resultTO.getStudentCode());
        //assertEquals(expectedBO.getCareer(), CareerApiMapper.convertTOToBO(resultTO.getCareer()));
    }

    @Test
    void convertTOToBOTest(){
        StudentBO resultBO = StudentApiMapper.convertTOToBO(expectedTO);
        assertEquals(expectedTO.getId(), resultBO.getId());
        assertEquals(expectedTO.getName(), resultBO.getName());
        assertEquals(expectedTO.getLastName(), resultBO.getLastName());
        assertEquals(expectedTO.getEmail(), resultBO.getEmail());
        assertEquals(expectedTO.getPhoneNumber(), resultBO.getPhoneNumber());
        assertEquals(expectedTO.getStudentStatus(), resultBO.getStudentStatus());
        assertEquals(expectedTO.getStudentCode(), resultBO.getStudentCode());
        assertEquals(expectedTO.getCareer(), CareerApiMapper.convertBOToTO(resultBO.getCareer()));
    }

    @Test
    void convertBOListToTOListTest(){
        List<StudentTO> resultsTO = StudentApiMapper.convertBOListToTOList(List.of(expectedBO));
        assertEquals(List.of(expectedTO), resultsTO);
    }

}

