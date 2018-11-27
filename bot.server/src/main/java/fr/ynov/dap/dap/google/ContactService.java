package fr.ynov.dap.dap.google;

import java.io.IOException;
import java.security.GeneralSecurityException;

import org.springframework.stereotype.Service;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.services.people.v1.PeopleService;
import com.google.api.services.people.v1.model.ListConnectionsResponse;


/**
 * The Class ContactService.
 */
@Service
public class ContactService extends GoogleService {
	
	/**
	 * Instantiates a new contact service.
	 */
	public ContactService() {
		super();
	}
	
	/**
	 * Gets the service.
	 *
	 * @param accountName the user id
	 * @return the service
	 * @throws GeneralSecurityException the general security exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	private PeopleService getService(String accountName) throws GeneralSecurityException, IOException {
		final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
		PeopleService service = new PeopleService.Builder(HTTP_TRANSPORT, JACKSON_FACTORY,
				getCredentials(HTTP_TRANSPORT, accountName)).setApplicationName(configuration.getApplicationName()).build();
		return service;
	}
	
	/**
	 * Gets the contacts.
	 *
	 * @param accountName the user id
	 * @return the contacts
	 * @throws GeneralSecurityException the general security exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public int getNbContact(String accountName) throws GeneralSecurityException, IOException {
		PeopleService service = getService(accountName);     
		ListConnectionsResponse response = service.people().connections()
                .list("people/me")
                .setPersonFields("names,emailAddresses")
                .execute();

		return response.getTotalPeople();
	}
}
