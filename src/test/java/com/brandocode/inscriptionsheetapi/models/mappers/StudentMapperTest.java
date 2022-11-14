package com.brandocode.inscriptionsheetapi.models.mappers;

import com.brandocode.inscriptionsheetapi.TestUtils;
import com.brandocode.inscriptionsheetapi.models.bo.StudentBO;
import com.brandocode.inscriptionsheetapi.models.de.StudentDE;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class StudentMapperTest {

    StudentBO expectedBO;

    StudentDE expectedDE;

    @BeforeAll
    public void setUp(){
        expectedBO = TestUtils.getStudentBO();
        expectedDE = TestUtils.getStudentDE();
    }

    @Test
    void convertBOToDETest(){
        StudentDE resultDE = StudentMapper.convertBOToDE(expectedBO);
        assertEquals(expectedBO.getId(), resultDE.getId());
        assertEquals(expectedBO.getName(), resultDE.getName());
        assertEquals(expectedBO.getLastName(), resultDE.getLastName());
        assertEquals(expectedBO.getEmail(), resultDE.getEmail());
        assertEquals(expectedBO.getPhoneNumber(), resultDE.getPhoneNumber());
        assertEquals(expectedBO.getStudentStatus(), resultDE.getStudentStatus());
        assertEquals(expectedBO.getStudentCode(), resultDE.getStudentCode());
        assertEquals(expectedBO.getCareer(), CareerMapper.convertDEToBO(resultDE.getCareer()));
    }

    @Test
    void convertDEToBOTest(){
        StudentBO resultBO = StudentMapper.convertDEToBO(expectedDE);
        assertEquals(expectedDE.getId(), resultBO.getId());
        assertEquals(expectedDE.getName(), resultBO.getName());
        assertEquals(expectedDE.getLastName(), resultBO.getLastName());
        assertEquals(expectedDE.getEmail(), resultBO.getEmail());
        assertEquals(expectedDE.getPhoneNumber(), resultBO.getPhoneNumber());
        assertEquals(expectedDE.getStudentStatus(), resultBO.getStudentStatus());
        assertEquals(expectedDE.getStudentCode(), resultBO.getStudentCode());
        assertEquals(expectedDE.getCareer(), CareerMapper.convertBOToDE(resultBO.getCareer()));
    }

    @Test
    void convertDEListToBOListTest(){
        List<StudentBO> resultsBO = StudentMapper.convertDEListToBOList(List.of(expectedDE));
        assertEquals(List.of(expectedBO),resultsBO);
    }

    @Test
    void convertBOListToDEListTest(){
        List<StudentDE> resultsDE = StudentMapper.convertBOListToDEList(List.of(expectedBO));
        assertEquals(List.of(expectedDE), resultsDE);
    }

}
