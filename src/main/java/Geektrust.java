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
        
        

        while (sc.hasNextLine()) {
            String[] input = sc.nextLine().split(" ");
            String output = "";
            LocalTime startTime = LocalTime.parse(input[1]);
            LocalTime endTime = LocalTime.parse(input[2]);
            Integer count = input.length == 4? Integer.parseInt(input[3]) :null;
//            output = processInput(input, startTime, endTime, count);
            if (!TimeUtil.isValidInput(startTime, endTime)) {
                output = "INCORRECT_INPUT";
                continue;
            }else if(TimeUtil.isBufferTime(startTime, endTime)){
                output =  "NO_VACANT_ROOM";
                continue;
            }
            
            output = evaluateInput(input[0], count, startTime, endTime);
            System.out.println(output);
            
        }
        
    }

    private static String processInput(String[] input, LocalTime startTime, LocalTime endTime, Integer count) {
        String output;
        if (!TimeUtil.isValidInput(startTime, endTime)) {
            output = "INCORRECT_INPUT";
        }else if(TimeUtil.isBufferTime(startTime, endTime)){
            output =  "NO_VACANT_ROOM";
        }
        else {
            output = evaluateInput(input[0], count, startTime, endTime);
        }
        return output;
    }

    static String evaluateInput(String action, Integer count, LocalTime startTime, LocalTime endTime){
         MakeSpaceService makeSpaceService = new MakeSpaceService();
         if (action.equals("BOOK")) {
            return makeSpaceService.bookRoom(count, startTime, endTime);
        }else{
            return  makeSpaceService.checkAvailability(startTime, endTime);
        }
    }
    
    
    
}


