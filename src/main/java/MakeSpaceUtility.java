import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class MakeSpaceUtility{
    static final int CAVE_CAPACITY = 3;
    static final int TOWER_CAPACITY = 7;
    static final int MANSION_CAPACITY = 20;

    static Room cave = new Room(CAVE_CAPACITY,"C-Cave");
    static Room tower = new Room(TOWER_CAPACITY, "D-Tower");
    static Room mansion = new Room(MANSION_CAPACITY, "G-Mansion");


    public   String bookRoom(String[] input, LocalTime startTime, LocalTime endTime) {
        String output =  "NO_VACANT_ROOM";;
        int count = Integer.parseInt(input[3].trim());
        if(count >=2 && count <= 20) {
            List<Room> eligibleRooms = getEligibleRooms(input[3]);
            return getBookedRoom(startTime, endTime, eligibleRooms);
        }

        return  output;

    }

    private   List<Room> getEligibleRooms( String count){
        Integer peopleCount = Integer.parseInt(count.trim());
        List<Room> eligibleRooms = new ArrayList<>();

        if(peopleCount <= CAVE_CAPACITY){
            eligibleRooms.addAll(Arrays.asList(cave,tower,mansion));
        }else if (peopleCount > CAVE_CAPACITY &&  peopleCount <= TOWER_CAPACITY){
            eligibleRooms.addAll(Arrays.asList(tower,mansion));
        }else {
            eligibleRooms.add(mansion);
        }

        return eligibleRooms;

    }

    public  String checkAvailability(LocalTime startTime, LocalTime endTime){
        String output = "";
        List<Room> rooms = Arrays.asList(cave,tower,mansion);
        for(Room room : rooms){
            if(room.isSlotAvailable(startTime,endTime)){
                output += room.getName();
                output += " ";
            }


        }
        if(output.isEmpty()){
            output = "NO_VACANT_ROOM";
        }
        return output.trim();
    }

    private   String getBookedRoom(LocalTime startTime, LocalTime endTime, List<Room> rooms){
        for(Room room : rooms){
            if(room.isSlotAvailable(startTime,endTime)){
                room.bookSlot(startTime,endTime);
                return  room.getName();
            }
        }
        return "NO_VACANT_ROOM";
    }
}