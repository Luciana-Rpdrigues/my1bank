package com.luciana.challenge.mybank.dto.request;

import com.luciana.challenge.mybank.enums.PhoneType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;


import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClientDTO {

    @NotNull
    @NotEmpty
    private Long id;

    @NotNull
    @NotEmpty
    @Size(min = 10, max = 100)
    private String name;

    @NotNull
    @NotEmpty
    @CPF
    @Size(max = 11)
    private String cpf;

    @NotNull
    @NotEmpty
    @Valid
    private List<PhoneDTO> phone = getPhone();

    public ClientDTO(Long id, String name, String cpf, PhoneType phones) {
    }
}



