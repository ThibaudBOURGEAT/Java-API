package fr.ynov.dap.dap.web;

import java.io.IOException;
import java.security.GeneralSecurityException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.ynov.dap.dap.exeption.NoEventExeption;
import fr.ynov.dap.dap.google.CalendarService;
import fr.ynov.dap.dap.model.GoogleCalendarResponse;



/**
 * The Class CalendarController.
 */
@RestController
@RequestMapping("/calendar")
public class CalendarController {

	/** The calendar service. */
	@Autowired
	private CalendarService calendarService;
	
	/**
	 * Gets the next event.
	 *
	 * @param userId the user id
	 * @return the next event
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws GeneralSecurityException the general security exception
	 * @throws NoEventExeption 
	 */
	@RequestMapping("/nextEvent")
	public GoogleCalendarResponse getNextEvent(@RequestParam("userKey") final String userId) throws
	IOException, GeneralSecurityException, NoEventExeption {
		return calendarService.getNextEventAllAccount(userId);
	}
	
}
