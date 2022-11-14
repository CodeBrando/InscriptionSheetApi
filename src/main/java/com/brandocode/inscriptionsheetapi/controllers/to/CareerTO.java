package com.brandocode.inscriptionsheetapi.controllers.to;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CareerTO {

    private Long id;

    private String name;

    private String careerCode;

    private List<AssignmentTO> assignments;
}
