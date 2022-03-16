package com.luciana.challenge.mybank.service;

import com.luciana.challenge.mybank.contact_of_customer.Client;
import com.luciana.challenge.mybank.dto.mapper.ClientMapper;
import com.luciana.challenge.mybank.dto.request.ClientDTO;
import com.luciana.challenge.mybank.dto.response.MessageResponseDTO;
import com.luciana.challenge.mybank.exception.ClientNofFoundException;
import com.luciana.challenge.mybank.repository.ClientRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ClientService {

    private ClientRepository clientRepository;
    private final ClientMapper clientMapper = ClientMapper.INSTANCE;

    public MessageResponseDTO createClient(ClientDTO clientDTO) {
        Client clientToSave = clientMapper.toModel(clientDTO);

        Client savedClient = clientRepository.save(clientToSave);
        return createMessageResponse(savedClient.getId(), "Create client with ID " +  savedClient);
    }

    public List<ClientDTO> listAll() {
        List<Client> allPeople = clientRepository.findAll();
        return allPeople.stream()
                .map(clientMapper::toDTO)
                .collect(Collectors.toList());
    }

    public ClientDTO findById(Long id) throws ClientNofFoundException {
        Client client = verifyExixts(id);

        return clientMapper.toDTO(client);
    }

    public void delete(Long id) throws ClientNofFoundException {
        verifyExixts(id);
        clientRepository.deleteById(id);
    }

    public MessageResponseDTO updateById(Long id, ClientDTO clientDTO) throws ClientNofFoundException {
        verifyExixts(id);

        Client clientToUpdate = clientMapper.toModel(clientDTO);

        Client updatedClient = clientRepository.save(clientToUpdate);
        return createMessageResponse(updatedClient.getId(), "Updated client with ID ");
    }

    private Client verifyExixts(Long id) throws ClientNofFoundException {
        return clientRepository.findById(id)
                .orElseThrow(() -> new ClientNofFoundException(id));
    }

    private MessageResponseDTO createMessageResponse(Long id, String message) {
        return MessageResponseDTO
                .builder()
                .message(message + id)
                .build();
    }
}
