package com.luciana.challenge.mybank.controller;

import com.luciana.challenge.mybank.builder.BankDTOBuilder;
import com.luciana.challenge.mybank.dto.request.BankDTO;
import com.luciana.challenge.mybank.exception.BankNotFoundException;
import com.luciana.challenge.mybank.service.BankService;
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

import static com.luciana.challenge.mybank.builder.BankDTOBuilder.asJsonString;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class BankControllerTest {

    private static final String BANK_API_URL_PATH = "/api/v1/bank";
    private static final Long VALID_BANK_ID = 1L;
    private static final long INVALID_BANK_ID = 2l;
    private static final String VALID_BANK_NAME = "BankLu";

    private MockMvc mockMvc;

    @Mock
    private BankService bankService;

    @InjectMocks
    private BankController bankController;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(bankController)
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .setViewResolvers((viewName, locale) -> new MappingJackson2JsonView())
                .build();
    }

    @Test
    void whenPOSTIsCalledThenABeerIsCreated() throws Exception {
        BankDTO bankDTO = BankDTOBuilder.builder().build().toBankDTO();

        when(bankService.createBank(bankDTO)).thenReturn(bankDTO);

        mockMvc.perform(post(BANK_API_URL_PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(bankDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", is(bankDTO.getName())));
    }

    @Test
    void whenPOSTIsCalledWithouRequiredFieldThenAnErrorIsReturned() throws Exception {
        BankDTO bankDTO = BankDTOBuilder.builder().build().toBankDTO();
        bankDTO.setName(null);

        mockMvc.perform(post(BANK_API_URL_PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(bankDTO)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void whenGETIsCalledWithValidNameThenOkStatusIsReturned() throws Exception {
        BankDTO bankDTO = BankDTOBuilder.builder().build().toBankDTO();

        when(bankService.findByName(bankDTO.getName())).thenReturn(bankDTO);

        mockMvc.perform(get(BANK_API_URL_PATH + "/" + bankDTO.getName())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(bankDTO.getName())));
    }

    @Test
    void whenGETIsCalledWithNotRegisteredNameThenNotFoundStatusIsReturned() throws Exception {
        BankDTO bankDTO = BankDTOBuilder.builder().build().toBankDTO();

        when(bankService.findByName(bankDTO.getName())).thenThrow(BankNotFoundException.class);

        mockMvc.perform(get(BANK_API_URL_PATH + "/" + bankDTO.getName())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void whenGETListWithBanksIsCalledThenOkStatusIsReturned() throws Exception {
        BankDTO bankDTO = BankDTOBuilder.builder().build().toBankDTO();

        when(bankService.listAll()).thenReturn(Collections.singletonList(bankDTO));

        mockMvc.perform(get(BANK_API_URL_PATH)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name", is(bankDTO.getName())));
    }

    @Test
    void whenGETListWithoutBanksIsCalledThenOkStatusIsReturned() throws Exception {
        when(bankService.listAll()).thenReturn(Collections.EMPTY_LIST);

        mockMvc.perform(get(BANK_API_URL_PATH)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void whenDELETEIsCalledWithValidIdThenNoContentStatusIsReturned() throws Exception {
        doNothing().when(bankService).deleteById(VALID_BANK_ID);

        mockMvc.perform(delete(BANK_API_URL_PATH + "/" + VALID_BANK_ID)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        verify(bankService, times(1)).deleteById(VALID_BANK_ID);
    }

    @Test
    void whenDELETEIsCalledWithoutValidIdThenNotFoundStatusIsReturned() throws Exception {
        doThrow(BankNotFoundException.class).when(bankService).deleteById(INVALID_BANK_ID);

        mockMvc.perform(delete(BANK_API_URL_PATH + "/" + INVALID_BANK_ID)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

}
