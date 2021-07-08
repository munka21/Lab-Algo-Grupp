import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.awt.*;
import java.awt.geom.Line2D;
import java.util.*;
import java.util.List;
import javax.swing.*;

public class FlächePolygone {

    static class Polygon {
        int numberOfLines = 0;
        double koordinaten[][];
        List<double[]> koordinatenList;
    }

    private static List<double[]> clipPolygon(List<double[]> subject, List<double[]> clipper) {
        List<double[]> result = new ArrayList<>(subject);
        int len = clipper.size();
        for (int i = 0; i < len; i++) {
            int len2 = result.size();
            List<double[]> input = result;
            result = new ArrayList<>(len2);
            double[] A = clipper.get((i + len - 1) % len);
            double[] B = clipper.get(i);
            for (int j = 0; j < len2; j++) {
                double[] P = input.get((j + len2 - 1) % len2);
                double[] Q = input.get(j);
                if (isInside(A, B, Q)) {
                    if (!isInside(A, B, P))
                        result.add(intersection(A, B, P, Q));
                    result.add(Q);
                } else if (isInside(A, B, P))
                    result.add(intersection(A, B, P, Q));
            }
        }
        return result;
    }

    private static boolean isInside(double[] a, double[] b, double[] c) {
        return (a[0] - c[0]) * (b[1] - c[1]) > (a[1] - c[1]) * (b[0] - c[0]);
    }

    private static double[] intersection(double[] a, double[] b, double[] p, double[] q) {
        double A1 = b[1] - a[1];
        double B1 = a[0] - b[0];
        double C1 = A1 * a[0] + B1 * a[1];

        double A2 = q[1] - p[1];
        double B2 = p[0] - q[0];
        double C2 = A2 * p[0] + B2 * p[1];

        double det = A1 * B2 - A2 * B1;
        double x = (B2 * C1 - B1 * C2) / det;
        double y = (A1 * C2 - A2 * C1) / det;

        return new double[]{x, y};
    }

    public static void areaPolygon(List<double[]> result){
        int temp = 0;
        for (int i = 0; i < result.size() - 1; i++){
            temp += ((result.get(i)[1] + result.get(i + 1)[1]) * (result.get(i)[0] - result.get(i + 1)[0]));
        }
        System.out.print("\nArea: " + Math.abs((double) temp / 2.0));
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new FileReader("D:\\test2.txt"));
        //BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String[] Line = bf.readLine().split(" ");
        int n = Integer.parseInt(Line[0]);
        FlächePolygone.Polygon[] polygons = new FlächePolygone.Polygon[n];
        for (int i = 0; i < n; i++) {
            polygons[i] = new FlächePolygone.Polygon();
            String[] linePolygon = bf.readLine().split(" ");
            polygons[i].numberOfLines = Integer.parseInt(linePolygon[0]);
            polygons[i].koordinaten = new double[polygons[i].numberOfLines][2];//[Zeilen] [Spalten]
            int index = 1;
            for (int j = 0; j < polygons[i].numberOfLines; j++) {
                polygons[i].koordinaten[j][0] = (Double.parseDouble(linePolygon[index++])) * 100;
                polygons[i].koordinaten[j][1] = (Double.parseDouble(linePolygon[index++])) * 100;
            }
            polygons[i].koordinatenList = new ArrayList<>(Arrays.asList(polygons[i].koordinaten));
        }
        List<double[]> result = null;
        for (int i = 0; i < n; i++){
            if (result == null){
                result = clipPolygon(polygons[i].koordinatenList, polygons[i+1].koordinatenList);
                i = i + 2;
            }
            else if (result != null){
                result = clipPolygon(result, polygons[i].koordinatenList);
            }
        }
        areaPolygon(result);
    }

}
