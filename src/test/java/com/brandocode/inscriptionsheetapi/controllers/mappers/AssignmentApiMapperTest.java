package com.brandocode.inscriptionsheetapi.controllers.mappers;

import com.brandocode.inscriptionsheetapi.TestUtils;
import com.brandocode.inscriptionsheetapi.controllers.to.AssignmentTO;
import com.brandocode.inscriptionsheetapi.models.bo.AssignmentBO;
import com.brandocode.inscriptionsheetapi.models.de.AssignmentDE;
import com.brandocode.inscriptionsheetapi.models.mappers.AssignmentMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AssignmentApiMapperTest {

    AssignmentTO expectedTO;

    AssignmentBO expectedBO;

    @BeforeAll
    public void setUp(){
        expectedBO = TestUtils.getAssignmentBO();
        expectedTO = TestUtils.getAssignmentTO();
    }

    @Test
    void convertBOToTOTest(){
        AssignmentTO resultTO = AssignmentApiMapper.convertBOToTO(expectedBO);
        assertEquals(expectedBO.getId(), resultTO.getId());
        assertEquals(expectedBO.getName(), resultTO.getName());
        assertEquals(expectedBO.getAssignmentCode(), resultTO.getAssignmentCode());
    }


    @Test
    void convertTOToBOTest(){
        AssignmentBO resultBO = AssignmentApiMapper.convertTOToBO(expectedTO);
        assertEquals(expectedTO.getId(), resultBO.getId());
        assertEquals(expectedTO.getName(), resultBO.getName());
        assertEquals(expectedTO.getAssignmentCode(), resultBO.getAssignmentCode());
    }

    @Test
    void convertTOListToBOListTest(){
        List<AssignmentBO> resultsBO = AssignmentApiMapper.convertTOListToBOList(List.of(expectedTO));
        assertEquals(List.of(expectedBO), resultsBO);
    }

    @Test
    void convertBOListToTOListTest(){
        List<AssignmentTO> resultsTO = AssignmentApiMapper.convertBOListToTOList(List.of(expectedBO));
        assertEquals(List.of(expectedTO), resultsTO);
    }
}
