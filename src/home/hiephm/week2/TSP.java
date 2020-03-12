package home.hiephm.week2;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TSP {
    static class Point{
        float x;
        float y;
        public Point(float x,float y){
            this.x = x;
            this.y = y;
        }
    }
    static float distance(Point p1,Point p2){
        return (float) Math.sqrt(Math.pow((p1.x - p2.x),2) + Math.pow((p1.y - p2.y),2));
    }
    public static void solve(String fileName) throws IOException {
        List<String> lines = new ArrayList<String>();
        List<Point> listPoint = new ArrayList<>();
        String filePath = "C:\\Users\\admin\\Desktop\\Discrete_Optimization\\data_TSP\\";
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
        int items = Integer.parseInt(lines.get(0));
        for (int i = 1; i < items + 1; i++) {
            String line = lines.get(i);
            String[] parts = line.split(" ");
            listPoint.add(new Point(Float.parseFloat(parts[0]), Float.parseFloat(parts[1])));
        }
    }

    public static void main(String[] args) throws IOException {
        solve("tsp_5_1");
    }
}
