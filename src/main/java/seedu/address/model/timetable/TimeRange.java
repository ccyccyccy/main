package seedu.address.model.timetable;

import seedu.address.commons.exceptions.IllegalValueException;

import java.time.DayOfWeek;
import java.time.LocalTime;

public class TimeRange {
    private final DayOfWeek day;
    private final LocalTime startTime;
    private final LocalTime endTime;

    public TimeRange(DayOfWeek day, LocalTime startTime, LocalTime endTime) throws IllegalValueException {
        if (!rangeIsValid(startTime, endTime)) {
            throw new IllegalValueException("StartTime should be earlier than endTime");
        }
        this.day = day;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public DayOfWeek getDay() {
        return day;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public boolean overlap(TimeRange timeRange) {
        return this.getDay() == timeRange.getDay()
                && this.startTime.compareTo(timeRange.getEndTime()) < 0
                && this.endTime.compareTo(timeRange.getStartTime()) > 0;
    }

    private static boolean rangeIsValid(LocalTime start, LocalTime end) {
        return start.compareTo(end) < 0;
    }
}
