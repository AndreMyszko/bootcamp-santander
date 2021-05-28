package com.project.bootcamp.repository;

import com.project.bootcamp.model.Stock;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository //dependency injection
public interface StockRepository  extends JpaRepository<Stock, Long>{
}
