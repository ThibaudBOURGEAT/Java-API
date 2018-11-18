package fr.ynov.dap.dap.repository;

import org.springframework.data.repository.CrudRepository;

import fr.ynov.dap.dap.data.AppUser;

public interface AppUserRepository extends CrudRepository<AppUser, Integer>{
	
	AppUser findByName(String name);
}
