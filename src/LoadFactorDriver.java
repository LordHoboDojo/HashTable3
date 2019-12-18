import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class LoadFactorDriver {
   private static ArrayList<String> largeData;
   private static  ArrayList<String> successful;
    private static ArrayList<String> unsuccessful;
    public static void main(String[] args) throws IOException {
        successful = initList("successful.txt");
        unsuccessful = initList("unsuccessful.txt");
        largeData = initList("LargeData.txt");
        double[] loadFactors = new double[]{0.1, 0.5, 0.8, 0.9, 1.0};
        FileWriter writer = new FileWriter("jay.txt");

        for (double loadFactor:loadFactors){
            writer.append(String.valueOf( loadFactor)).append("\n");
            writer.append("Type of Hashing: Linear Probing\n");
            writer.append("Average insertion time: ").append(String.valueOf(measureInsertionPerformance(loadFactor))).append("\n");
            writer.append("Collisions: ").append(String.valueOf(measureCollisions(loadFactor))).append("\n");
            writer.append("Collisions per insertion: ").append(String.valueOf(measureCollisions(loadFactor)/500000.0)).append("\n");
            writer.append("Average Probes per successful search: ").append(String.valueOf(measureProbes(loadFactor,true))).append("\n");;
            writer.append("Average Probes per unsuccessful search: ").append(String.valueOf(measureProbes(loadFactor,false))).append("\n");;
        }
        writer.flush();
        writer.close();

    }
    private static double measureProbes(double loadFactor,boolean isSuccessful) throws FileNotFoundException {
        int size = (int) (500000.0/loadFactor);
        double sum = 0;
        HashTable table = new HashTable(size);
        for (String s : largeData){
            String[] dat = s.split(" ");
            table.put(dat[0],dat[1] + " " +dat[2]);
        }
        if (isSuccessful){
            for (String s : successful){
                String[] dat = s.split(" ");
                sum += table.getProbes(dat[0]);
            }
        }
        else
        {
            for (String s : unsuccessful){
                String[] dat = s.split(" ");
                sum += table.getProbes(dat[0]);
            }
        }

        return sum/10000.0;

    }
    private static int measureCollisions(double loadFactor) {
        int size = (int) (500000.0/loadFactor);
        HashTable table = new HashTable(size);
        for (String s : largeData){
            String[] dat = s.split(" ");
            table.put(dat[0],dat[1] + " " +dat[2]);
        }
       return table.collisions;
    }

    private static ArrayList<String> initList(String filename) throws FileNotFoundException {
        ArrayList<String> data = new ArrayList<String>();
        Scanner sc = new Scanner(new File(filename));
        while (sc.hasNextLine()) {
            data.add(sc.nextLine());
        }
        return data;
        }
     private static double measureInsertionPerformance(double loadFactor){
        int size = (int) (500000.0/loadFactor);
        double sum = 0;
        int i=0;
        HashTable table = new HashTable(size);
         long start = System.currentTimeMillis();
         long stop = System.currentTimeMillis();
         for (String s : largeData){
            String[] dat = s.split(" ");
             start = System.currentTimeMillis();
             table.put(dat[0],dat[1] + " " +dat[2]);
             stop = System.currentTimeMillis();
             i++;
             sum += stop-start;
        }
         return (sum/500000);
     }
    }



