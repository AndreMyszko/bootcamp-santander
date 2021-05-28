package com.project.bootcamp.service;

import java.util.List;
import java.util.Optional;

import com.project.bootcamp.exceptions.BusinessException;
import com.project.bootcamp.exceptions.NotFoundException;
import com.project.bootcamp.mapper.StockMapper;
import com.project.bootcamp.model.Stock;
import com.project.bootcamp.model.dto.StockDTO;
import com.project.bootcamp.repository.StockRepository;
import com.project.bootcamp.util.MessageUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service //service decides when call or not a repository when called by a controller *orchestrator
public class StockService {

    @Autowired
    private StockRepository repository;

    //we need to use mapper to transform our DTO object to an Entity object, that is the way to enable Repository accept the received object
    @Autowired
    private StockMapper mapper;

    //INSERT
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

    //UPDATE
    @Transactional
    public StockDTO update(StockDTO dto) {
        Optional<Stock> optionalStock = repository.findByStockUpdate(dto.getName(), dto.getDate(), dto.getId()); //id check data integrity for update (only one) (SQL: <> = different)
        if(optionalStock.isPresent()){
            throw new BusinessException(MessageUtils.STOCK_ALREADY_EXISTIS);
        }

        Stock stock = mapper.toEntity(dto);
        repository.save(stock);
        return mapper.toDto(stock); //single dto return (CTRL+CLICK on toDto method to check)
    }

    //FIND ALL
    @Transactional(readOnly = true)
    public List<StockDTO> findAll() { //return a dto list to controller
        return mapper.toDto(repository.findAll()); // JPA method findAll() // list traformation method to recive dto as list (CTRL+CLICK on toDto method to check)
    }

    @Transactional(readOnly = true)
    public StockDTO findById(Long id) {
        return repository.findById(id).map(mapper::toDto).orElseThrow(NotFoundException::new); //throwing new instance of an exception class to receive return string
    }
    

}
