package com.brandocode.inscriptionsheetapi.controller.to;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@Builder

public class AssignmentTO {

    private Long id;

    private String name;

    private String assignmentCode;
}
