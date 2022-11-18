package com.brandocode.inscriptionsheetapi;

import com.brandocode.inscriptionsheetapi.controllers.to.AssignmentTO;
import com.brandocode.inscriptionsheetapi.controllers.to.CareerTO;
import com.brandocode.inscriptionsheetapi.controllers.to.StudentTO;
import com.brandocode.inscriptionsheetapi.enums.StudentStatusEnum;
import com.brandocode.inscriptionsheetapi.models.bo.AssignmentBO;
import com.brandocode.inscriptionsheetapi.models.bo.CareerBO;
import com.brandocode.inscriptionsheetapi.models.bo.StudentBO;
import com.brandocode.inscriptionsheetapi.models.de.AssignmentDE;
import com.brandocode.inscriptionsheetapi.models.de.CareerDE;
import com.brandocode.inscriptionsheetapi.models.de.StudentDE;

import java.util.ArrayList;
import java.util.List;

public class TestUtils {

    public static StudentDE getStudentDE(){
        return StudentDE.builder()
                .id(1L)
                .name("Martin")
                .lastName("Brando")
                .email("martin.braga252@gmail.com")
                .phoneNumber("+541134479644")
                .studentStatus(StudentStatusEnum.ENROLLED.name())
                .studentCode("studentCode")
                .career(getCareerDE())
                .build();
    }

    public static StudentBO getStudentBO(){
        return StudentBO.builder()
                .id(1L)
                .name("Martin")
                .lastName("Brando")
                .email("martin.braga252@gmail.com")
                .phoneNumber("+541134479644")
                .studentStatus(StudentStatusEnum.ENROLLED.name())
                .studentCode("studentCode")
                .career(getCareerBO())
                .build();
    }

    public static StudentTO getStudentTO(){
        return StudentTO.builder()
                .id(1L)
                .name("Martin")
                .lastName("Brando")
                .email("martin.braga252@gmail.com")
                .phoneNumber("+541134479644")
                .studentStatus(StudentStatusEnum.ENROLLED.name())
                .studentCode("studentCode")
                .career(getCareerTO())
                .build();
    }

    public static CareerDE getCareerDE(){
        return CareerDE.builder()
                .id(1L)
                .name("Programming")
                .careerCode("careerCode")
                .assignments(List.of(getAssignmentDE()))
                .build();
    }

    public static CareerBO getCareerBO(){
        return CareerBO.builder()
                .id(1L)
                .name("Programming")
                .careerCode("careerCode")
                .assignments(List.of(getAssignmentBO()))
                .build();
    }

    public static CareerTO getCareerTO(){
        return CareerTO.builder()
                .id(1L)
                .name("Programming")
                .careerCode("careerCode")
                .assignments(List.of(getAssignmentTO()))
                .build();
    }

    public static AssignmentDE getAssignmentDE(){
        return AssignmentDE.builder()
                .id(1L)
                .name("First Assignment")
                .assignmentCode("assignmentCode")
                .build();
    }

    public static AssignmentBO getAssignmentBO(){
        return AssignmentBO.builder()
                .id(1L)
                .name("First Assignment")
                .assignmentCode("assignmentCode")
                .build();
    }

    public static AssignmentTO getAssignmentTO(){
        return AssignmentTO.builder()
                .id(1L)
                .name("First Assignment")
                .assignmentCode("assignmentCode")
                .build();
    }

    public static AssignmentDE getFirstAssignmentUtil(){
        return AssignmentDE.builder()
                .id(1L)
                .name("First assignment")
                .assignmentCode("firstAssignmentCode")
                .build();
    }

    public static AssignmentDE getSecondAssignmentUtil(){
        return AssignmentDE.builder()
                .id(2L)
                .name("Second assignment")
                .assignmentCode("secondAssignmentCode")
                .build();
    }

    public static AssignmentDE getThirdAssignmentUtil(){
        return AssignmentDE.builder()
                .id(3L)
                .name("Third assignment")
                .assignmentCode("thirdAssignmentCode")
                .build();
    }
}
