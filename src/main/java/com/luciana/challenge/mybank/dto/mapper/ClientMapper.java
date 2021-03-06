package com.luciana.challenge.mybank.dto.mapper;

import com.luciana.challenge.mybank.builder.ClientDTOBuilder;
import com.luciana.challenge.mybank.dto.request.ClientDTO;



import com.luciana.challenge.mybank.entity.Client;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ClientMapper {

    ClientMapper INSTANCE = Mappers.getMapper(ClientMapper.class);

    Client toModel(ClientDTO dto);

    ClientDTO toDTO(Client dto);
}
