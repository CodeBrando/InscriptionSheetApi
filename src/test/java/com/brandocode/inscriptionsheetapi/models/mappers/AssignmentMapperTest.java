package com.brandocode.inscriptionsheetapi.models.mappers;

import com.brandocode.inscriptionsheetapi.TestUtils;
import com.brandocode.inscriptionsheetapi.models.bo.AssignmentBO;
import com.brandocode.inscriptionsheetapi.models.bo.CareerBO;
import com.brandocode.inscriptionsheetapi.models.de.AssignmentDE;
import com.brandocode.inscriptionsheetapi.models.de.CareerDE;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AssignmentMapperTest {

    AssignmentDE expectedDE;

    AssignmentBO expectedBO;

    @BeforeAll
    public void setUp(){
        expectedBO = TestUtils.getAssignmentBO();
        expectedDE = TestUtils.getAssignmentDE();
    }

    @Test
    void convertBOToDETest(){
        AssignmentDE resultDE = AssignmentMapper.convertBOToDE(expectedBO);
        assertEquals(expectedBO.getId(), resultDE.getId());
        assertEquals(expectedBO.getName(), resultDE.getName());
        assertEquals(expectedBO.getAssignmentCode(), resultDE.getAssignmentCode());
    }


    @Test
    void convertDEToBOTest(){
        AssignmentBO resultBO = AssignmentMapper.convertDEToBO(expectedDE);
        assertEquals(expectedDE.getId(), resultBO.getId());
        assertEquals(expectedDE.getName(), resultBO.getName());
        assertEquals(expectedDE.getAssignmentCode(), resultBO.getAssignmentCode());
    }

    @Test
    void convertDEListToBOListTest(){
        List<AssignmentBO> resultsBO = AssignmentMapper.convertDEListToBOList(List.of(expectedDE));
        assertEquals(List.of(expectedBO), resultsBO);
    }

    @Test
    void convertBOListToDEListTest(){
        List<AssignmentDE> resultsDE = AssignmentMapper.convertBOListToDEList(List.of(expectedBO));
        assertEquals(List.of(expectedDE), resultsDE);
    }

}
