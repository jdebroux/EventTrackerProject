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

import com.skilldistillery.weddings.entities.Client;
import com.skilldistillery.weddings.services.ClientService;

@RequestMapping("api")
@RestController
public class ClientController {

	@Autowired
	private ClientService serv;

	@GetMapping("clients")
	public List<Client> getAllClients() {
		return serv.index();
	}

	@GetMapping("clients/{id}")
	public Client getClientById(@PathVariable Integer id, HttpServletResponse resp) {
		Client client = serv.show(id);
		if (client == null) {
			resp.setStatus(404);
		}
		return client;
	}

	@PostMapping("clients")
	public Client persistClient(@RequestBody Client client, HttpServletRequest req, HttpServletResponse resp) {
		try {
			serv.create(client);
			resp.setStatus(201);
			StringBuffer url = req.getRequestURL();
			url.append("/");
			url.append(client.getId());
			resp.setHeader("Location", url.toString());
		} catch (Exception e) {
			resp.setStatus(400);
			client = null;
			e.printStackTrace();
		}
		return client;
	}

	@PutMapping("clients/{id}")
	public Client updateClient(@RequestBody Client client, @PathVariable int id, HttpServletResponse resp) {
		try {
			client = serv.update(client, id);
			if (client == null) {
				resp.setStatus(404);
			}
		} catch (Exception e) {
			resp.setStatus(400);
			client = null;
			e.printStackTrace();
		}
		return client;
	}

	@DeleteMapping("clients/{id}")
	public void deleteClient(@PathVariable int id, HttpServletResponse resp) {
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