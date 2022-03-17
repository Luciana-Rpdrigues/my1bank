package com.luciana.challenge.mybank.builder;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.luciana.challenge.mybank.dto.request.BankDTO;
import com.luciana.challenge.mybank.dto.request.ClientDTO;
import com.luciana.challenge.mybank.enums.PhoneType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClientDTOBuilder {

    @Id
    @Builder.Default
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id = 1L;

    @Builder.Default
    @Column(nullable = false)
    private String name = "Daniel";

    @Builder.Default
    @Column(nullable = false, unique = true)
    private String cpf = "999.888.777-11";

    @Builder.Default
    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
    private PhoneType phones = PhoneType.MOBILE;
    public ClientDTO toClientDTO() {
        return new ClientDTO(
                id,
                name,
                cpf,
                phones);
    }

    public static String asJsonString(ClientDTO bookDTO) {
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
