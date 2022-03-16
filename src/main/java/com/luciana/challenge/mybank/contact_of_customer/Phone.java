package com.luciana.challenge.mybank.contact_of_customer;

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
public class Phone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id = 2L;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PhoneType type = PhoneType.MOBILE;

    @Column(nullable = false)
    private String number = "(61)98888-9999";
}
