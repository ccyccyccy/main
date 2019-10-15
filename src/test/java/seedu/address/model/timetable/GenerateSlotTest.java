package seedu.address.model.timetable;

import org.junit.jupiter.api.Test;
import seedu.address.commons.exceptions.IllegalValueException;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static seedu.address.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

public class GenerateSlotTest {
    @Test
    public void generate_validTimeRanges_generatesCorrectTimeslot() throws IllegalValueException {
        LocalTime start = LocalTime.parse("11:00");
        LocalTime end = LocalTime.parse("09:00");
        TimeTable timeTable = new TimeTable("MONDAY MONDAY 1300 1500");
        List<TimeTable> timeTables = new ArrayList<>();
        timeTables.add(timeTable);
        List<TimeRange> generated = GenerateSlot.generate(timeTables, 2, new TimeRange(DayOfWeek.MONDAY, DayOfWeek.FRIDAY, start, end));
        List<TimeRange> expected = new ArrayList<>();
        expected.add(new TimeRange(DayOfWeek.MONDAY, DayOfWeek.MONDAY, start, LocalTime.parse("13:00")));
        expected.add(new TimeRange(DayOfWeek.MONDAY, DayOfWeek.FRIDAY, LocalTime.parse("15:00"), end));
        assertEquals(generated, expected);
    }
}
