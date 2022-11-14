package com.brandocode.inscriptionsheetapi.controllers.mappers;

import com.brandocode.inscriptionsheetapi.controllers.to.StudentTO;
import com.brandocode.inscriptionsheetapi.models.bo.StudentBO;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class StudentApiMapper {

    public static StudentTO convertBOToTO(StudentBO studentBO){
        return StudentTO.builder()
                .id(Objects.isNull(studentBO.getId()) ? null : studentBO.getId())
                .name(studentBO.getName())
                .lastName(studentBO.getLastName())
                .email(studentBO.getEmail())
                .phoneNumber(studentBO.getPhoneNumber())
                .studentStatus(studentBO.getStudentStatus())
                .studentCode(studentBO.getStudentCode())
                .career(CareerApiMapper.convertBOToTO(studentBO.getCareer()))
                .build();
    }

    public static StudentBO convertTOToBO(StudentTO studentTO){
        return StudentBO.builder()
                .id(Objects.isNull(studentTO.getId()) ? null : studentTO.getId())
                .name(studentTO.getName())
                .lastName(studentTO.getLastName())
                .email(studentTO.getEmail())
                .phoneNumber(studentTO.getPhoneNumber())
                .studentStatus(studentTO.getStudentStatus())
                .studentCode(studentTO.getStudentCode())
                .career(CareerApiMapper.convertTOToBO(studentTO.getCareer()))
                .build();
    }

    public static List<StudentTO> convertBOListToTOList(List<StudentBO> studentsBO){
        List<StudentTO> studentsTO = new ArrayList<>();
        studentsBO.forEach(studentBO -> studentsTO.add(convertBOToTO(studentBO)));
        return studentsTO;
    }

    public static List<StudentBO> convertTOListToBOList(List<StudentTO> studentsTO){
        List<StudentBO> studentsBO = new ArrayList<>();
        studentsTO.forEach(studentTO -> studentsBO.add(convertTOToBO(studentTO)));
        return studentsBO;
    }
}
