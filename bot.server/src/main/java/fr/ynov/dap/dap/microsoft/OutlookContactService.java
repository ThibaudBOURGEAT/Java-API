package fr.ynov.dap.dap.microsoft;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.ynov.dap.dap.auth.AuthHelper;
import fr.ynov.dap.dap.data.AppUser;
import fr.ynov.dap.dap.data.OutlookAccount;
import fr.ynov.dap.dap.data.Token;
import fr.ynov.dap.dap.google.GoogleAccountService;
import fr.ynov.dap.dap.model.OutlookContact;
import fr.ynov.dap.dap.model.OutlookPagedResult;
import fr.ynov.dap.dap.repository.AppUserRepository;

/**
 * The Class OutlookContactService.
 */
@Service
public class OutlookContactService {
	
	/** The log. */
	private final Logger LOG = LogManager.getLogger(GoogleAccountService.class);

	/** The app user repo. */
	@Autowired
	private AppUserRepository appUserRepo;
	
	/**
	 * Gets the nb contacts.
	 *
	 * @param account the account
	 * @return the nb contacts
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	private Integer getNbContacts(final OutlookAccount account) throws IOException {

        if (account == null) {
        	LOG.warn("MicrosoftAccount is null");
            return 0;
        }

        String sort = "GivenName ASC";
        String properties = "GivenName,Surname,CompanyName,EmailAddresses";

        Token tokens = AuthHelper.ensureTokens(account.getToken(), account.getTenantId());

        OutlookService outlookService = OutlookServiceBuilder
        		.getOutlookService(tokens.getAccessToken());
        OutlookPagedResult<OutlookContact> contacts = outlookService
        		.getContacts(sort, properties)
        		.execute()
        		.body();

        if (contacts != null && contacts.getValue() != null) {
            return contacts.getValue().length;
        }
        
        LOG.warn("No contacts available for outlook account : " + account.getName());

        return 0;
    }

  
    /**
     * Gets the nb contacts for all accounts.
     *
     * @param userKey the user key
     * @return the nb contacts for all accounts
     * @throws IOException Signals that an I/O exception has occurred.
     */
    public final Integer getNbContactsForAllAccounts(final String userKey) throws IOException {
    	
    	AppUser appUser = appUserRepo.findByName(userKey);
    	
        if (appUser.getOutlookAccounts().size() == 0) {
        	LOG.warn("Current user " + appUser.getName() + " haven't any outlook account");
            return 0;
        }

        Integer nbContacts = 0;

        for (OutlookAccount account : appUser.getOutlookAccounts()) {
            Integer nb = getNbContacts(account);
            nbContacts += nb;
        }

        return nbContacts;
    }
}
