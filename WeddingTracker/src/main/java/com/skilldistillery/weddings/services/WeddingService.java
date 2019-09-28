package com.skilldistillery.weddings.services;

import java.util.List;

import com.skilldistillery.weddings.entities.Wedding;

public interface WeddingService {

	List<Wedding> index();

	Wedding show(Integer id);

	Wedding create(Wedding wedding);

	Wedding update(Wedding wedding, Integer id);

	Boolean delete(Integer weddingId);

	List<Wedding> getWeddingsWithinPriceRange(Double low, Double high);

}
