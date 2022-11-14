package com.brandocode.inscriptionsheetapi.models.mappers;

import com.brandocode.inscriptionsheetapi.TestUtils;
import com.brandocode.inscriptionsheetapi.models.bo.CareerBO;
import com.brandocode.inscriptionsheetapi.models.bo.StudentBO;
import com.brandocode.inscriptionsheetapi.models.de.CareerDE;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CareerMapperTest {

    CareerBO expectedBO;

    CareerDE expectedDE;



    @BeforeAll
    public void setUp(){
        expectedBO = TestUtils.getCareerBO();
        expectedDE = TestUtils.getCareerDE();
    }

    @Test
    void convertBOToDETest(){
        CareerDE resultDE = CareerMapper.convertBOToDE(expectedBO);
        assertEquals(expectedBO.getId(), resultDE.getId());
        assertEquals(expectedBO.getName(), resultDE.getName());
        assertEquals(expectedBO.getCareerCode(), resultDE.getCareerCode());
        assertEquals(expectedBO.getAssignments(), AssignmentMapper.convertDEListToBOList(resultDE.getAssignments()));
    }

    @Test
    void convertDEToBOTest(){
        CareerBO resultBO = CareerMapper.convertDEToBO(expectedDE);
        assertEquals(expectedDE.getId(), resultBO.getId());
        assertEquals(expectedDE.getName(), resultBO.getName());
        assertEquals(expectedDE.getCareerCode(), resultBO.getCareerCode());
        assertEquals(expectedDE.getAssignments(), AssignmentMapper.convertBOListToDEList(resultBO.getAssignments()));
    }

    @Test
    void convertDEListToBOListTest(){
        List<CareerBO> resultsBO = CareerMapper.convertDEListToBOList(List.of(expectedDE));
        assertEquals(List.of(expectedBO), resultsBO);
    }

    @Test
    void convertBOListToDEListTest(){
        List<CareerDE> resultsDE = CareerMapper.convertBOListToDEList(List.of(expectedBO));
        assertEquals(List.of(expectedDE), resultsDE);
    }

}
