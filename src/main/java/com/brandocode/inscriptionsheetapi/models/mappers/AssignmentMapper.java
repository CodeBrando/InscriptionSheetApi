package com.brandocode.inscriptionsheetapi.models.mappers;

import com.brandocode.inscriptionsheetapi.models.bo.AssignmentBO;
import com.brandocode.inscriptionsheetapi.models.de.AssignmentDE;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AssignmentMapper {
    public static AssignmentDE convertBOToDE(AssignmentBO assignmentBO){
        return AssignmentDE.builder()
                .id(Objects.isNull(assignmentBO.getId()) ? null : assignmentBO.getId())
                .name(assignmentBO.getName())
                .assignmentCode(assignmentBO.getAssignmentCode())
                .build();
    }

    public static AssignmentBO convertDEToBO(AssignmentDE assignmentDE){
        return AssignmentBO.builder()
                .id(Objects.isNull(assignmentDE.getId()) ? null : assignmentDE.getId())
                .name(assignmentDE.getName())
                .assignmentCode(assignmentDE.getAssignmentCode())
                .build();
    }

    public static List<AssignmentBO> convertDEListToBOList(List<AssignmentDE> assignmentsDE){
        List<AssignmentBO> assignmentsBO = new ArrayList<>();
        assignmentsDE.forEach(assignmentDE -> assignmentsBO.add(convertDEToBO(assignmentDE)));
        return assignmentsBO;
    }

    public static List<AssignmentDE> convertBOListToDEList(List<AssignmentBO> assignmentsBO){
        List<AssignmentDE> assignmentsDE = new ArrayList<>();
        assignmentsBO.forEach(assignmentBO -> assignmentsDE.add(convertBOToDE(assignmentBO)));
        return assignmentsDE;
    }
}
