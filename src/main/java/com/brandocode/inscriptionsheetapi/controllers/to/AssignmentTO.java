package com.brandocode.inscriptionsheetapi.controllers.to;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@Builder

public class AssignmentTO {

    private Long id;

    @NotNull(message = "has to be null bitch")
    private String name;

    private String assignmentCode;
}
