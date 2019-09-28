package com.skilldistillery.weddings.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.weddings.entities.Client;
import com.skilldistillery.weddings.entities.Wedding;
import com.skilldistillery.weddings.repositories.ClientRepository;

@Service
public class ClientServiceImpl implements ClientService {
	
	@Autowired
	private ClientRepository clientRepo;
	
	@Override
	public List<Client> index() {
		return clientRepo.findAll();
	}
	
	@Override
	public Client show(Integer id) {
		Optional<Client> clientOpt = clientRepo.findById(id);
		Client client = null;
		if (clientOpt.isPresent()) {
			client = clientOpt.get();
		}
		return client;

	}
	
	@Override
	public Client create(Client client) {
		try {
			if(client.getWedding() == null) {
				client.setWedding(new Wedding(1));
			}
			clientRepo.saveAndFlush(client);
		} catch (Exception e) {
			client = null;
			e.printStackTrace();
		}
		return client;
	}
	
	@Override
	public Client update(Client client, Integer id) {
		Client managedClient = show(id);
		if (managedClient != null) {
			managedClient.setEmail(client.getEmail());
			managedClient.setFirstName(client.getFirstName());
			managedClient.setLastName(client.getLastName());
			managedClient.setPhone(client.getPhone());
			clientRepo.saveAndFlush(managedClient);
		}
		return managedClient;
	}
	
	@Override
	public Boolean delete(Integer clientId) {
		Boolean deleted = false;
		if (clientRepo.existsById(clientId)) {
			clientRepo.deleteById(clientId);
			deleted = true;
		}
		return deleted;
	}
}
