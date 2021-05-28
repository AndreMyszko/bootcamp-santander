package com.project.bootcamp.repository;

import java.time.LocalDate;
import java.util.Optional;

import com.project.bootcamp.model.Stock;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository //dependency injection
public interface StockRepository  extends JpaRepository<Stock, Long>{

    Optional<Stock> findByNameAndDate(String name, LocalDate date); //used by service for transactional validation (like: select STOCK from tb_stock where name=* and data=*)
}
