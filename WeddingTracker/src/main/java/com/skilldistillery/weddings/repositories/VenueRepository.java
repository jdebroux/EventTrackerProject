package com.skilldistillery.weddings.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.weddings.entities.Venue;

public interface VenueRepository extends JpaRepository<Venue, Integer>{

}
