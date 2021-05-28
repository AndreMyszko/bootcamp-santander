package com.project.bootcamp.mapper;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.project.bootcamp.model.Stock;
import com.project.bootcamp.model.dto.StockDTO;

import org.springframework.stereotype.Component;

//mapper is user to transform our DTO to ENTITY obj
@Component
public class StockMapper {

    //convert dto to entity
    public Stock toEntity(@Valid StockDTO dto) {
        Stock stock = new Stock();
        stock.setId(dto.getId());
        stock.setName(dto.getName());
        stock.setPrice(dto.getPrice());
        stock.setVariation(dto.getVariation());
        stock.setDate(dto.getDate());
        return stock;
    }

    //convert entity to dto
    public StockDTO toDto(Stock stock) {
        StockDTO dto = new StockDTO();
        dto.setId(stock.getId());
        dto.setName(stock.getName());
        dto.setPrice(stock.getPrice());
        dto.setVariation(stock.getVariation());
        dto.setDate(stock.getDate());
        return dto;
    }

    //toDto overload
    public List<StockDTO> toDto(List<Stock> listStock) {
        //transformation
        return listStock.stream().map(this::toDto).collect(Collectors.toList()); //use toDto single return to transform the stream list
    }

}
