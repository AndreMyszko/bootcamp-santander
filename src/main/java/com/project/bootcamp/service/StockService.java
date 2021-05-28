package com.project.bootcamp.service;

import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import com.project.bootcamp.exceptions.BusinessException;
import com.project.bootcamp.mapper.StockMapper;
import com.project.bootcamp.model.Stock;
import com.project.bootcamp.model.dto.StockDTO;
import com.project.bootcamp.repository.StockRepository;
import com.project.bootcamp.util.MessageUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service //service decides when call or not a repository when called by a controller *orchestrator
public class StockService {

    @Autowired
    private StockRepository repository;

    //we need to use mapper to transform our DTO object to an Entity object, that is the way to enable Repository accept the received object
    @Autowired
    private StockMapper mapper;

    @Transactional //open and close transaction -- garants rollback
    public StockDTO save(StockDTO dto) { //come DTO have no ID
        //\/ using exception handler \/
        Optional<Stock> optionalStock = repository.findByNameAndDate(dto.getName(), dto.getDate()); //verification springdata (select * where * -- findByNameAndDate() = repository method with class attributes "name" and "date")
        if(optionalStock.isPresent()){ //is this register alredy present?
            throw new BusinessException(MessageUtils.STOCK_ALREADY_EXISTIS); //using support classes: Exceptions and Messages...
        }

        Stock stock = mapper.toEntity(dto); //making conversion
        repository.save(stock); //saving repository obj
        return mapper.toDto(stock); //returning dto to controller -> returning DTO already have ID!!
    }

    @Transactional
    public StockDTO update(StockDTO dto) {
        Optional<Stock> optionalStock = repository.findByStockUpdate(dto.getName(), dto.getDate(), dto.getId()); //id check data integrity for update (only one) (SQL: <> = different)
        if(optionalStock.isPresent()){
            throw new BusinessException(MessageUtils.STOCK_ALREADY_EXISTIS);
        }

        Stock stock = mapper.toEntity(dto);
        repository.save(stock);
        return mapper.toDto(stock);
    }
    

}
