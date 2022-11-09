package com.brandocode.inscriptionsheetapi.controller;


import com.brandocode.inscriptionsheetapi.controller.to.CareerTO;
import com.brandocode.inscriptionsheetapi.controller.to.ResponseTO;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping("/api/v1/careers")
public interface ICareerController {

    String APPLICATION_JSON = "application/json";

    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Created"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @Operation(summary = "Creates a new career")
    @PostMapping(value = "/{assignment1}/{assignment2}/{assignment3}", consumes = APPLICATION_JSON, produces = APPLICATION_JSON)
    ResponseEntity<ResponseTO> createCareer(@ApiParam(value = "Career to create" ,required=true)
                                            @Valid @RequestBody CareerTO career,
                                            @PathVariable("assignment1") String assignment1,
    @PathVariable("assignment2") String assignment2,
    @PathVariable("assignment3") String assignment3);


    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @Operation(summary = "Obtain the career with the given career code")
    @GetMapping(value = "/{careerCode}", produces = APPLICATION_JSON)
    ResponseEntity<?> getCareerByCareerCode(@ApiParam(value = "Career code of the career to find") @PathVariable("careerCode") String careerCode);

    @ApiResponses(value={
            @ApiResponse(responseCode = "202", description = "Created"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "500", description = "Internal Server error")
    })
    @Operation(summary = "Finds all careers")
    @GetMapping(produces = APPLICATION_JSON)
    ResponseEntity<?> findAllCareers();

    @ApiResponses(value={
            @ApiResponse(responseCode = "202", description = "Updated"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "500", description = "Internal Server error")
    })
    @Operation(summary = "Updates an existing career")
    @PutMapping(value = "/{careerCode}", consumes = APPLICATION_JSON, produces = APPLICATION_JSON)
    ResponseEntity<ResponseTO> updateCareer(@ApiParam(value = "Career to update", required = true) @Valid @RequestBody CareerTO career,
                                            @PathVariable("careerCode") String careerCode);

    @ApiResponses(value={
            @ApiResponse(responseCode = "202", description = "Deleted"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "500", description = "Internal Server error")
    })
    @Operation(summary = "Deletes an existing career")
    @DeleteMapping(value = "/{careerCode}")
    ResponseEntity<?> deleteCareer(@ApiParam(value = "Career to delete") @PathVariable("careerCode") String careerCode);
}
