package org.comparus.ua.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.comparus.ua.dto.UserDto;
import org.comparus.ua.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import static org.comparus.ua.constants.Base.*;
import static org.comparus.ua.constants.OpenAPI.*;

@RestController
@RequestMapping(path = USERS_MAPPING, produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    @Autowired
    private UserService userService;

    @Operation(summary = FIND_ALL_SUMMARY, description = FIND_ALL_OPERATION_DESCRIPTION)
    @ApiResponses({
            @ApiResponse(responseCode = SUCCESS_CODE, content = @Content(array = @ArraySchema(schema = @Schema(implementation = UserDto.class)))),
            @ApiResponse(responseCode = SERVER_ERROR_CODE, content =  @Content(), description = SERVER_ERROR_DESCRIPTION),
    })
    @GetMapping(path = FIND_ALL_MAPPING)
    public List<UserDto> getAllUsers() {
        return userService.getAllUsers();
    }

    @Operation(summary = FIND_BY_ID_SUMMARY, description = FIND_BY_ID_OPERATION_DESCRIPTION)
    @ApiResponses({
            @ApiResponse(responseCode = SUCCESS_CODE, content =  @Content(schema = @Schema(implementation = UserDto.class))),
            @ApiResponse(responseCode = NOT_FOUND_CODE, content =  @Content(), description = NOT_FOUND_DESCRIPTION),
            @ApiResponse(responseCode = SERVER_ERROR_CODE, content =  @Content(schema = @Schema()), description = SERVER_ERROR_DESCRIPTION),
    })
    @GetMapping(path = FIND_BY_ID_MAPPING)
    public UserDto getUserById(@RequestParam String userId) {
        return userService.getUserById(userId);
    }

    @Operation(summary = FIND_BY_FILTERS_SUMMARY, description = FIND_BY_FILTERS_OPERATION_DESCRIPTION)
    @ApiResponses({
            @ApiResponse(responseCode = SUCCESS_CODE, content = @Content(array = @ArraySchema(schema = @Schema(implementation = UserDto.class)))),
            @ApiResponse(responseCode = SERVER_ERROR_CODE, content =  @Content(), description = SERVER_ERROR_DESCRIPTION),
    })
    @PostMapping(path = FIND_BY_FILTERS_MAPPING)
    public List<UserDto> getUsersByFilters(@Valid @RequestBody UserDto userDto) {
        return userService.getUsersByFilters(userDto);
    }
}