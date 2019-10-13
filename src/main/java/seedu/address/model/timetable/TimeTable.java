package seedu.address.model.timetable;

import seedu.address.commons.exceptions.IllegalValueException;

import java.util.ArrayList;
import java.util.List;

public class TimeTable {
    private List<TimeRange> timeRanges;

    /**
     * Takes in a formatted string of timings.
     * @param timetable Newline separated TIMEOFWEEK HHMM timings for a timetable
     */
    public TimeTable(String timetable) throws IllegalValueException {
        timeRanges = new ArrayList<>();
        for (String line : timetable.split("\n")) {
            timeRanges.add(TimeRangeParser.parseTimeRange(line));
        }
    }

    public List<TimeRange> getTimeRanges() {
        return timeRanges;
    }
}
