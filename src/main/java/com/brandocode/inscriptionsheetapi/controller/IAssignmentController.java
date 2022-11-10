package com.brandocode.inscriptionsheetapi.controller;

import com.brandocode.inscriptionsheetapi.controller.to.AssignmentTO;
import com.brandocode.inscriptionsheetapi.controller.to.ResponseTO;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
@RequestMapping("/api/v1/assignments")
public interface IAssignmentController {

    String APPLICATION_JSON = "application/json";

    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Created"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @Operation(summary = "Creates a new assignment")
    @PostMapping(consumes = APPLICATION_JSON, produces = APPLICATION_JSON)
    ResponseEntity<ResponseTO> createAssignment(@ApiParam(value = "Assignment to create" ,required=true )@Valid @RequestBody AssignmentTO assignment);


    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @Operation(summary = "Obtain the assignment with the given assignment code")
    @GetMapping(value = "/{assignmentCode}", produces = APPLICATION_JSON)
    ResponseEntity<?> findAssignmentByAssignmentCode(@ApiParam(value = "Assignment code of the assignment to find")
                                                    @PathVariable("assignmentCode") String assignmentCode);

    @ApiResponses(value={
            @ApiResponse(responseCode = "202", description = "Created"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "500", description = "Internal Server error")
    })
    @Operation(summary = "Finds all assignments")
    @GetMapping(produces = APPLICATION_JSON)
    ResponseEntity<?> findAllAssignments();

    @ApiResponses(value={
            @ApiResponse(responseCode = "202", description = "Updated"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "500", description = "Internal Server error")
    })
    @Operation(summary = "Updates an existing assignment")
    @PutMapping(value = "/{assignmentCode}", consumes = APPLICATION_JSON, produces = APPLICATION_JSON)
    ResponseEntity<ResponseTO> updateAssignment(@ApiParam(value = "Assignment to update", required = true) @Valid @RequestBody AssignmentTO assignment,
                                                @PathVariable("assignmentCode") String assignmentCode);

    @ApiResponses(value={
            @ApiResponse(responseCode = "202", description = "Deleted"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "500", description = "Internal Server error")
    })
    @Operation(summary = "Deletes an existing assignment with a given assignment code")
    @DeleteMapping(value = "/{assignmentCode}")
    ResponseEntity<?> deleteAssignment(@ApiParam(value = "Assignment to delete") @PathVariable("assignmentCode") String assignmentCode);
}
