package com.brandocode.inscriptionsheetapi.controllers;

import com.brandocode.inscriptionsheetapi.controllers.to.ResponseTO;
import com.brandocode.inscriptionsheetapi.controllers.to.StudentTO;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping("/api/v1/students")
public interface IStudentController {

    String APPLICATION_JSON = "application/json";

    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Created"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @Operation(summary = "Creates a new student")
    @PostMapping(value = "/{careerCode}" , consumes = APPLICATION_JSON, produces = APPLICATION_JSON)
    ResponseEntity<ResponseTO> createStudent(@ApiParam(value = "Student to create" ,required=true )
                                             @Valid @RequestBody StudentTO student, @PathVariable("careerCode") String careerCode);


    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @Operation(summary = "Obtain the student with the given student code")
    @GetMapping(value = "/{studentCode}", produces = APPLICATION_JSON)
    ResponseEntity<?> findStudentByStudentCode(@ApiParam(value = "Student code of the student to find") @PathVariable("studentCode") String studentCode);

    @ApiResponses(value={
            @ApiResponse(responseCode = "202", description = "Created"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "500", description = "Internal Server error")
    })
    @Operation(summary = "Finds all students")
    @GetMapping(produces = APPLICATION_JSON)
    ResponseEntity<?> findAllStudents();

    @ApiResponses(value={
            @ApiResponse(responseCode = "202", description = "Updated"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "500", description = "Internal Server error")
    })
    @Operation(summary = "Updates an existing student")
    @PutMapping(value = "/{studentCode}", consumes = APPLICATION_JSON, produces = APPLICATION_JSON)
    ResponseEntity<ResponseTO> updateStudent(@ApiParam(value = "Student to update", required = true)
                                             @Valid @RequestBody StudentTO student, @PathVariable("studentCode") String studentCode);

    @ApiResponses(value={
            @ApiResponse(responseCode = "202", description = "Deleted"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "500", description = "Internal Server error")
    })
    @Operation(summary = "Deletes an existing student")
    @DeleteMapping(value = "/{studentCode}")
    ResponseEntity<?> deleteStudent(@ApiParam(value = "Student to delete") @PathVariable("studentCode") String studentCode);

}
