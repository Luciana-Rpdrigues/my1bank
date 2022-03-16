package com.luciana.challenge.mybank.controller;

import com.luciana.challenge.mybank.dto.request.BankDTO;
import com.luciana.challenge.mybank.dto.response.BankMessageResponseDTO;
import com.luciana.challenge.mybank.exception.BankAlreadyRegisteredException;
import com.luciana.challenge.mybank.exception.BankNotFoundException;
import com.luciana.challenge.mybank.service.BankService;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@Data
@Builder
@RestController
@RequestMapping(value = "/api/v1/banks")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class BankController extends BankControllerDocs {

    private BankService bankService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BankDTO createBank(@RequestBody @Valid BankDTO bankDTO) throws BankAlreadyRegisteredException {
        return bankService.createBank(bankDTO);
    }

    @GetMapping("/{name}")
    public BankDTO findByName(@PathVariable String name) throws BankNotFoundException {
        return bankService.findByName(name);
    }

    @GetMapping
    public List<Object> listAll() {
        return bankService.listAll();
    }

    @GetMapping("/{id}")
    public BankMessageResponseDTO updateById(@PathVariable Long id, @RequestBody BankDTO bankDTO) throws BankNotFoundException {
        return bankService.updateById(id, bankDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(Long id) throws BankNotFoundException {
        bankService.delete(id);
    }
}
