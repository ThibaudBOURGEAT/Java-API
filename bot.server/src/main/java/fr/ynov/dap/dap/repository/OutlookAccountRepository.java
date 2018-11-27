package fr.ynov.dap.dap.repository;

import org.springframework.data.repository.CrudRepository;

import fr.ynov.dap.dap.data.OutlookAccount;


public interface OutlookAccountRepository extends CrudRepository<OutlookAccount, Integer> {
	OutlookAccount findByName(String name);
}
