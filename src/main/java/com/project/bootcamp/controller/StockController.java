package com.project.bootcamp.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import com.project.bootcamp.model.dto.StockDTO;
import com.project.bootcamp.service.StockService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//EndPoint
@RestController
@RequestMapping(value = "/stock") //controller only knows DTO classes, never entity classes
public class StockController {

    @Autowired //open-close cycle
    private StockService service; //instantiate service connection

    //INSERT
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE) // send and return JSON
    public ResponseEntity<StockDTO> save(@Valid @RequestBody StockDTO dto){ //@Valid to @NotNull on DTO class
        return ResponseEntity.ok(service.save(dto)); //send status 200OK and recive DTO just to run the project.
    }

    //UPDATE
    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StockDTO> update (@Valid @RequestBody StockDTO dto){
        return ResponseEntity.ok(service.update(dto));
    }

    //FIND ALL
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE) //get, only send..
    public ResponseEntity<List<StockDTO>> findAll(){ //return all DTO list
        return ResponseEntity.ok(service.findAll());
    }

    //FIND BY ID
    @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StockDTO> findById(@PathVariable Long id){
        return ResponseEntity.ok(service.findById(id));
    }

    //DELETE
    @DeleteMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE) //this method have a -return dto object- with info of deleted register
    public ResponseEntity<StockDTO> delete(@PathVariable Long id){
        return ResponseEntity.ok(service.delete(id));
    }

}
