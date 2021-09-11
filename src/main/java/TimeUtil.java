
import java.time.LocalTime;
import java.util.ArrayList;

import java.util.Arrays;
import java.util.List;

public class TimeUtil {

    static List<List<LocalTime>> bufferTimes = new ArrayList<>();
    public static final int FIFTEEN_MINUTES = 15;


    public static boolean isValidInput(LocalTime startTime, LocalTime endTime) {
        if (startTime.getMinute() % FIFTEEN_MINUTES == 0 && endTime.getMinute() % FIFTEEN_MINUTES == 0 && startTime.isBefore(endTime)) {
            return true;
        }
        return false;
    }

    static LocalTime getTime(String time) { return LocalTime.parse(time); }

    public static boolean isSlotAvailable(LocalTime startTime, LocalTime endTime, List<List<LocalTime>> bookedSlots) {
        return !isStartTimeOverlap(startTime, bookedSlots) && !isEndTimeOverlap(startTime, endTime, bookedSlots);
    }

    private static boolean isStartTimeOverlap(LocalTime startTime, List<List<LocalTime>> timeIntervals) {
        for (List<LocalTime> interval : timeIntervals) {
            if ((startTime.equals(interval.get(0)) || startTime.isAfter(interval.get(0))) &&
                    startTime.isBefore(interval.get(1))) {
                return true;
            }
        }
        return false;
    }

    private static boolean isEndTimeOverlap(LocalTime startTime, LocalTime endTime, List<List<LocalTime>> timeIntervals) {
        for (List<LocalTime> interval : timeIntervals) {
            if ((interval.get(0).isAfter(startTime) || interval.get(0).equals(startTime)) && interval.get(0).isBefore(endTime)) {
                return true;
            }
        }
        return false;
    }

    public static void initialiseBufferTimes(List<List<String>> bufferTimeStrings) {
        for (List<String> timeInterval : bufferTimeStrings) {
            LocalTime t1 = getTime(timeInterval.get(0));
            LocalTime t2 = getTime(timeInterval.get(1));
            bufferTimes.add(Arrays.asList(t1, t2));
        }

    }

    public static boolean isBufferTime(LocalTime startTime, LocalTime endTIme) {
        return !isSlotAvailable(startTime, endTIme, bufferTimes);
    }

}


