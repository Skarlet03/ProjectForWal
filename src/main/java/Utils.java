import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Utils {

    static ArrayList<Integer[]> list2 = new ArrayList<Integer[]>();
    public static ArrayList<Integer[]> possibleCombinations(
            int length, int[] elements) {
        if (length<1){
            System.out.println("No orders has been placed");
        }else if(length == 1) {
            //System.out.println("-->" + Arrays.toString(elements));
            Integer[] integers = wrapArray(elements);
            // System.out.println("integers " + Arrays.toString(integers));
            list2.add(integers);
            //System.out.println(list2.toString());
        } else {
            for(int i = 0; i < length-1; i++) {
                possibleCombinations(length - 1, elements);
                if(length % 2 == 0) {
                    swap(elements, i, length-1);
                } else {
                    swap(elements, 0, length-1);
                }
            }
            possibleCombinations(length - 1, elements);
        }

        return list2;
    }

    /**
     * @param pathToFile
     * @return extractedString
     * Extract the data from a txt file
     */
    public static StringBuilder fileReader(String pathToFile){
        StringBuilder extractedString= new StringBuilder("");
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(pathToFile)));
            String line;
            while ((line = br.readLine()) != null) {
                extractedString.append(line).append("Newline");
            }
            System.out.println(extractedString);
            br.close();
        } catch (IOException e) {
            System.out.println("ERROR: File was not found. Unable to read file " + pathToFile);
            e.printStackTrace();
        }
        return extractedString;
    }

    private static void swap(int[] input, int a, int b) {
        int tmp = input[a];
        input[a] = input[b];
        input[b] = tmp;
    }

    private static Integer[] wrapArray(int[] input) {
        Integer[] nums = new Integer[input.length];
        for(int i = 0; i < input.length; i++) {
            nums[i] = input[i];
        }
        return nums;
    }

    /**
     * Building an coordinatesNumbers of int as a total time in MINUTES for each order by ignoring the coordinates direction and adding blocks together
     * then multiply by 2 (both ways same time)
     * @param coordinates
     * @return minutes
     *
     */
    public static int[] timeToDeliveryInMinutes(String[] coordinates) {
        //TODO WAS(int [] minutes = new int[orders.length];)
        String [] coordinatesNumbers = new String[2];
        String temp = "";
        int [] minutes = new int[coordinates.length];
        for (int i = 0; i < coordinates.length; i++) {
            temp=coordinates[i].substring(1);
            for (int j = 0; j < coordinates[i].length(); j++) {
                if (Character.isLetter(coordinates[i].charAt(j))) {
                    coordinatesNumbers = temp.split(coordinates[i].charAt(j) + "");
                }
            }
            minutes[i] = (Integer.parseInt(coordinatesNumbers[1]) + Integer.parseInt(coordinatesNumbers[0])) * 2;
        }
        return minutes;
    }


    //TODO PLAN FOR 04/02/2019 <<<<<<<<<<<<<<<<<<<<<<<<
    //TODO Change order# + time(time needed for delivery) to Map format and only then do allPossibleCombinations
    //TODO Util for time change (note the schedule is 6am-10pm) -> Date d = cal.getTime();
    //        d.setTime(d.getTime() + 60000); <--- will add one minute
    //TODO write to the file and print the path
    //TODO organize everything in the packages <-- implement clear code by using pojos to read from the file add comments and explain text
    //TODO logs and report
    //TODO unit tests
    //TODO to run with a maven pass the path to the file as an argument
    //TODO write the readme file (add screenshot of the framework and point with the arrow to explain what is the purpose of each class)

}
