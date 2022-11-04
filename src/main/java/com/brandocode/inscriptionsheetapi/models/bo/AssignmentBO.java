package com.brandocode.inscriptionsheetapi.models.bo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AssignmentBO {
    private Long id;
    private String name;
    private String assignmentCode;
}
