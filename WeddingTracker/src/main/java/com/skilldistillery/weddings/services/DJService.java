package com.skilldistillery.weddings.services;

import java.util.List;

import com.skilldistillery.weddings.entities.DJ;

public interface DJService {

	List<DJ> index();

	DJ show(Integer id);

	DJ create(DJ dj);

	DJ update(DJ dj, Integer id);

	Boolean delete(Integer djId);

}
