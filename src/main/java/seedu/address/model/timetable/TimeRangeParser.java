package seedu.address.model.timetable;

import seedu.address.commons.exceptions.IllegalValueException;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class TimeRangeParser {
    public static TimeRange parseTimeRange(String line) throws IllegalValueException {
        String[] split = line.split(" ");
        DayOfWeek day = DayOfWeek.valueOf(split[0]);
        LocalTime startTime = LocalTime.parse(split[1], DateTimeFormatter.ofPattern("HHmm"));
        LocalTime endTime = LocalTime.parse(split[2], DateTimeFormatter.ofPattern("HHmm"));
        return new TimeRange(day, startTime, endTime);
    }
}
