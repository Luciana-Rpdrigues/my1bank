package com.luciana.challenge.mybank.dto.request;

import com.luciana.challenge.mybank.enums.PhoneType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
public class PhoneDTO {

    @Builder.Default
    private Long id;

    @Builder.Default
    @Enumerated(EnumType.STRING)
    private PhoneType type;

    @Builder.Default
    @NotEmpty
    @Size(min = 13, max = 14)
    private String number;
}
