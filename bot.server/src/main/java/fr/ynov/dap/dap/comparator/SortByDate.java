package fr.ynov.dap.dap.comparator;

import java.util.Comparator;

import fr.ynov.dap.dap.model.CalendarModel;

public class SortByDate implements Comparator<CalendarModel>{

	@Override
    public int compare(final CalendarModel event1, final CalendarModel event2) {
        return event1.getStart().compareTo(event2.getStart());
    }
	
}
