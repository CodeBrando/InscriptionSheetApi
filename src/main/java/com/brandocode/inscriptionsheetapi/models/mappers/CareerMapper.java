package com.brandocode.inscriptionsheetapi.models.mappers;

import com.brandocode.inscriptionsheetapi.models.bo.CareerBO;
import com.brandocode.inscriptionsheetapi.models.de.CareerDE;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CareerMapper {

    public static CareerDE convertBOToDE(CareerBO careerBO){
        return CareerDE.builder()
                .id(Objects.isNull(careerBO.getId()) ? null : careerBO.getId())
                .name(careerBO.getName())
                .careerCode(careerBO.getCareerCode())
                .assignments(AssignmentMapper.convertBOListToDEList(careerBO.getAssignments()))
                .build();
    }

    public static CareerBO convertDEToBO(CareerDE careerDE){
        return CareerBO.builder()
                .id(Objects.isNull(careerDE.getId()) ? null : careerDE.getId())
                .name(careerDE.getName())
                .careerCode(careerDE.getCareerCode())
                .assignments(AssignmentMapper.convertDEListToBOList(careerDE.getAssignments()))
                .build();
    }

    public static List<CareerBO> convertDEListToBOList(List<CareerDE> careersDE){
        List<CareerBO> careersBO = new ArrayList<>();
        careersDE.forEach(careerDE -> careersBO.add(convertDEToBO(careerDE)));
        return careersBO;
    }

    public static List<CareerDE> convertBOListToDEList(List<CareerBO> careersBO){
        List<CareerDE> careersDE = new ArrayList<>();
        careersBO.forEach(careerBO -> careersDE.add(convertBOToDE(careerBO)));
        return careersDE;
    }
}
