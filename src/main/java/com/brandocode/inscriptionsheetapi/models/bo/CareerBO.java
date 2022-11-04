package com.brandocode.inscriptionsheetapi.models.bo;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CareerBO {
    private Long id;
    private String name;
    private String careerCode;
    private List<AssignmentBO> assignments;
}
