import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.Arrays;
class Point{
    double x, y;

}
//RE: java.lang.ArrayIndexOutOfBoundsException: Index 3 out of bounds for length 3
//RE:
public class Main {
    public static void main(String[] args) {
        // File file = new File("course/algo/input.txt");

        Scanner scanner = new Scanner(System.in);
        // Scanner scanner = null;
        // try {
        //     scanner = new Scanner(file);
        // } catch (FileNotFoundException e) {
        //     System.out.println("File not found");
        //     return;
        // }
        int numberOfCases = scanner.nextInt();//第一個數字是測資數量
        for (int i = 0; i < numberOfCases; i++) {
            double min = Double.MAX_VALUE;;
            int numberOfPoints = scanner.nextInt();//第二個數字是點的數量
            Point[] pointsSortedByX = new Point[numberOfPoints];
            Point[] pointsSortedByY = new Point[numberOfPoints];
            for (int j = 0; j < numberOfPoints; j++) {
                pointsSortedByX[j] = new Point();
                pointsSortedByX[j].x = scanner.nextDouble(); //第三個數字是x座標
                pointsSortedByX[j].y = scanner.nextDouble(); //第四個數字是y座標

                pointsSortedByY[j] = pointsSortedByX[j];
            }
            Arrays.sort(pointsSortedByX, new Comparator<Point>() {
                public int compare(Point p0, Point p1) {
                    if(p0.x == p1.x)
                        return Double.compare(p0.y, p1.y);
                    return Double.compare(p0.x, p1.x);
                };
            });
            Arrays.sort(pointsSortedByY, new Comparator<Point>() {
                public int compare(Point p0, Point p1) {
                    if(p0.y == p1.y)
                        return Double.compare(p0.x, p1.x);
                    return Double.compare(p0.y, p1.y);
                };
            });
            //divide求出兩邊最小值
            min = Math.min(min, divideAndConquer(pointsSortedByX, 0, numberOfPoints - 1));
            // System.out.println("min = " + min);
            
            //改求中間部分
            for(int j = 0; j < numberOfPoints; j++){
                // for(int k = j + 1; k < numberOfPoints && pointsSortedByY[j].y - pointsSortedByY[k].y < min; k++){
                //     min = Math.min(min, distance(pointsSortedByY[j], pointsSortedByY[k]));
                // }
                for(int k = j + 1; k < numberOfPoints && k - j <= 8; k++){
                    min = Math.min(min, distance(pointsSortedByY[j], pointsSortedByY[k]));
                }
            }
            // double xMid = pointsSortedByX[numberOfPoints / 2].x;//此點算在右邊xR中
            // double xLeftLine = xMid - min;
            // double xRightLine = xMid + min;
            // for(int j = 0; j < numberOfPoints / 2; j++){
            //     if(xLeftLine < pointsSortedByX[j].x){
            //         //找出yR中的點
            //     }
            // }

            //印出最小值
            min = Math.sqrt(min);
            System.out.printf("%.6f\n", min);
            // List<double[]> yAxis = points.sort(null);
            // Collections.sort(points, new Comparator<double[]>() {
            //     public int compare(double[] d0, double[] d1) {
            //         return Double.compare(d0[0], d1[0]);
            //     };
            // });
            
            // testCases.add(points);
        }
        scanner.close();
        //test input
        // for(int i = 0; i < testCases.size(); i++){
        //     for(int j = 0; j < testCases.get(i).size(); j++){
        //         System.out.println(testCases.get(i).get(j)[0] + " " + testCases.get(i).get(j)[1]);
        //     }
        // }

    }

    static double divideAndConquer(Point[] x, int start, int end){//start~end
        int n = end - start + 1;
        
        if(n <= 3){
            //Brute force
            switch (n) {
                case 3:
                    return Math.min(distance(x[start], x[start + 1]), Math.min(distance(x[start + 1], x[start + 2]), distance(x[start], x[start + 2])));
                case 2:
                    return distance(x[start], x[start + 1]);
                default:
                    return -999.0;
            }
        }
        int mid = n / 2;
        // System.out.println("mid: " + mid);
    
        return Math.min(divideAndConquer(x, start, start + mid - 1), divideAndConquer(x, start + mid, end));

    }
    static double distance(Point p0, Point p1){
        return Math.pow(p0.x - p1.x, 2) + Math.pow(p0.y - p1.y, 2);
    }
}