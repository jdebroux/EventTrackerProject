package com.skilldistillery.weddings.services;

import java.util.List;

import com.skilldistillery.weddings.entities.Client;

public interface ClientService {

	List<Client> index();

	Client show(Integer id);

	Client create(Client client);

	Client update(Client client, Integer id);

	Boolean delete(Integer clientId);

}
