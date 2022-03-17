package com.luciana.challenge.mybank.dto.mapper;

import com.luciana.challenge.mybank.dto.request.BankDTO;
import com.luciana.challenge.mybank.entity.Bank;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface BankMapper {

    BankMapper INSTANCE = Mappers.getMapper(BankMapper.class);

    Bank toModel(BankDTO dto);

    BankDTO toDTO(Bank dto);

}
