package com.skilldistillery.weddings.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.weddings.entities.DJ;
import com.skilldistillery.weddings.repositories.DJRepository;

@Service
public class DJServiceImpl implements DJService {
	
	@Autowired
	private DJRepository djRepo;
	
	@Override
	public List<DJ> index() {
		return djRepo.findAll();
	}
	
	@Override
	public DJ show(Integer id) {
		Optional<DJ> djOpt = djRepo.findById(id);
		DJ dj = null;
		if (djOpt.isPresent()) {
			dj = djOpt.get();
		}
		return dj;

	}
	
	@Override
	public DJ create(DJ dj) {
		try {
			djRepo.saveAndFlush(dj);
		} catch (Exception e) {
			dj = null;
			e.printStackTrace();
		}
		return dj;
	}
	
	@Override
	public DJ update(DJ dj, Integer id) {
		DJ managedDJ = show(id);
		if (managedDJ != null) {
			managedDJ.setEmail(dj.getEmail());
			managedDJ.setFirstName(dj.getFirstName());
			managedDJ.setLastName(dj.getLastName());
			managedDJ.setPayPerGig(dj.getPayPerGig());
			managedDJ.setPhone(dj.getPhone());
			djRepo.saveAndFlush(managedDJ);
		}
		return managedDJ;
	}
	
	@Override
	public Boolean delete(Integer djId) {
		Boolean deleted = false;
		if (djRepo.existsById(djId)) {
			djRepo.deleteById(djId);
			deleted = true;
		}
		return deleted;
	}
}
