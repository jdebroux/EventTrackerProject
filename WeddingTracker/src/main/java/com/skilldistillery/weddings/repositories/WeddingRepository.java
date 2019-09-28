package com.skilldistillery.weddings.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.weddings.entities.Wedding;

public interface WeddingRepository extends JpaRepository<Wedding, Integer>{

	List<Wedding> findByTotalCostBetween(Double low, Double high);

}
