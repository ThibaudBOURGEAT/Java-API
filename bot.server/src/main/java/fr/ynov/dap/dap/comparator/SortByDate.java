package fr.ynov.dap.dap.comparator;

import java.util.Comparator;

import fr.ynov.dap.dap.model.GoogleCalendarResponse;

/**
 * The Class SortByDate.
 */
public class SortByDate implements Comparator<GoogleCalendarResponse>{

	/* (non-Javadoc)
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	@Override
    public int compare(final GoogleCalendarResponse event1, final GoogleCalendarResponse event2) {
        return event1.getStart().compareTo(event2.getStart());
    }
	
}
