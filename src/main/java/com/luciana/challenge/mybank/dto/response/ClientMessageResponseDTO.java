package com.luciana.challenge.mybank.dto.response;

import com.luciana.challenge.mybank.dto.request.ClientDTO;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
public
class ClientMessageResponseDTO extends ClientDTO {

    private String message;
}
