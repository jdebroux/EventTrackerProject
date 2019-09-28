package com.skilldistillery.weddings.services;

import java.util.List;

import com.skilldistillery.weddings.entities.Venue;

public interface VenueService {

	List<Venue> index();

	Venue show(Integer id);

	Venue create(Venue venue);

	Venue update(Venue venue, Integer id);

	Boolean delete(Integer venueId);

}
