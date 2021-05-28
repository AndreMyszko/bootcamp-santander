package com.project.bootcamp.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.project.bootcamp.model.Stock;
import com.project.bootcamp.model.dto.StockDTO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository //dependency injection
public interface StockRepository  extends JpaRepository<Stock, Long>{

    //spring data jpa default method query
    Optional<Stock> findByNameAndDate(String name, LocalDate date); //used by service for transactional validation (like: select STOCK from tb_stock where name=* and data=*)

    //personalized query for UPDATE
    @Query("SELECT stock " +
    "FROM Stock stock " + 
    "WHERE stock.name = :name " + 
    "AND stock.date = :date " + 
    "AND stock.id <> :id ")
    Optional<Stock> findByStockUpdate(String name, LocalDate date, Long id);
    
    @Query("SELECT stock " +
            "FROM Stock stock " + 
            "WHERE stock.date = :date ") //CURRENT_DATE = postgre command to select current date.. we will not use it, so the querry can works with any SQL language
    Optional<List<Stock>> findByToday(LocalDate date);
}
