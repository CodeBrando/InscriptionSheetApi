package com.brandocode.inscriptionsheetapi.controllers.mappers;

import com.brandocode.inscriptionsheetapi.controllers.to.CareerTO;
import com.brandocode.inscriptionsheetapi.models.bo.CareerBO;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CareerApiMapper {

    public static CareerBO convertTOToBO(CareerTO careerTO){
        return CareerBO.builder()
                .id(Objects.isNull(careerTO.getId()) ? null : careerTO.getId())
                .name(careerTO.getName())
                .careerCode(careerTO.getCareerCode())
                .assignments(AssignmentApiMapper.convertTOListToBOList(careerTO.getAssignments()))
                .build();
    }

    public static CareerTO convertBOToTO(CareerBO careerBO){
        return CareerTO.builder()
                .id(Objects.isNull(careerBO.getId()) ? null : careerBO.getId())
                .name(careerBO.getName())
                .careerCode(careerBO.getCareerCode())
                .assignments(AssignmentApiMapper.convertBOListToTOList(careerBO.getAssignments()))
                .build();
    }

    public static List<CareerTO> convertBOListToTOList(List<CareerBO> careersBO){
        List<CareerTO> careersTO = new ArrayList<>();
        careersBO.forEach(careerBO -> careersTO.add(convertBOToTO(careerBO)));
        return careersTO;
    }

    public static List<CareerBO> convertTOListToBOList(List<CareerTO> careersTO){
        List<CareerBO> careersBO = new ArrayList<>();
        careersTO.forEach(careerTO -> careersBO.add(convertTOToBO(careerTO)));
        return careersBO;
    }
}
