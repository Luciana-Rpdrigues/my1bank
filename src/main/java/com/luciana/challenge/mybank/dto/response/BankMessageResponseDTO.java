package com.luciana.challenge.mybank.dto.response;

import com.luciana.challenge.mybank.dto.request.BankDTO;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BankMessageResponseDTO extends BankDTO {

    private String message;
}
