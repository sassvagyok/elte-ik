package worker.schedule;

import java.util.HashMap;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.Map;

public class WorkerSchedule {
    private HashMap<Integer, HashSet<String>> weekToWorkers;

    public WorkerSchedule() {
        weekToWorkers = new HashMap<>();
    }

    public void add(int week, HashSet<String> names) {
        if (weekToWorkers.keySet().contains(Integer.valueOf(week))) {
            HashSet<String> currentlyWorking = weekToWorkers.get(Integer.valueOf(week));

            currentlyWorking.addAll(names);
        } else {
            weekToWorkers.put(Integer.valueOf(week), new HashSet<String>(names));
        }
    }

    public void add(HashSet<Integer> weeks, ArrayList<String> workers) {
        HashSet<String> workerAsSet = new HashSet<>(workers);

        for (Integer week : weeks) {
            add(week.intValue(), workerAsSet);
        }
    }

    public boolean isWorkingOnWeek(String name, int week) {
        HashSet<String> currentlyWorking = weekToWorkers.get(Integer.valueOf(week));

        if (currentlyWorking == null) {
            return false;
        } else {
            return currentlyWorking.contains(name);
        }
    }

    public HashSet<Integer> getWorkWeeks(String name) {
        HashSet<Integer> weeks = new HashSet<>();

        for (Map.Entry<Integer, HashSet<String>> entry : weekToWorkers.entrySet()) {
            if (entry.getValue().contains(name)) {
                weeks.add(entry.getKey());
            }
        }
    }
}