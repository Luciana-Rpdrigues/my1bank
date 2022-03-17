package com.luciana.challenge.mybank.dto.response;

import com.luciana.challenge.mybank.dto.request.BankDTO;
import lombok.Data;

@Data
public class BankMessageResponseDTO extends BankDTO {

    private String message;
}
