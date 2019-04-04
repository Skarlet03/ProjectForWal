import java.util.ArrayList;
import java.util.Arrays;

public class Rating {
    ArrayList<Integer[]> possibleCombinations;
    public  Rating (ArrayList<Integer[]> possibleCombinations){
        this.possibleCombinations = possibleCombinations;
        npsList = new double[possibleCombinations.size()];
        neutralList = new double[possibleCombinations.size()];
        avarageList = new double[possibleCombinations.size()];
        timing = new int[possibleCombinations.size()];
        rates = new ArrayList<Integer[]>();
    }

    double[] npsList;
    double[] neutralList;
    double[] avarageList;
    int[] timing;
    ArrayList<Integer[]> rates;


        //calculating how much time it will take
        public void calculateRates(){
        for (int i = 0; i < possibleCombinations.size(); i++) {
            int sum = possibleCombinations.get(i)[0];
            System.out.println("Before modification " + Arrays.toString(possibleCombinations.get(i)));
            for (int j = 1; j < possibleCombinations.get(i).length; j++) {
                (possibleCombinations.get(i))[j] = (possibleCombinations.get(i))[j] + (possibleCombinations.get(i))[j - 1];
                sum += possibleCombinations.get(i)[j];
            }
            Integer[] ratesArray = new Integer[possibleCombinations.get(i).length];
            for (int j = 0; j < possibleCombinations.get(i).length; j++) {
                ratesArray[j] = rate((possibleCombinations.get(i))[j]);
            }
            rates.add(ratesArray);
            neutralList[i] = neutralAndAbove(rates.get(i));
            npsList[i] = nps(rates.get(i));
            avarageList[i] = averageRates(rates.get(i));
            timing[i] = sum;
            //System.out.println("After modification: " + Arrays.toString(possibleCombinations.get(i)));
            //System.out.println("rates: " + Arrays.toString(rates.get(i)));
            //System.out.println("total time: " + i + " " + sum);
        }

    }

    private static Integer rate(int time){

        int[] timeFrames = {600, 540, 480, 420, 360, 300, 240, 180, 120, 60, 0};
        if (time>=timeFrames[0]) return 0;
        for (int i = 1; i < timeFrames.length; i++) {
            if (time >= timeFrames[i] && time < timeFrames[i - 1]) {
                return i;
            }
        }
        return 0;
    }

    private static double nps(Integer[] rates){
        int promotersCount = 0;
        int detractorsCount = 0;
        for (int i = 0; i < rates.length; i++) {
            if (rates[i]>=9) promotersCount++;
            else if (rates[i] <=6) detractorsCount++;
        }
        double percentage = ((promotersCount/rates.length) - (detractorsCount/rates.length));
        //System.out.println("%" + percentage );
        return percentage;
    }

    private static double neutralAndAbove (Integer[] rates){
        int rateCount = 0;
        for (int i = 0; i < rates.length; i++) {
            if (rates[i]>=7) rateCount++;
        }

        //System.out.println("neutral" + rateCount );
        return rateCount;
    }

    private static double averageRates (Integer[] rates){
        int rateCount = 0;
        for (int i = 0; i < rates.length; i++) {
            rateCount += rates[i];
        }
        double average = rateCount/rates.length;
        // System.out.println("average" + average );
        return average;
    }

    public Integer [] bestSchedule(){
        Integer [] max = possibleCombinations.get(0);
        int idx = 0;
        for (int i = 1; i < possibleCombinations.size(); i++) {
            if (npsList[i] >= npsList[idx]) {
                if (neutralList[i] >= neutralList[idx] && (avarageList[i] >= avarageList[idx]) && timing[i] <= timing[idx]) {
                    idx = i;
                    max = possibleCombinations.get(i);

                }
            }
        }
        return max;
    }
}
