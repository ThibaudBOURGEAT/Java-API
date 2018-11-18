package fr.ynov.dap.dap.web;

import java.io.IOException;
import java.security.GeneralSecurityException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.ynov.dap.dap.model.GmailModel;
import fr.ynov.dap.dap.service.GMailService;

/**
 * The Class GmailController.
 */
@RestController
@RequestMapping(value="/mail")
public class GmailController {

	/** The gmail service. */
	@Autowired
	private GMailService gmailService;
	
	/**
	 * Gets the mail inbox un read.
	 *
	 * @param userId the user id
	 * @return the mail inbox un read
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws GeneralSecurityException the general security exception
	 */
	@RequestMapping(value="/inbox")
	public int getMailInboxUnRead(@RequestParam("userKey") final String userKey) throws
	IOException, GeneralSecurityException {
		return gmailService.getNbMailInboxAllAccount(userKey);
	}
}
