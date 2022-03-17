package com.luciana.challenge.mybank.controller;

import com.luciana.challenge.mybank.builder.ClientDTOBuilder;
import com.luciana.challenge.mybank.dto.request.ClientDTO;
import com.luciana.challenge.mybank.exception.ClientNotFoundException;
import com.luciana.challenge.mybank.service.ClientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import java.util.Collections;

import static com.luciana.challenge.mybank.builder.ClientDTOBuilder.asJsonString;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ClientControllerTest {

    private static final String CLIENT_API_URL_PATH = ("/api/v1/banks");
    private static final long VALID_CLIENT_ID = 1L;
    private static final long INVALID_CLIENT_ID = 2l;

    private MockMvc mockMvc;

    @Mock
    private ClientService clientService;

    @InjectMocks
    private ClientController clientController;
    private ClientDTO clientDTO;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(clientController)
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .setViewResolvers((viewName, locale) -> new MappingJackson2JsonView())
                .build();
    }
    @Test
    void whenPOSTIsCalledThenABeerIsCreated() throws Exception {
        ClientDTO beerDTO = ClientDTOBuilder.builder().build().toClientDTO();

        when(clientService.createClient(clientDTO)).thenReturn(clientDTO);

        mockMvc.perform(post(CLIENT_API_URL_PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(clientDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", is(clientDTO.getName())))
                .andExpect(jsonPath("$.cpf", is(clientDTO.getCpf())))
                .andExpect(jsonPath("$.phone", is(clientDTO.getPhone().toString())));
    }

    @Test
    void whenPOSTIsCalledWithouRequiredFieldThenAnErrorIsReturned() throws Exception {
        ClientDTO clientDTO = ClientDTOBuilder.builder().build().toClientDTO();
        clientDTO.setName(null);

        mockMvc.perform(post(CLIENT_API_URL_PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(clientDTO)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void whenGETIsCalledWithValidNameThenOkStatusIsReturned() throws Exception {
        ClientDTO clientDTO = ClientDTOBuilder.builder().build().toClientDTO();

        when(clientService.findByName(clientDTO.getName())).thenReturn(clientDTO);

        mockMvc.perform(get(CLIENT_API_URL_PATH + "/" + clientDTO.getName())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(clientDTO.getName())))
                .andExpect(jsonPath("$.cpf", is(clientDTO.getCpf())))
                .andExpect(jsonPath("$.phone", is(clientDTO.getPhone().toString())));
    }

    @Test
    void whenGETIsCalledWithNotRegisteredNameThenNotFoundStatusIsReturned() throws Exception {
        ClientDTO clientDTO = ClientDTOBuilder.builder().build().toClientDTO();

        when(clientService.findByName(clientDTO.getName())).thenThrow(ClientNotFoundException.class);

        mockMvc.perform(get(CLIENT_API_URL_PATH + "/" + clientDTO.getName())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void whenGETListWithBeersIsCalledThenOkStatusIsReturned() throws Exception {
        ClientDTO beerDTO = ClientDTOBuilder.builder().build().toClientDTO();

        when(clientService.listAll()).thenReturn(Collections.singletonList(beerDTO));

        mockMvc.perform(get(CLIENT_API_URL_PATH)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(clientDTO.getName())))
                .andExpect(jsonPath("$.cpf", is(clientDTO.getCpf())))
                .andExpect(jsonPath("$.phone", is(clientDTO.getPhone().toString())));
    }

    @Test
    void whenGETListWithoutClientsIsCalledThenOkStatusIsReturned() throws Exception {
        when(clientService.listAll()).thenReturn(Collections.EMPTY_LIST);

        mockMvc.perform(get(CLIENT_API_URL_PATH)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void whenDELETEIsCalledWithValidIdThenNoContentStatusIsReturned() throws Exception {
        doNothing().when(clientService).deleteById(VALID_CLIENT_ID);

        mockMvc.perform(delete(CLIENT_API_URL_PATH + "/" + VALID_CLIENT_ID)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        verify(clientService, times(1)).deleteById(VALID_CLIENT_ID);
    }

    @Test
    void whenDELETEIsCalledWithoutValidIdThenNotFoundStatusIsReturned() throws Exception {
        doThrow(ClientNotFoundException.class).when(clientService).deleteById(INVALID_CLIENT_ID);

        mockMvc.perform(delete(CLIENT_API_URL_PATH + "/" + INVALID_CLIENT_ID)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}
