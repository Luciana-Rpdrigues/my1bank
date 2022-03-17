package com.luciana.challenge.mybank.entity;

import com.luciana.challenge.mybank.builder.ClientDTOBuilder;
import com.luciana.challenge.mybank.controller.BillController;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Bank {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = true)
    private List<BillController> bills;

    @Column(nullable = true)
    private List<ClientDTOBuilder> clients;


}
