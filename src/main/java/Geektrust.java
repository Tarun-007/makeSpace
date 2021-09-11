import com.sun.crypto.provider.HmacMD5KeyGenerator;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Geektrust {

    static final int CAVE_CAPACITY = 3;
    static final int TOWER_CAPACITY = 7;
    static final int MANSION_CAPACITY = 20;
    
    static final int MIN_CAPACITY = 2;
    static final int MAX_CAPACITY = 20;
    
    public static void main (String[] args) throws FileNotFoundException {
        
        String filePath = args[0];
        File file = new File(filePath);
        Scanner sc = new Scanner(file);
        List<List<String>> bufferTimeList = Arrays.
                asList(Arrays.asList("09:00","09:15"),
                        Arrays.asList("13:15","13:45"),
                        Arrays.asList("18:45","19:00"));
        
         MakeSpaceService makeSpaceService = new MakeSpaceService(MIN_CAPACITY,MAX_CAPACITY);
         Room cave = new Room(CAVE_CAPACITY, "C-Cave");
         Room tower = new Room(TOWER_CAPACITY, "D-Tower");
         Room mansion = new Room(MANSION_CAPACITY, "G-Mansion");
         makeSpaceService.setAvailableRooms(Arrays.asList(cave,tower,mansion));
         TimeUtil.initialiseBufferTimes(bufferTimeList);
        
        while (sc.hasNextLine()) {
            String[] input = sc.nextLine().split(" ");
            String output = "";
            LocalTime startTime = LocalTime.parse(input[1]);
            LocalTime endTime = LocalTime.parse(input[2]);
            String action = input[0];
            Integer count = input.length == 4? Integer.parseInt(input[3]) :null;
            
            if (!TimeUtil.isValidInput(startTime, endTime)) {
                output = "INCORRECT_INPUT";
            }else {
                output = Geektrust.evaluateInput(action, count, startTime, endTime,makeSpaceService);
            }
            System.out.println(output);
            
        }
        
    }
    

     static String evaluateInput(String action, Integer count, LocalTime startTime, LocalTime endTime,MakeSpaceService makeSpaceService){
         if (action.equals("BOOK")) {
            return makeSpaceService.bookRoom(count, startTime, endTime);
        }else{
            return  makeSpaceService.checkAvailability(startTime, endTime);
        }
    }
    
    
    
}


