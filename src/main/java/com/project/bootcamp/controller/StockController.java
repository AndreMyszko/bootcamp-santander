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
    private StockService service; //Service connection

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE) // send and return JSON
    public ResponseEntity<StockDTO> save(@Valid @RequestBody StockDTO dto){ //@Valid to @NotNull on DTO class
        return ResponseEntity.ok(service.save(dto)); //send status 200OK and recive DTO just to run the project.
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StockDTO> update (@Valid @RequestBody StockDTO dto){
        return ResponseEntity.ok(service.update(dto));
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE) //get, only send..
    public ResponseEntity<List<StockDTO>> findAll(){ //return all DTO list

        //temporary list created for this part, it will change
        List<StockDTO> list = new ArrayList<>(); 
        StockDTO dto = new StockDTO();
        dto.setId(1L);
        dto.setName("SANTANDER DEV WEEK");
        dto.setPrice(100D);
        dto.setVariation(10D);
        dto.setDate(LocalDate.now());
        list.add(dto);

        return ResponseEntity.ok(list);
    }

    @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StockDTO> findById(@PathVariable Long id){
        
        //temporary list created for this part, it will change
        List<StockDTO> list = new ArrayList<>(); 

        StockDTO dto1 = new StockDTO();
        dto1.setId(1L);
        dto1.setName("SANTANDER DEV WEEK");
        dto1.setPrice(100D);
        dto1.setVariation(10D);
        dto1.setDate(LocalDate.now());
        
        StockDTO dto2 = new StockDTO();
        dto2.setId(2L);
        dto2.setName("DIGITAL INNOVATION ONE");
        dto2.setPrice(100D);
        dto2.setVariation(10D);
        dto2.setDate(LocalDate.now());
        
        StockDTO dto3 = new StockDTO();
        dto3.setId(3L);
        dto3.setName("SPRING BOOT");
        dto3.setPrice(100D);
        dto3.setVariation(10D);
        dto3.setDate(LocalDate.now());

        list.add(dto1);
        list.add(dto2);
        list.add(dto3);

        StockDTO dtoSelected = list.stream().filter(x -> x.getId().compareTo(id) == 0).findFirst().get();//lambda to list above | when id = 0 == id = equal
        return ResponseEntity.ok(dtoSelected);
    }

}
