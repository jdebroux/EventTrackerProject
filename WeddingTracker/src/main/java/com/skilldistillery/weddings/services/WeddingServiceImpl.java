package com.skilldistillery.weddings.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.weddings.entities.Wedding;
import com.skilldistillery.weddings.repositories.WeddingRepository;

@Service
public class WeddingServiceImpl implements WeddingService {

	@Autowired
	private WeddingRepository weddRepo;
	
	@Override
	public List<Wedding> index() {
		return weddRepo.findAll();
	}
	
	@Override
	public Wedding show(Integer id) {
		Optional<Wedding> weddingOpt = weddRepo.findById(id);
		Wedding wedding = null;
		if (weddingOpt.isPresent()) {
			wedding = weddingOpt.get();
		}
		return wedding;

	}
	
	@Override
	public Wedding create(Wedding wedding) {
		try {
			weddRepo.saveAndFlush(wedding);
		} catch (Exception e) {
			wedding = null;
			e.printStackTrace();
		}
		return wedding;
	}
	
	@Override
	public Wedding update(Wedding wedding, Integer id) {
		Wedding managedWedding = show(id);
		if (managedWedding != null) {
			managedWedding.setBookingDate(wedding.getBookingDate());
			managedWedding.setCelebrationDate(wedding.getCelebrationDate());
			managedWedding.setTotalCost(wedding.getTotalCost());
			managedWedding.setUpLighting(wedding.getUpLighting());
			managedWedding.setVenue(wedding.getVenue());
			managedWedding.setNotes(wedding.getNotes());
			weddRepo.saveAndFlush(managedWedding);
		}
		return managedWedding;
	}
	
	@Override
	public Boolean delete(Integer weddingId) {
		Boolean deleted = false;
		if (weddRepo.existsById(weddingId)) {
			weddRepo.deleteById(weddingId);
			deleted = true;
		}
		return deleted;
	}
	
	@Override
	public List<Wedding> getWeddingsWithinPriceRange(Double low, Double high) {
		return weddRepo.findByTotalCostBetween(low, high);
	}

}
