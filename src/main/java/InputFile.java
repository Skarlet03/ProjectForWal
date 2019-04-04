import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Calendar;
import java.util.Date;

public class InputFile {
    private static StringBuilder extractedString;
    static {
         extractedString = new StringBuilder("");
    }
    /**
     * @param pathToFile
     * Extract the data from a txt file
     */
    public InputFile(String pathToFile){
        extractedString = Utils.fileReader(pathToFile);
        orders = extractedString.toString().replace("\"","").split("Newline");
        orderNumber = new String[orders.length];
        coordinates = new String[orders.length];
        OrderPlacedTimeStamp = new String[orders.length][3];

    }

    //creating an array with info for each order

    private String[] orders;
    private String [] orderNumber;
    private String[] coordinates;
    private String[][] OrderPlacedTimeStamp;


    //creating an array for each component of an order

    String temp;
    //TODO comment here initialize instances
    public void setUpOrderComponents(){
        for (int i = 0; i < orders.length; i++) {
            temp = orders[i].substring(0,6).trim();
                if (temp.matches("WM(\\d{1,4})")) orderNumber[i] = temp;
                else throw new UnsupportedFormatException("ERROR: The order number has the wrong format: " + temp);
            temp = orders[i].substring(orders[i].indexOf(" ")+1, orders[i].lastIndexOf(" "));
                //TODO add regex to match
                if (temp.matches("[S|N](\\d+)[W|E](\\d+)")) coordinates[i] = temp; //TODO
                else throw new UnsupportedFormatException("ERROR: The coordinates have the wrong format: " + temp);
            OrderPlacedTimeStamp[i] = orders[i].substring(orders[i].lastIndexOf(" ")+1).split(":");
            //TODO Think of the map here order as a key -> coordinates as a value
        }
    }

    public String[] getOrderNumber() {
        return orderNumber;
    }

    public String[] getCoordinates() {
        return coordinates;
    }

    public String[][] getOrderPlacedTimeStamp() {
        for (String eachTimeStamp: orderNumber) {
            //TODO
            if (true) continue; //TODO regex for: eachTimeStamp.matches("HH:MM:SS")
            else throw new UnsupportedFormatException("ERROR: The order time has the wrong format: " + eachTimeStamp);
        }
        return OrderPlacedTimeStamp;
    }

    //TODO TIME
    Calendar ca1 = Calendar.getInstance();

    //splitting the order to each different coordinatesNumbers

    //======================================================
    //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    //TODO parse exception for the wrong date or time (PUTED IN BLOCK {} to avoid errors for now)
    /*
    Date[] time = new Date[orders.length];

    {
        for (int i = 0; i <orders.length; i++) {
            ca1.set(Calendar.HOUR_OF_DAY, Integer.parseInt(OrderPlacedTimeStamp[i][0]));
            ca1.set(Calendar.MINUTE, Integer.parseInt(OrderPlacedTimeStamp[i][1]));
            ca1.set(Calendar.SECOND, Integer.parseInt(OrderPlacedTimeStamp[i][2]));
            time[i] = ca1.getTime();
        }

        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 6);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);

        Date d = cal.getTime();
        d.setTime(d.getTime() + 60000); //<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
        //System.out.println(Arrays.toString(orderNumber));
        //System.out.println(Arrays.toString(coordinates));
        //System.out.println(Arrays.toString(time));
    }
    */

}
