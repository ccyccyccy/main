package seedu.address.model.timetable;

import seedu.address.commons.exceptions.IllegalValueException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TimeTable timeTable = (TimeTable) o;
        return timeRanges.equals(timeTable.timeRanges);
    }

    @Override
    public int hashCode() {
        return Objects.hash(timeRanges);
    }
}
