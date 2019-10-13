package seedu.address.model.timetable;

import java.util.ArrayList;
import java.util.List;

public class GenerateSlot {
    public static TimeRange generate(List<TimeTable> timeTables) {
        List<TimeRange> timeRanges = new ArrayList<>();
        for (TimeTable timeTable : timeTables) {
            timeRanges.addAll(timeTable.getTimeRanges());
        }
        // TODO: Add algorithm to generate timing from the timeRanges
    }
}
