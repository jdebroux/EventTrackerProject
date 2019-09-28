package com.skilldistillery.weddings.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.weddings.entities.Venue;
import com.skilldistillery.weddings.services.VenueService;

@RequestMapping("api")
@RestController
public class VenueController {

	@Autowired
	private VenueService serv;

	@GetMapping("venues")
	public List<Venue> getAllVenues() {
		return serv.index();
	}

	@GetMapping("venues/{id}")
	public Venue getVenueById(@PathVariable Integer id, HttpServletResponse resp) {
		Venue venue = serv.show(id);
		if (venue == null) {
			resp.setStatus(404);
		}
		return venue;
	}

	@PostMapping("venues")
	public Venue persistVenue(@RequestBody Venue venue, HttpServletRequest req, HttpServletResponse resp) {
		try {
			serv.create(venue);
			resp.setStatus(201);
			StringBuffer url = req.getRequestURL();
			url.append("/");
			url.append(venue.getId());
			resp.setHeader("Location", url.toString());
		} catch (Exception e) {
			resp.setStatus(400);
			venue = null;
			e.printStackTrace();
		}
		return venue;
	}

	@PutMapping("venues/{id}")
	public Venue updateVenue(@RequestBody Venue venue, @PathVariable int id, HttpServletResponse resp) {
		try {
			venue = serv.update(venue, id);
			if (venue == null) {
				resp.setStatus(404);
			}
		} catch (Exception e) {
			resp.setStatus(400);
			venue = null;
			e.printStackTrace();
		}
		return venue;
	}

	@DeleteMapping("venues/{id}")
	public void deleteVenue(@PathVariable int id, HttpServletResponse resp) {
		try {
			Boolean deleted = serv.delete(id);
			if (deleted) {
				resp.setStatus(204);
			} else {
				resp.setStatus(404);
			}
		} catch (Exception e) {
			resp.setStatus(400);
			e.printStackTrace();
		}
	}
}