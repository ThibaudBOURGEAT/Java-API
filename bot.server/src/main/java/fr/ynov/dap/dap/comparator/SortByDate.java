package fr.ynov.dap.dap.comparator;

import java.util.Comparator;

import fr.ynov.dap.dap.model.GoogleCalendarResponse;

public class SortByDate implements Comparator<GoogleCalendarResponse>{

	@Override
    public int compare(final GoogleCalendarResponse event1, final GoogleCalendarResponse event2) {
        return event1.getStart().compareTo(event2.getStart());
    }
	
}
