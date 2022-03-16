package com.luciana.challenge.mybank.controller;

import com.luciana.challenge.mybank.dto.request.ClientDTO;
import com.luciana.challenge.mybank.dto.response.MessageResponseDTO;
import com.luciana.challenge.mybank.exception.ClientNofFoundException;
import com.luciana.challenge.mybank.service.ClientService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Data
@RestController
@RequestMapping
@AllArgsConstructor
public class ClientController {

    private ClientService clientService;

    public ClientController() {

    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MessageResponseDTO create(@RequestBody @Valid ClientDTO clientDTO) {
        return clientService.createClient(clientDTO);
    }

    @GetMapping
    public List<ClientDTO> listAll() {
        return clientService.listAll();
    }

    @GetMapping("/{id}")
    public ClientDTO findById(@PathVariable Long id) throws ClientNofFoundException {
        return clientService.findById(id);
    }

    @GetMapping("/{id}")
    public MessageResponseDTO updateById(@PathVariable Long id, @RequestBody ClientDTO clientDTO) throws ClientNofFoundException {
        return clientService.updateById(id, clientDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(Long id) throws ClientNofFoundException {
        clientService.delete(id);
    }

}
