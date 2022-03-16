package com.luciana.challenge.mybank.dto.request;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.luciana.challenge.mybank.controller.BillController;
import com.luciana.challenge.mybank.controller.ClientController;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BankDTO {

    @Builder.Default
    private Long id;

    @Builder.Default
    @NotEmpty
    private String name = "BankLu";

    @Builder.Default
    @NotEmpty
    private List<BillController> bills;

    @Builder.Default
    @NotEmpty
    private List<ClientController> clients;

    public BankDTO toBankDTO(){
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
            objectMapper.registerModules(new JavaTimeModule());

            return objectMapper.writeValueAsString(bookDTO);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
