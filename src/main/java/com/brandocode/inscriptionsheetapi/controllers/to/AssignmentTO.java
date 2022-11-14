package com.brandocode.inscriptionsheetapi.controllers.to;

import lombok.Builder;
import lombok.Data;

@Data
@Builder

public class AssignmentTO {

    private Long id;

    private String name;

    private String assignmentCode;
}
