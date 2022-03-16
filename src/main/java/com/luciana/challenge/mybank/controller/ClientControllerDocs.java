package com.luciana.challenge.mybank.controller;

import com.luciana.challenge.mybank.dto.request.ClientDTO;
import com.luciana.challenge.mybank.exception.ClientAlreadyRegisteredException;
import com.luciana.challenge.mybank.exception.ClientNotFoundException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;

@Api("Manages client")
public interface ClientControllerDocs {

    @ApiOperation(value = "Client creation operation")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Success beer creation"),
            @ApiResponse(code = 400, message = "Missing required fields or wrong field range value.")
    })
    ClientDTO createClient(ClientDTO beerDTO) throws ClientAlreadyRegisteredException;

    @ApiOperation(value = "Returns beer found by a given name")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success client found in the system"),
            @ApiResponse(code = 404, message = "Client with given name not found.")
    })
    ClientDTO findByName(@PathVariable String name) throws ClientNotFoundException;

    @ApiOperation(value = "Returns a list of all beers registered in the system")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "List of all beers registered in the system"),
    })
    List<ClientDTO> listClient();

    @ApiOperation(value = "Delete a client found by a given valid Id")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Success client deleted in the system"),
            @ApiResponse(code = 404, message = "Client with given id not found.")
    })
    void deleteById(@PathVariable Long id) throws ClientNotFoundException;
}