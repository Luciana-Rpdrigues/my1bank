package com.luciana.challenge.mybank.dto.request;

import com.luciana.challenge.mybank.builder.ClientDTOBuilder;
import com.luciana.challenge.mybank.controller.BillController;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BankDTO {

    @NotNull
    @NotEmpty
    private Long id;

    @NotNull
    @NotEmpty
    @Size(min = 10, max = 100)
    private String name;

    @NotNull
    @NotEmpty
    private List<BillController> bills;

    @NotNull
    @NotEmpty
    private List<ClientDTOBuilder> clients;

    public BankDTO toBankDTO() {
        return null;
    }
}
