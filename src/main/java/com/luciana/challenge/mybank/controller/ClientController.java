package com.luciana.challenge.mybank.controller;

import com.luciana.challenge.mybank.dto.request.ClientDTO;
import com.luciana.challenge.mybank.dto.response.ClientMessageResponseDTO;
import com.luciana.challenge.mybank.exception.ClientAlreadyRegisteredException;
import com.luciana.challenge.mybank.exception.ClientNotFoundException;
import com.luciana.challenge.mybank.service.ClientService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Data
@RestController
@RequestMapping(value = "/api/v1/banks")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ClientController implements ClientControllerDocs {

    private ClientService clientService;

    public ClientController() {

    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ClientDTO createClient(@RequestBody @Valid ClientDTO clientDTO) throws ClientAlreadyRegisteredException {
        return clientService.createClient(clientDTO);
    }

    @Override
    public ClientDTO findByName(String name) throws ClientNotFoundException {
        return null;
    }

    @Override
    public List<ClientDTO> listClient() {
        return null;
    }

    @GetMapping
    public List<ClientDTO> listAll() {
        return clientService.listAll();
    }

    @GetMapping("/{id}")
    public ClientDTO findById(@PathVariable Long id) throws ClientNotFoundException {
        return clientService.findById(id);
    }

    @GetMapping("/{id}")
    public ClientMessageResponseDTO updateById(@PathVariable Long id, @RequestBody ClientDTO clientDTO) throws ClientNotFoundException {
        return (ClientMessageResponseDTO) clientService.updateById(id, clientDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(Long id) throws ClientNotFoundException {
        clientService.delete(id);
    }
}
