package com.luciana.challenge.mybank.builder;


import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.luciana.challenge.mybank.controller.BillController;
import com.luciana.challenge.mybank.dto.request.BankDTO;
import lombok.Builder;
import java.util.List;


@Builder
public class BankDTOBuilder {

    @Builder.Default
    private Long id = 1L;

    @Builder.Default
    private String name = "BankLu";

    @Builder.Default
    private List<BillController> bills;

    @Builder.Default
    private List<ClientDTOBuilder> clients;

    public BankDTO toBankDTO() {
        return new BankDTO(
                id,
                name,
                bills,
                clients);
    }

    public static String asJsonString(BankDTO bookDTO) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
            objectMapper.registerModule(new JavaTimeModule());
            return objectMapper.writeValueAsString(bookDTO);
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
