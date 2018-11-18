package fr.ynov.dap.dap.web;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.api.client.auth.oauth2.StoredCredential;
import com.google.api.client.util.store.DataStore;

import fr.ynov.dap.dap.service.GMailService;

@Controller
public class Welcome {

	/** The gmail service. */
	@Autowired
	private GMailService gmailService;
	
	

	@RequestMapping("/{userId}")
	public String welcome(ModelMap model, @PathVariable final String userId)
			throws IOException, GeneralSecurityException {
		model.addAttribute("nbEmails", gmailService.getNbMailInbox(userId));
		return "welcome";
	}

	@RequestMapping("/data")
	public String returnDataStore(final ModelMap model) {
		try {
			Map<String, Object> dataStore = new HashMap<String, Object>();
			DataStore<StoredCredential> credentials = gmailService.getFlow().getCredentialDataStore();

			for (String key : credentials.keySet()) {
				Map<String, Object> userData = new HashMap<String, Object>();
				StoredCredential values = credentials.get(key);
				userData.put("accessToken", values.getAccessToken());
				userData.put("refreshToken", values.getRefreshToken());
				userData.put("expirationTimeMilliseconds", values.getExpirationTimeMilliseconds());

				dataStore.put(key, userData);
			}
			model.addAttribute("dataStore", dataStore);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "data";
	}

}
