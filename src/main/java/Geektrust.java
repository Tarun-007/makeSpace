import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Geektrust {
    
    public static void main (String[] args) throws FileNotFoundException {
        
        String filePath = args[0];
        File file = new File(filePath);
        Scanner sc = new Scanner(file);
        List<List<String>> bufferTimeList = Arrays.
                asList(Arrays.asList("09:00","09:15"),
                        Arrays.asList("13:15","13:45"),
                        Arrays.asList("18:45","19:00"));
        
        TimeUtil.initialiseBufferTimes(bufferTimeList);
        
        MakeSpaceService makeSpace = new MakeSpaceService();

        while (sc.hasNextLine()) {
            String[] input = sc.nextLine().split(" ");
            String output = "";
            LocalTime startTime = LocalTime.parse(input[1]);
            LocalTime endTime = LocalTime.parse(input[2]);
            
            if (!TimeUtil.validateInput(input[1], input[2])) {
                output = "INCORRECT_INPUT";
            }else if(TimeUtil.isBufferTime(startTime,endTime)){
                output =  "NO_VACANT_ROOM";
            }
            else if (input[0].equals("BOOK")) {
                int count = Integer.parseInt(input[3].trim());
                output = makeSpace.bookRoom(count, startTime, endTime);
            }else if (input[0].equals("VACANCY")) {
                output = makeSpace.checkAvailability(startTime, endTime);
            }
            
            System.out.println(output);
            
        }
        
    }
    
    
    
}


