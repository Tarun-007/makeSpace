import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class MakeSpaceService implements HotelService {

    
    
    private final int minCapacity;
    private final int maxCapacity;
    
    MakeSpaceService(int minCapacity, int maxCapacity){
        this.minCapacity = minCapacity;
        this.maxCapacity = maxCapacity;
    }


    List<Room> availableRooms;
    
    public List<Room> getAvailableRooms() {
        return availableRooms;
    }

    

    public void setAvailableRooms(List<Room> availableRooms) {
        this.availableRooms = availableRooms;
    }

    
    


    public String bookRoom(Integer count, LocalTime startTime, LocalTime endTime) {
        String output = "NO_VACANT_ROOM";
        
        if (count >= minCapacity && count <= maxCapacity) {
            List<Room> eligibleRooms = getEligibleRooms(count);
            return getBookedRoom(startTime, endTime, eligibleRooms);
        }

        return output;

    }

    private List<Room> getEligibleRooms(Integer count) {
        List<Room> eligibleRooms = new ArrayList<>();
        for(Room room : this.availableRooms){
            if(room.getCapacity() >= count){
                eligibleRooms.add(room);
            }
        }
        return eligibleRooms;

    }

    public String checkAvailability(LocalTime startTime, LocalTime endTime) {
        String output = "";
        for (Room room : this.availableRooms) {
            if (room.isSlotAvailable(startTime, endTime)) {
                output += room.getName();
                output += " ";
            }
        }
        if (output.isEmpty() || TimeUtil.isBufferTime(startTime, endTime)) {
            output = "NO_VACANT_ROOM";
        }
        return output.trim();
    }

    private String getBookedRoom(LocalTime startTime, LocalTime endTime, List<Room> rooms) {
        for (Room room : rooms) {
            if (room.isSlotAvailable(startTime, endTime)) {
                room.bookSlot(startTime, endTime);
                return room.getName();
            }
        }
        return "NO_VACANT_ROOM";
    }
}