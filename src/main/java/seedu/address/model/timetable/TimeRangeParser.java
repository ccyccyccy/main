package seedu.address.model.timetable;

import seedu.address.commons.exceptions.IllegalValueException;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class TimeRangeParser {
    public static TimeRange parseTimeRange(String line) throws IllegalValueException {
        String[] split = line.split(" ");
        DayOfWeek dayStart = DayOfWeek.valueOf(split[0]);
        DayOfWeek dayEnd = DayOfWeek.valueOf(split[1]);
        LocalTime startTime = LocalTime.parse(split[2], DateTimeFormatter.ofPattern("HHmm"));
        LocalTime endTime = LocalTime.parse(split[3], DateTimeFormatter.ofPattern("HHmm"));
        return new TimeRange(dayStart, dayEnd, startTime, endTime);
    }
}
