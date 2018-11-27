package fr.ynov.dap.dap.google;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.ynov.dap.dap.data.AppUser;
import fr.ynov.dap.dap.repository.AppUserRepository;

@Service
public class AppUserService {

	@Autowired
	private AppUserRepository appUserRepo;
	private final Logger LOG = LogManager.getLogger(GoogleAccountService.class);
	
	public Map<String, Object> addUser(final String userKey){
		Map<String, Object> response = new HashMap<String, Object>();
		if(appUserRepo.findByName(userKey) == null) {
			AppUser appUser = new AppUser();
			appUser.setName(userKey);
			appUserRepo.save(appUser);

			response.put("Success", true);
			response.put("Message", "User has been save.");
		}else {
			response.put("Success", false);
			response.put("Message", "User already exist.");
		}
		return response;
	}
}
