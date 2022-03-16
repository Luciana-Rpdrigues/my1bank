package com.luciana.challenge.mybank.service;

import com.luciana.challenge.mybank.contact_of_customer.Bank;
import com.luciana.challenge.mybank.dto.mapper.BankMapper;
import com.luciana.challenge.mybank.dto.request.BankDTO;
import com.luciana.challenge.mybank.dto.response.BankMessageResponseDTO;
import com.luciana.challenge.mybank.exception.BankAlreadyRegisteredException;
import com.luciana.challenge.mybank.exception.BankNotFoundException;
import com.luciana.challenge.mybank.repository.BankRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class BankService {

    private final BankRepository bankRepository;
    private final BankMapper bankMapper = BankMapper.INSTANCE;
    private BankDTO bank;

    public BankDTO createBank(BankDTO bankDTO) throws BankAlreadyRegisteredException {
        verifyIfIsAlreadyRegistered(bankDTO.getName());
        Bank bank = bankMapper.toModel(bankDTO);
        Bank savedBank = bankRepository.save(bank);
        return bankMapper.toDTO(savedBank);
    }

    public BankDTO findByName(String name) throws BankNotFoundException {
        Bank foundBeer = bankRepository.findByName(name)
                .orElseThrow(() -> new BankNotFoundException(name));
        return bankMapper.toDTO(foundBeer);
    }

    public List<Object> listAll() {
        return bankRepository.findAll()
                .stream()
                .map(bankMapper::toDTO)
                .collect(Collectors.toList());
    }

    public BankDTO findById(Long id) throws BankNotFoundException {
        Bank bank = verifyExixts(id);

        return bankMapper.toDTO(bank);
    }

    public BankMessageResponseDTO updateById(Long id, BankDTO clientDTO) throws BankNotFoundException {
        verifyExixts(id);

        Bank bankToUpdate = bankMapper.toModel(bank);

        Bank updatedBank = bankRepository.save(bankToUpdate);
        return createMessageResponse(updatedBank.getId(), "Updated bank with ID ");
    }

    private Bank verifyExixts(Long id) throws BankNotFoundException {
        return bankRepository.findById(id)
                .orElseThrow(() -> new BankNotFoundException(id));
    }

    public void delete(Long id) throws BankNotFoundException {
        verifyExixts(id);
        bankRepository.deleteById(id);
    }

    private BankMessageResponseDTO createMessageResponse(Long id, String message) {
        return BankMessageResponseDTO
                .builder()
                .message(message + id)
                .build();
    }
    private void verifyIfIsAlreadyRegistered(String name) throws BankAlreadyRegisteredException {
        Optional<Bank> optSavedBank = bankRepository.findByName(name);
        if (optSavedBank.isPresent()) {
            throw new BankAlreadyRegisteredException(name);
        }
    }

}
