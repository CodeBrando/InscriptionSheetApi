package com.brandocode.inscriptionsheetapi.controllers.mappers;

import com.brandocode.inscriptionsheetapi.TestUtils;
import com.brandocode.inscriptionsheetapi.controllers.to.CareerTO;
import com.brandocode.inscriptionsheetapi.models.bo.CareerBO;
import com.brandocode.inscriptionsheetapi.models.de.CareerDE;
import com.brandocode.inscriptionsheetapi.models.mappers.AssignmentMapper;
import com.brandocode.inscriptionsheetapi.models.mappers.CareerMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CareerApiMapperTest {

    CareerBO expectedBO;

    CareerTO expectedTO;



    @BeforeAll
    public void setUp(){
        expectedBO = TestUtils.getCareerBO();
        expectedTO = TestUtils.getCareerTO();
    }

    @Test
    void convertBOToTOTest(){
        CareerTO resultTO = CareerApiMapper.convertBOToTO(expectedBO);
        assertEquals(expectedBO.getId(), resultTO.getId());
        assertEquals(expectedBO.getName(), resultTO.getName());
        assertEquals(expectedBO.getCareerCode(), resultTO.getCareerCode());
        assertEquals(expectedBO.getAssignments(), AssignmentApiMapper.convertTOListToBOList(resultTO.getAssignments()));
    }

    @Test
    void convertTOToBOTest(){
        CareerBO resultBO = CareerApiMapper.convertTOToBO(expectedTO);
        assertEquals(expectedTO.getId(), resultBO.getId());
        assertEquals(expectedTO.getName(), resultBO.getName());
        assertEquals(expectedTO.getCareerCode(), resultBO.getCareerCode());
        assertEquals(expectedTO.getAssignments(), AssignmentApiMapper.convertBOListToTOList(resultBO.getAssignments()));
    }

    @Test
    void convertTOListToBOListTest(){
        List<CareerBO> resultsBO = CareerApiMapper.convertTOListToBOList(List.of(expectedTO));
        assertEquals(List.of(expectedBO), resultsBO);
    }

    @Test
    void convertBOListToTOListTest(){
        List<CareerTO> resultsTO = CareerApiMapper.convertBOListToTOList(List.of(expectedBO));
        assertEquals(List.of(expectedTO), resultsTO);
    }
}
