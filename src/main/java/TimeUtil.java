
import java.time.LocalTime;
import java.util.ArrayList;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class TimeUtil {
    
    static Pattern p = Pattern.compile("^([0-1][0-9]|2[0-3]):[0-5][0-9]$");
    static List<List<LocalTime>> bufferTimes = new ArrayList<>();
    
    
    public static boolean validateInput(String t1, String t2){
        if(p.matcher(t1).matches() && p.matcher(t2).matches()  ){
            LocalTime startTime = getTime(t1);
            LocalTime endTime = getTime(t2);
            if(startTime.getMinute()%15 == 0 && endTime.getMinute()%15 == 0 && startTime.isBefore(endTime) ){
                return true;
            }else{
                return false;
            }
        }
        return false;
    }

    static LocalTime getTime(String time){
        return LocalTime.parse(time);
    }
    
    public static boolean isSlotAvailable(LocalTime startTime, LocalTime endTime, List<List<LocalTime>> unavailableTimeSlots){
        return !startTimeOverlap(startTime,unavailableTimeSlots) && !endTimeOverlap(startTime,endTime,unavailableTimeSlots);
    }

    private static boolean startTimeOverlap(LocalTime startTime, List<List<LocalTime>> timeIntervals) { 
        for(List<LocalTime> interval : timeIntervals){
            if((startTime.equals(interval.get(0)) || startTime.isAfter(interval.get(0))) &&
                    startTime.isBefore(interval.get(1)) ){
                return true;
            }
        }
        return false;
    }

    private static boolean endTimeOverlap(LocalTime startTime, LocalTime endTime, List<List<LocalTime>> timeIntervals) {
        for(List<LocalTime> interval : timeIntervals){
            if((interval.get(0).isAfter(startTime) || interval.get(0).equals(startTime) ) && interval.get(0).isBefore(endTime)){
                return true;
            }
        }
        return false;
    }

    public  static void initialiseBufferTimes(List<List<String>>  bufferTimeStrings){
        for(List<String> timeInterval : bufferTimeStrings){
            LocalTime t1 = getTime(timeInterval.get(0));
            LocalTime t2 = getTime(timeInterval.get(1));
            bufferTimes.add(Arrays.asList(t1,t2));
        }
        
    }
    
    public  static boolean isBufferTime(LocalTime startTime, LocalTime endTIme){
        return !isSlotAvailable(startTime,endTIme,bufferTimes);
    }

}


