package fr.ynov.dap.dap.google;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.ynov.dap.dap.data.AppUser;
import fr.ynov.dap.dap.repository.AppUserRepository;

/**
 * The Class AppUserService.
 */
@Service
public class AppUserService {

	/** The app user repo. */
	@Autowired
	private AppUserRepository appUserRepo;
	
	/**
	 * Adds the user.
	 *
	 * @param userKey the user key
	 * @return the map
	 */
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
