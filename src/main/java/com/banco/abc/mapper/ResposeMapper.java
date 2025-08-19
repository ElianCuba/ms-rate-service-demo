package com.banco.abc.mapper;


import com.banco.abc.proxy.dto.ApiExchangeRateResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ResposeMapper {
    ResposeMapper INSTANCE = Mappers.getMapper(ResposeMapper.class);

    ApiExchangeRateResponse apiExchangeRateResponseTo (com.banco.abc.proxy.model.ApiExchangeRateResponse rateResponse);
}
