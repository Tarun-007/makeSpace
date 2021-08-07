
import java.time.LocalTime;
import java.util.ArrayList;

import java.util.List;
import java.util.regex.Pattern;

public class TimeUtil {
    
    static Pattern p = Pattern.compile("^([0-1][0-9]|2[0-3]):[0-5][0-9]$");
    static List<Pair<LocalTime,LocalTime>> bufferTimes = new ArrayList<>();
    
    
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
    
    public static boolean isSlotAvailable(LocalTime startTime, LocalTime endTime, List<Pair<LocalTime,LocalTime>> unavailableTimeSlots){
        return firstOverlap(startTime,unavailableTimeSlots) && secondOverlap(startTime,endTime,unavailableTimeSlots);
    }

    private static boolean firstOverlap(LocalTime startTime, List<Pair<LocalTime, LocalTime>> timeIntervals) { 
        for(Pair<LocalTime,LocalTime> interval : timeIntervals){
            if((startTime.equals(interval.getFirst()) || startTime.isAfter(interval.getFirst())) &&
                    startTime.isBefore(interval.getSecond()) ){
                return false;
            }
        }
        return true;
    }

    private static boolean secondOverlap(LocalTime startTime, LocalTime endTime, List<Pair<LocalTime, LocalTime>> timeIntervals) {
        for(Pair<LocalTime,LocalTime> interval : timeIntervals){
            if((interval.getFirst().isAfter(startTime) || interval.getFirst().equals(startTime) ) && interval.getFirst().isBefore(endTime)){
                return false;
            }
        }
        return true;
    }

    public  static void initialiseBufferTimes(List<List<String>>  bufferTimeStrings){
        for(List<String> timeInterval : bufferTimeStrings){
            LocalTime t1 = getTime(timeInterval.get(0));
            LocalTime t2 = getTime(timeInterval.get(1));
            bufferTimes.add(new Pair(t1,t2));
        }
        
    }
    
    public  static boolean isBufferTime(LocalTime startTime, LocalTime endTIme){
        return !isSlotAvailable(startTime,endTIme,bufferTimes);
    }

}


