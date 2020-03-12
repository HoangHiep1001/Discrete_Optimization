package home.hiephm.week2;

import java.io.*;
import java.util.List;
import java.util.ArrayList;

/**
 * my token: T90dJdPWkKvksvrx
 * The class <code>Solver</code> is an implementation of a greedy algorithm to solve the knapsack problem.
 *
 */
public class Knapsack {

    /**
     * The main class
     */
    public static void main(String[] args) {
        try {
            System.out.println(solvedDP("ks_4_0"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Read the instance, solve it, and print the solution in the standard output
     */
    public static void solve(String[] args) throws IOException {
        String fileName = null;

        // get the temp file name
        for(String arg : args){
            if(arg.startsWith("-file=")){
                fileName = arg.substring(6);
            }
        }
        if(fileName == null)
            return;

        // read the lines out of the file
        List<String> lines = new ArrayList<String>();

        BufferedReader input =  new BufferedReader(new FileReader(fileName));
        try {
            String line = null;
            while (( line = input.readLine()) != null){
                lines.add(line);
            }
        }
        finally {
            input.close();
        }


        // parse the data in the file
        String[] firstLine = lines.get(0).split("\\s+");
        int items = Integer.parseInt(firstLine[0]);
        int capacity = Integer.parseInt(firstLine[1]);

        int[] values = new int[items];
        int[] weights = new int[items];

        for(int i=1; i < items+1; i++){
            String line = lines.get(i);
            String[] parts = line.split("\\s+");

            values[i-1] = Integer.parseInt(parts[0]);
            weights[i-1] = Integer.parseInt(parts[1]);
        }

        // a trivial greedy algorithm for filling the knapsack
        // it takes items in-order until the knapsack is full
        int value = 0;
        int weight = 0;
        int[] taken = new int[items];

        for(int i=0; i < items; i++){
            if(weight + weights[i] <= capacity){
                taken[i] = 1;
                value += values[i];
                weight += weights[i];
            } else {
                taken[i] = 0;
            }
        }

        // prepare the solution in the specified output format
        System.out.println(value+" 0");
        for(int i=0; i < items; i++){
            System.out.print(taken[i]+" ");
        }
        System.out.println("");
    }
    /**
     * DP for Knapsack
     */
    public static int solvedDP(String fileName) throws IOException {
        List<String> lines = new ArrayList<String>();
        String filePath = "C:\\Users\\admin\\Desktop\\Discrete_Optimization\\data_Knapsack\\";
        FileReader fileReader = new FileReader(filePath + fileName);
        BufferedReader input = new BufferedReader(fileReader);
        try {
            String line = null;
            while ((line = input.readLine()) != null) {
                lines.add(line);
            }
        } finally {
            input.close();
        }

        // parse the data in the file
        String[] firstLine = lines.get(0).split("\\s+");
        int items = Integer.parseInt(firstLine[0]);
        int capacity = Integer.parseInt(firstLine[1]);

        //weights and values
        int[] values = new int[items];
        int[] weights = new int[items];


        for (int i = 1; i < items + 1; i++) {
            String line = lines.get(i);
            String[] parts = line.split("\\s+");

            values[i - 1] = Integer.parseInt(parts[0]);
            weights[i - 1] = Integer.parseInt(parts[1]);
        }
        int i,w;
        int[][] bag = new int[items + 1][capacity + 1];
        for (i = 0; i <= items; i++) {
            for (w = 0; w <= capacity; w++) {
                if (i == 0 || w == 0)
                    bag[i][w] = 0;
                else if (weights[i - 1] > w){
                    bag[i][w] = bag[i - 1][w];
                }
                else {
                    bag[i][w] = Math.max(values[i - 1] + bag[i - 1][w - weights[i - 1]], bag[i - 1][w]);
                }
            }
        }
        return bag[items][capacity];
    }
}