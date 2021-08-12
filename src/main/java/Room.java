
import lombok.Data;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Data
public class Room {
    private Integer capacity;
    private String name;
    private List<List<LocalTime>> bookedSlots;
    Room(int capacity, String name){
        this.capacity = capacity;
        this.name = name;
        this.bookedSlots = new ArrayList<>();
    }
    
    public boolean isSlotAvailable(LocalTime startTime, LocalTime endTime){
       return TimeUtil.isSlotAvailable(startTime,endTime, bookedSlots);
    }
    
    public void bookSlot(LocalTime startTime, LocalTime endtTime){
        this.bookedSlots.add(Arrays.asList(startTime, endtTime));
    }
    
    
}
