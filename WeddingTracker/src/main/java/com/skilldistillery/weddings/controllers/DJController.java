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

import com.skilldistillery.weddings.entities.DJ;
import com.skilldistillery.weddings.services.DJService;

@RequestMapping("api")
@RestController
public class DJController {

	@Autowired
	private DJService serv;

	@GetMapping("djs")
	public List<DJ> getAllDJs() {
		return serv.index();
	}

	@GetMapping("djs/{id}")
	public DJ getVenueById(@PathVariable Integer id, HttpServletResponse resp) {
		DJ dj = serv.show(id);
		if (dj == null) {
			resp.setStatus(404);
		}
		return dj;
	}

	@PostMapping("djs")
	public DJ persistDJ(@RequestBody DJ dj, HttpServletRequest req, HttpServletResponse resp) {
		try {
			serv.create(dj);
			resp.setStatus(201);
			StringBuffer url = req.getRequestURL();
			url.append("/");
			url.append(dj.getId());
			resp.setHeader("Location", url.toString());
		} catch (Exception e) {
			resp.setStatus(400);
			dj = null;
			e.printStackTrace();
		}
		return dj;
	}

	@PutMapping("djs/{id}")
	public DJ updateDJ(@RequestBody DJ dj, @PathVariable int id, HttpServletResponse resp) {
		try {
			dj = serv.update(dj, id);
			if (dj == null) {
				resp.setStatus(404);
			}
		} catch (Exception e) {
			resp.setStatus(400);
			dj = null;
			e.printStackTrace();
		}
		return dj;
	}

	@DeleteMapping("djs/{id}")
	public void deleteDJ(@PathVariable int id, HttpServletResponse resp) {
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