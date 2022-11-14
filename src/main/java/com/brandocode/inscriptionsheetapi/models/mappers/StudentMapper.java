package com.brandocode.inscriptionsheetapi.models.mappers;

import com.brandocode.inscriptionsheetapi.models.bo.StudentBO;
import com.brandocode.inscriptionsheetapi.models.de.StudentDE;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class StudentMapper {

    public static StudentDE convertBOToDE(StudentBO studentBO){
        return StudentDE.builder()
                .id(Objects.isNull(studentBO.getId()) ? null : studentBO.getId())
                .name(studentBO.getName())
                .lastName(studentBO.getLastName())
                .email(studentBO.getEmail())
                .phoneNumber(studentBO.getPhoneNumber())
                .studentStatus(studentBO.getStudentStatus())
                .studentCode(studentBO.getStudentCode())
                .career(CareerMapper.convertBOToDE(studentBO.getCareer()))
                .build();
    }

    public static StudentBO convertDEToBO(StudentDE studentDE){
        return StudentBO.builder()
                .id(Objects.isNull(studentDE.getId()) ? null : studentDE.getId())
                .name(studentDE.getName())
                .lastName(studentDE.getLastName())
                .email(studentDE.getEmail())
                .phoneNumber(studentDE.getPhoneNumber())
                .studentStatus(studentDE.getStudentStatus())
                .studentCode(studentDE.getStudentCode())
                .career(CareerMapper.convertDEToBO(studentDE.getCareer()))
                .build();
    }

    public static List<StudentBO> convertDEListToBOList(List<StudentDE> studentsDE){
        List<StudentBO> studentsBO = new ArrayList<>();
        studentsDE.forEach(studentDE -> studentsBO.add(convertDEToBO(studentDE)));
        return studentsBO;
    }

    public static List<StudentDE> convertBOListToDEList(List<StudentBO> studentsBO){
        List<StudentDE> studentsDE = new ArrayList<>();
        studentsBO.forEach(studentBO -> studentsDE.add(convertBOToDE(studentBO)));
        return studentsDE;
    }
}
