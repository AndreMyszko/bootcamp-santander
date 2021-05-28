package com.project.bootcamp.service;

import javax.transaction.Transactional;
import javax.validation.Valid;

import com.project.bootcamp.mapper.StockMapper;
import com.project.bootcamp.model.Stock;
import com.project.bootcamp.model.dto.StockDTO;
import com.project.bootcamp.repository.StockRepository;

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
        Stock stock = mapper.toEntity(dto); //making conversion
        repository.save(stock); //saving repository obj
        return mapper.toDto(stock); //returning dto to controller -> returning DTO already have ID!!
    }
    

}
