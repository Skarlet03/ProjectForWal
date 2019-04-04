
import java.util.ArrayList;
import java.util.Arrays;


public class DroneDelivery {

    public static void main(String[] args) {

        String filePath = "C:\\Users\\Mira Pavliuk\\Desktop\\walmart.txt"; //TODO Must to provide a link as a global variable
        InputFile inputFile = new InputFile(filePath);

        String[] orderNumber = inputFile.getOrderNumber();
        String[] coordinates = inputFile.getCoordinates();
        String[][] OrderPlacedTimeStamp = inputFile.getOrderPlacedTimeStamp();
        inputFile.setUpOrderComponents();

        int[] deliveryTimeDuration = Utils.timeToDeliveryInMinutes(coordinates);

        ArrayList<Integer[]> possibleCombinations = Utils.possibleCombinations(orderNumber.length, deliveryTimeDuration);

        Rating rating = new Rating(possibleCombinations);
        rating.calculateRates();
        System.out.println(Arrays.toString(rating.bestSchedule()));


    /*
    BufferedReader br = new BufferedReader(new FileReader(new File("Filepath")));
    BufferedWriter bw = new BufferedWriter(new FileWriter(new File("Filepath")));
    String l;
    while((l=br.readLine())!=null){

        ... do stuff ...

        bw.write("what you did");

    }

    bw.close();
     */


    }

}
