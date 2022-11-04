package com.brandocode.inscriptionsheetapi.controller.mappers;

import com.brandocode.inscriptionsheetapi.controller.to.AssignmentTO;
import com.brandocode.inscriptionsheetapi.models.bo.AssignmentBO;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AssignmentApiMapper {

    public static AssignmentTO convertBOToTO(AssignmentBO assignmentBO){
        return AssignmentTO.builder()
                .id(Objects.isNull(assignmentBO.getId()) ? null : assignmentBO.getId())
                .name(assignmentBO.getName())
                .assignmentCode(assignmentBO.getAssignmentCode())
                .build();
    }

    public static AssignmentBO convertTOToBO(AssignmentTO assignmentTO){
        return AssignmentBO.builder()
                .id(Objects.isNull(assignmentTO.getId()) ? null : assignmentTO.getId())
                .name(assignmentTO.getName())
                .build();
    }

    public static List<AssignmentTO> convertBOListToTOList(List<AssignmentBO> assignmentsBO){
        List<AssignmentTO> assignmentsTO = new ArrayList<>();
        assignmentsBO.forEach(assignmentBO -> assignmentsTO.add(convertBOToTO(assignmentBO)));
        return assignmentsTO;
    }
}
