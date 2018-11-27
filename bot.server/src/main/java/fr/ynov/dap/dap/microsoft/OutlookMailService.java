package fr.ynov.dap.dap.microsoft;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.ynov.dap.dap.auth.AuthHelper;
import fr.ynov.dap.dap.data.AppUser;
import fr.ynov.dap.dap.data.OutlookAccount;
import fr.ynov.dap.dap.data.Token;
import fr.ynov.dap.dap.google.GoogleAccountService;
import fr.ynov.dap.dap.model.MailFolder;
import fr.ynov.dap.dap.model.Message;
import fr.ynov.dap.dap.model.PagedResult;
import fr.ynov.dap.dap.repository.AppUserRepository;


@Service
public class OutlookMailService {

	@Autowired
	private AppUserRepository appUserRepo;
	
	private final Logger LOG = LogManager.getLogger(GoogleAccountService.class);

	public Map<String, Object> getMailForAllAccounts(String userKey) {
		Map<String, Object> response = new HashMap<String, Object>();
		List<String> users = new ArrayList<>();
		List<Message[]> messages = new ArrayList<>();
		AppUser appUser = appUserRepo.findByName(userKey);
		String folder = "inbox";
		String sort = "receivedDateTime DESC";
		String properties = "receivedDateTime,from,isRead,subject,bodyPreview";
		Integer maxResults = 10;
		
	    for(OutlookAccount account : appUser.getOutlookAccount()) {
	    	Token tokens = AuthHelper.ensureTokens(account.getToken(), account.getTenantId());	
	    	OutlookService outlookService = OutlookServiceBuilder
					.getOutlookService(tokens.getAccessToken());
	        try {
	        	PagedResult<Message> emails = outlookService.getMessages(folder, sort, properties, maxResults).execute()
						.body();
	        	users.add(account.getName());
	        	messages.add(emails.getValue());
	        }catch(IOException e) {
	        	LOG.error("Error when trying get mail for all accounts.",  e);
	        }
	    }
	    response.put("users", users);
	    response.put("messages", messages);
		
	    return response;
	}
	
	public int getNbMailInboxForAllAccount(String userKey) {
		AppUser appUser = appUserRepo.findByName(userKey);
		int totalMailInbox = 0;
		for(OutlookAccount account : appUser.getOutlookAccount()) {
			Token tokens = AuthHelper.ensureTokens(account.getToken(), account.getTenantId());	
	    	OutlookService outlookService = OutlookServiceBuilder
					.getOutlookService(tokens.getAccessToken());
			try {
	            MailFolder mailFolder = outlookService.getMailFolders("INBOX").execute().body();
	            totalMailInbox += mailFolder.getUnreadItemCount();
	        }catch(IOException e) {
	            LOG.error("Error when trying get number mail inbox for all accounts.",  e);
	        }
		}	
		return totalMailInbox;
	}
	/*AppUser appUser = repository.findByName(userid);
    List<OutlookAccount> otAccounts = appUser.getOutlookAccounts();
    Integer total = 0;
    for(int i=0; i < otAccounts.size(); i++) {
        OutlookService outlookService = OutlookServiceFactory.getOutlookService(otAccounts.get(i).getIdToken());
        try {
            MailFolder mailFolder = outlookService.getMailFolders("INBOX").execute().body();
            total += mailFolder.getUnreadItemCount();
        }catch(IOException e) {
            return -1;
        }

    }*/

}
