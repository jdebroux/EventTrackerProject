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

import com.skilldistillery.weddings.entities.Wedding;
import com.skilldistillery.weddings.services.WeddingService;

@RequestMapping("api")
@RestController
public class WeddingController {

	@Autowired
	private WeddingService serv;

	@GetMapping("ping")
	public String ping() {
		return "pong";
	}

	@GetMapping("weddings")
	public List<Wedding> getAllWeddings() {
		return serv.index();
	}

	@GetMapping("weddings/{id}")
	public Wedding getWeddingById(@PathVariable Integer id, HttpServletResponse resp) {
		Wedding wedding = serv.show(id);
		if (wedding == null) {
			resp.setStatus(404);
		}
		return wedding;
	}

	@PostMapping("weddings")
	public Wedding persistWeddng(@RequestBody Wedding wedding, HttpServletRequest req, HttpServletResponse resp) {
		try {
			serv.create(wedding);
			resp.setStatus(201);
			StringBuffer url = req.getRequestURL();
			url.append("/");
			url.append(wedding.getId());
			resp.setHeader("Location", url.toString());
		} catch (Exception e) {
			resp.setStatus(400);
			wedding = null;
			e.printStackTrace();
		}
		return wedding;
	}

	@PutMapping("weddings/{id}")
	public Wedding updateWedding(@RequestBody Wedding wedding, @PathVariable int id, HttpServletResponse resp) {
		try {
			wedding = serv.update(wedding, id);
			if (wedding == null) {
				resp.setStatus(404);
			}
		} catch (Exception e) {
			resp.setStatus(400);
			wedding = null;
			e.printStackTrace();
		}
		return wedding;
	}

	@DeleteMapping("weddings/{id}")
	public void deleteWedding(@PathVariable int id, HttpServletResponse resp) {
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
	
	@GetMapping("weddings/search/totalcost/{low}/{high}")
	public List<Wedding> getWeddingsWithinRange(@PathVariable Double low, @PathVariable Double high, HttpServletResponse resp){
		List<Wedding> weddings = serv.getWeddingsWithinPriceRange(low, high);
		if(weddings.size() == 0) {
			resp.setStatus(404);
		}
		return weddings;
		
	}
}