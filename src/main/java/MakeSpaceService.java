import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class MakeSpaceService {
    static final int CAVE_CAPACITY = 3;
    static final int TOWER_CAPACITY = 7;
    static final int MANSION_CAPACITY = 20;
    static final int MIN_CAPACITY = 2;
    static final int MAX_CAPACITY= 20;

    static Room cave = new Room(CAVE_CAPACITY, "C-Cave");
    static Room tower = new Room(TOWER_CAPACITY, "D-Tower");
    static Room mansion = new Room(MANSION_CAPACITY, "G-Mansion");


    public String bookRoom(Integer count, LocalTime startTime, LocalTime endTime) {
        String output = "NO_VACANT_ROOM";
        
        if (count >= MIN_CAPACITY && count <= MAX_CAPACITY) {
            List<Room> eligibleRooms = getEligibleRooms(count);
            return getBookedRoom(startTime, endTime, eligibleRooms);
        }

        return output;

    }

    private List<Room> getEligibleRooms(Integer count) {
        List<Room> eligibleRooms = new ArrayList<>();
        if (count <= CAVE_CAPACITY) {
            eligibleRooms.addAll(Arrays.asList(cave, tower, mansion));
        } else if (count > CAVE_CAPACITY && count <= TOWER_CAPACITY) {
            eligibleRooms.addAll(Arrays.asList(tower, mansion));
        } else {
            eligibleRooms.add(mansion);
        }
        return eligibleRooms;

    }

    public String checkAvailability(LocalTime startTime, LocalTime endTime) {
        String output = "";
        List<Room> rooms = Arrays.asList(cave, tower, mansion);
        for (Room room : rooms) {
            if (room.isSlotAvailable(startTime, endTime)) {
                output += room.getName();
                output += " ";
            }


        }
        if (output.isEmpty()) {
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