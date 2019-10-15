package seedu.address.model.timetable;

import seedu.address.commons.exceptions.IllegalValueException;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Objects;

public class TimeRange implements Comparable<TimeRange> {
    private final DayOfWeek dayStart;
    private final DayOfWeek dayEnd;
    private final LocalTime timeStart;
    private final LocalTime timeEnd;

    public TimeRange(DayOfWeek dayStart, DayOfWeek dayEnd, LocalTime timeStart, LocalTime timeEnd) throws IllegalValueException {
        if (!rangeIsValid(timeStart, timeEnd)) {
            throw new IllegalValueException("timeStart should be earlier than timeEnd");
        }
        this.dayStart = dayStart;
        this.dayEnd = dayEnd;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
    }

    public int getDurationInHours() {
        return (dayEnd.getValue() - dayStart.getValue()) * 24 + timeEnd.getHour() - timeStart.getHour();
    }

    public DayOfWeek getDayStart() {
        return dayStart;
    }

    public DayOfWeek getDayEnd() {
        return dayEnd;
    }

    public LocalTime getTimeStart() {
        return timeStart;
    }

    public LocalTime getTimeEnd() {
        return timeEnd;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TimeRange timeRange = (TimeRange) o;
        return dayStart == timeRange.dayStart
                && timeStart.equals(timeRange.timeStart)
                && timeEnd.equals(timeRange.timeEnd);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dayStart, dayEnd, timeStart, timeEnd);
    }

    /**
     * Compare by start time.
     * @param other Other TimeRange to compare with.
     * @return Negative if start earlier, positive if start later.
     */
    @Override
    public int compareTo(TimeRange other) {
        if (this.dayStart.getValue() != other.dayStart.getValue()) {
            return this.dayStart.getValue() - other.dayStart.getValue();
        }
        return this.timeStart.compareTo(other.timeStart);
    }

    public boolean overlap(TimeRange timeRange) {
        return this.getDayStart() == timeRange.getDayStart()
                && this.timeStart.compareTo(timeRange.getTimeEnd()) < 0
                && this.timeEnd.compareTo(timeRange.getTimeStart()) > 0;
    }

    private static boolean rangeIsValid(LocalTime start, LocalTime end) {
        return start.compareTo(end) < 0;
    }
}
