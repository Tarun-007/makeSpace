
import lombok.Data;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Data
public class Room {
    private Integer capacity;
    private String name;
    private List<Pair<LocalTime,LocalTime>> bookedTimeSlots;
    Room(int capacity, String name){
        this.capacity = capacity;
        this.name = name;
        this.bookedTimeSlots = new ArrayList<>();
    }
    
    public boolean isSlotAvailable(LocalTime startTime, LocalTime endTime){
       return TimeUtil.isSlotAvailable(startTime,endTime,bookedTimeSlots);
    }
    
    public void bookSlot(LocalTime startTime, LocalTime endtTime){
        this.bookedTimeSlots.add(new Pair(startTime,endtTime));
    }
    
    
}
