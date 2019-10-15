package seedu.address.model.timetable;

import seedu.address.commons.exceptions.IllegalValueException;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.*;

public class GenerateSlot {
    public static List<TimeRange> generate(List<TimeTable> timeTables, int numberOfHours, TimeRange userSpecifiedTimeRange) throws IllegalValueException {
        List<TimeRange> uniqueTimeRanges = filterUniqueTimeRanges(timeTables);
        // TODO: Add algorithm to generate timing from the timeRanges
        List<TimeRange> merged = mergeOverlappingTimeRanges(uniqueTimeRanges);
        List<TimeRange> inverted = invertTimeRange(merged);
        List<TimeRange> truncated = truncateTimeRange(inverted, userSpecifiedTimeRange);
        return getSuitableTimeRanges(truncated, numberOfHours);
    }

    public static List<TimeRange> filterUniqueTimeRanges(List<TimeTable> timeTables) {
        Set<TimeRange> timeRanges = new HashSet<>();
        for (TimeTable timeTable : timeTables) {
            timeRanges.addAll(timeTable.getTimeRanges());
        }
        return new ArrayList<>(timeRanges);
    }

    public static List<TimeRange> mergeOverlappingTimeRanges(List<TimeRange> timeRanges) throws IllegalValueException {
        Collections.sort(timeRanges);
        List<TimeRange> merged = new ArrayList<>();
        for (TimeRange timeRange : timeRanges) {
            if (merged.isEmpty()) {
                merged.add(timeRange);
                continue;
            }
            TimeRange latest = merged.get(merged.size() - 1);
            if (latest.getTimeEnd().compareTo(timeRange.getTimeStart()) >= 0) { // If last TimeRange in merged overlaps with new latest
                TimeRange tr = merge(timeRange, latest);
                merged.set(merged.size() - 1, tr);
            } else {
                merged.add(timeRange);
            }
        }
        return null;
    }

    /**
     * Merge 2 TimeRange together, assuming that they overlap.
     * @param r1 TimeRange 1.
     * @param r2 TimeRange 2.
     * @return new merged TimeRange.
     * @throws IllegalValueException If error in creating new TimeRange.
     */
    private static TimeRange merge(TimeRange r1, TimeRange r2) throws IllegalValueException {
        return new TimeRange(r1.getDayStart(), r2.getDayEnd(), r1.getTimeStart(), r2.getTimeEnd());
    }

    public static List<TimeRange> invertTimeRange(List<TimeRange> timeRanges) throws IllegalValueException {
        // Start from MONDAY 0000, to SUNDAY 2359
        List<TimeRange> inverted = new ArrayList<>();
        DayOfWeek curDay = DayOfWeek.MONDAY;
        LocalTime curTime = LocalTime.parse("0000");
        for (TimeRange timeRange : timeRanges) {
            TimeRange toAdd = new TimeRange(curDay, timeRange.getDayStart(), curTime, timeRange.getTimeStart());
            inverted.add(toAdd);
            curDay = timeRange.getDayEnd();
            curTime = timeRange.getTimeEnd();
        }
        inverted.add(new TimeRange(curDay, DayOfWeek.SUNDAY, curTime, LocalTime.parse("2359")));
        return inverted;
    }

    public static List<TimeRange> truncateTimeRange(List<TimeRange> timeRanges, TimeRange limit) throws IllegalValueException {
        List<TimeRange> truncated = new ArrayList<>();
        for (TimeRange timeRange : timeRanges) {
            if (timeRange.getTimeEnd().compareTo(limit.getTimeStart()) <= 0 // End before before start
                    || timeRange.getTimeStart().compareTo(limit.getTimeEnd()) >= 0) { // Start after end
                continue;
            }
            LocalTime timeStart = timeRange.getTimeStart().isBefore(limit.getTimeStart())
                    ? limit.getTimeStart()
                    : timeRange.getTimeStart();
            DayOfWeek dayStart = timeRange.getDayStart().getValue() < limit.getDayStart().getValue()
                    ? limit.getDayStart()
                    : timeRange.getDayStart();
            LocalTime timeEnd = timeRange.getTimeEnd().isAfter(limit.getTimeEnd())
                    ? limit.getTimeEnd()
                    : timeRange.getTimeEnd();
            DayOfWeek dayEnd = timeRange.getDayEnd().getValue() > limit.getDayEnd().getValue()
                    ? limit.getDayEnd()
                    : timeRange.getDayEnd();
            truncated.add(new TimeRange(dayStart, dayEnd, timeStart, timeEnd));
        }
        return truncated;
    }

    public static List<TimeRange> getSuitableTimeRanges(List<TimeRange> timeRanges, int numberOfHours) {
        List<TimeRange> possibleRanges = new ArrayList<>();
        for (TimeRange timeRange : timeRanges) {
            if (timeRange.getDurationInHours() >= numberOfHours) {
                possibleRanges.add(timeRange);
            }
        }
        return possibleRanges;
    }

}
