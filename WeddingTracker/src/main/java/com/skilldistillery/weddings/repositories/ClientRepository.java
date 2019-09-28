package com.skilldistillery.weddings.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.weddings.entities.Client;

public interface ClientRepository extends JpaRepository<Client, Integer>{

}
