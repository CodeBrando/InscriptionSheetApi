package com.brandocode.inscriptionsheetapi.controller.to;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Builder
public class StudentTO {

    private Long id;

    @NotNull(message = "Students must have names.")
    @Size(message = "Name of student can´t have less than 2 letters.", min = 2)
    private String name;

    @NotNull(message = "Students must have last names.")
    @Size(message = "Last name of student can´t have less than 2 letters.", min = 2)
    private String lastName;

    private String email;

    @NotNull
    @Size(message = "Phone number must have at least 8 digits.", min = 8, max = 16)
    private String phoneNumber;

    private String studentStatus;

    private String studentCode;

    private CareerTO career;
}
