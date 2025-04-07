package worker.schedule;

import static check.CheckThat.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.*;
import org.junit.jupiter.api.extension.*;
import org.junit.jupiter.params.*;
import org.junit.jupiter.params.provider.*;
import check.*;

import java.util.HashSet;
import java.util.ArrayList;
import java.util.List;

public class WorkerScheduleTest {
    @Test
    public void emptySchedule() {
        WorkerSchedule ws = new WorkerSchedule();

        assertFalse(ws.isWorkingOnWeek("name1", 0));
        assertFalse(ws.isWorkingOnWeek("name2", 1));
    }

    @Test
    public void schedule() {
        WorkerSchedule ws = new WorkerSchedule();

        HashSet<String> workers1 = new HashSet<String>(List.of("name1", "name2"));
        ws.add(3, workers1);
        ws.add(13, workers1);

        assertFalse(ws.isWorkingOnWeek("name1", 0));
        assertFalse(ws.isWorkingOnWeek("name1", 1));
        assertFalse(ws.isWorkingOnWeek("name2", 0));
        assertFalse(ws.isWorkingOnWeek("name2", 1));
        assertTrue(ws.isWorkingOnWeek("name1", 3));
        assertTrue(ws.isWorkingOnWeek("name1", 13));
        assertTrue(ws.isWorkingOnWeek("name2", 3));
        assertTrue(ws.isWorkingOnWeek("name2", 13));

        HashSet<Integer> weeks = new HashSet<>(List.of(1, 2, 3));
        ArrayList<String> workers2 = new ArrayList<>(List.of("name3", "name4"));

        ws.add(weeks, workers2);
        assertFalse(ws.isWorkingOnWeek("name1", 0));
        assertFalse(ws.isWorkingOnWeek("name2", 0));
        assertFalse(ws.isWorkingOnWeek("name1", 0));
        assertFalse(ws.isWorkingOnWeek("name2", 0));

        assertFalse(ws.isWorkingOnWeek("name1", 1));
        assertFalse(ws.isWorkingOnWeek("name2", 1));
        assertTrue(ws.isWorkingOnWeek("name3", 1));
        assertTrue(ws.isWorkingOnWeek("name4", 1));

        assertFalse(ws.isWorkingOnWeek("name1", 2));
        assertFalse(ws.isWorkingOnWeek("name2", 2));
        assertTrue(ws.isWorkingOnWeek("name3", 2));
        assertTrue(ws.isWorkingOnWeek("name4", 2));

        assertTrue(ws.isWorkingOnWeek("name1", 3));
        assertTrue(ws.isWorkingOnWeek("name2", 3));
        assertTrue(ws.isWorkingOnWeek("name1", 3));
        assertTrue(ws.isWorkingOnWeek("name2", 3));

        assertTrue(ws.isWorkingOnWeek("name1", 13));
        assertTrue(ws.isWorkingOnWeek("name2", 13));
        assertFalse(ws.isWorkingOnWeek("name1", 13));
        assertFalse(ws.isWorkingOnWeek("name2", 13));

        HashSet<Integer> excepted1 = new HashSet<>(List.of(3, 13));
        HashSet<Integer> excepted3 = new HashSet<>(List.of(1, 2, 3));

        asserEquals(excepted1, ws.getWorkWeeks("name1"));
        asserEquals(excepted3, ws.getWorkWeeks("name3"));
    }
}
