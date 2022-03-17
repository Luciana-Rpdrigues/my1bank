package com.luciana.challenge.mybank.builder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.luciana.challenge.mybank.enums.PhoneType;

import javax.persistence.*;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PhoneDTOBuilder {

    @Id
    @Builder.Default
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id = 2L;

    @Builder.Default
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PhoneType type = PhoneType.MOBILE;

    @Builder.Default
    @Column(nullable = false)
    private String number = "(61)98888-9999";
}
