package com.brandocode.inscriptionsheetapi.models.bo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StudentBO {

    private Long id;
    private String name;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String studentStatus;
    private String studentCode;
    private CareerBO career;


}
