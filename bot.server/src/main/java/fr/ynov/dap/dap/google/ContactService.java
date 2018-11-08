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
	 * @param userId the user id
	 * @return the service
	 * @throws GeneralSecurityException the general security exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	private PeopleService getService(String userId) throws GeneralSecurityException, IOException {
		final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
		PeopleService service = new PeopleService.Builder(HTTP_TRANSPORT, JACKSON_FACTORY,
				getCredentials(HTTP_TRANSPORT, userId)).setApplicationName(configuration.getApplicationName()).build();
		return service;
	}
	
	/**
	 * Gets the contacts.
	 *
	 * @param userId the user id
	 * @return the contacts
	 * @throws GeneralSecurityException the general security exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public int getNbContact(String userId) throws GeneralSecurityException, IOException {
		PeopleService service = getService(userId);     
		ListConnectionsResponse response = service.people().connections()
                .list("people/me")
                .setPersonFields("names,emailAddresses")
                .execute();

		return response.getTotalPeople();
	}
}
