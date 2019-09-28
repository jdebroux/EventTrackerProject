package com.skilldistillery.weddings.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.weddings.entities.Venue;
import com.skilldistillery.weddings.repositories.VenueRepository;

@Service
public class VenueServiceImpl implements VenueService {

	@Autowired
	private VenueRepository venRepo;
	
	@Override
	public List<Venue> index() {
		return venRepo.findAll();
	}
	
	@Override
	public Venue show(Integer id) {
		Optional<Venue> venueOpt = venRepo.findById(id);
		Venue venue = null;
		if (venueOpt.isPresent()) {
			venue = venueOpt.get();
		}
		return venue;

	}
	
	@Override
	public Venue create(Venue venue) {
		try {
			venRepo.saveAndFlush(venue);
		} catch (Exception e) {
			venue = null;
			e.printStackTrace();
		}
		return venue;
	}
	
	@Override
	public Venue update(Venue venue, Integer id) {
		Venue managedVenue = show(id);
		if (managedVenue != null) {
			managedVenue.setAddress(venue.getAddress());
			managedVenue.setContactFirstName(venue.getContactFirstName());
			managedVenue.setContactLastName(venue.getContactLastName());
			managedVenue.setName(venue.getName());
			managedVenue.setPhone(venue.getPhone());
			venRepo.saveAndFlush(managedVenue);
		}
		return managedVenue;
	}
	
	@Override
	public Boolean delete(Integer venueId) {
		Boolean deleted = false;
		if (venRepo.existsById(venueId)) {
			venRepo.deleteById(venueId);
			deleted = true;
		}
		return deleted;
	}
}
