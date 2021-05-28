import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class kMeansIIIV2 {

    public static void kMeans(ArrayList<Point> points, double r) {
        int size = points.size() - 1;//Prüfe
        for (int i = 0; i < size; i++) {
            if ((points.get(i).isCenter == true) || (points.get(i).inCenters == true)) {
                continue;
            } else {
                //Finde Centers oder Macht Center
                findCenter(points, r, i);
            }
        }
    }

    public static void findCenter(ArrayList<Point> points, double r, int indexFromCurrentPoint) {
        //Für dem Center finden oder als Center setzen
        int size = points.size() - 1;
        int[] indexOfPoints = new int[size];
        int index = 0;
        int nummberOfPoints = 0;
        for (int i = 0; i < size; i++) {
            if (i == indexFromCurrentPoint) {
                continue;//Die betrachtete Punkt überspringen wir
            }
            double test = Math.abs(points.get(indexFromCurrentPoint).distanceToZero - points.get(i).distanceToZero);
            if ((test <= r) && (points.get(i).inCenters == false)) {
                double disPoint = distToPoint(points.get(indexFromCurrentPoint), points.get(i));
                if (disPoint <= r) {//Vieleicht zu Int tauschen, wegen genauichkeit von double??
                    if (points.get(i).isCenter == true) {
                        points.get(indexFromCurrentPoint).inCenters = true; //Hire wird er zu Center hinzugefügt
                        break;//wurde zu Zentrum hinzugefügt, nächste punkt
                    } else if (points.get(i).isCenter == false) {//Hier merken wir Punkte die max r weit sind und keine Center sind, falss der Punkt Center wird direkt übernehemen
                        nummberOfPoints++;
                        indexOfPoints[index] = i;
                        index++;
                        //Hier müssen wir uns den punk erstmal merken, falls betrachtete Punk ein Centers wird
                    }
                }
            } else {
                continue;//Würden sortiert, falls if = false, weiter wird nicht true sein
            }
        }
        //Hier Kein Centers Gefunden unsere Punkt muss Centers sein, und alle Punkte in indexOfPoints zu eigene Center hinzufügen, besser neue Funktion dafür
        if ((points.get(indexFromCurrentPoint).inCenters == false) && (points.get(indexFromCurrentPoint).isCenter == false)) {
            makeCenters(points, indexFromCurrentPoint, indexOfPoints, nummberOfPoints);
        }
    }

    public static void makeCenters(ArrayList<Point> points, int indexFromCurrentPoint, int indexOfPoints[], int nummberOfPoints) {
        points.get(indexFromCurrentPoint).isCenter = true;
        //Anzahl von Centers hier messen
        for (int i = 0; i < nummberOfPoints; i++) {
            points.get(indexOfPoints[i]).inCenters = true;
        }
    }

    public static double distToPoint(Point a, Point b) {
        double dis = 0.0;
        double temp = 0.0;
        int dimension = a.coordinates.length;
        for (int i = 0; i < dimension; i++) {
            temp = (a.coordinates[i] - b.coordinates[i]);
            temp = Math.pow(temp, 2);
            dis += temp;
        }
        dis = Math.sqrt(dis);
        return dis;
    }

    public static void printPoints(ArrayList<Point> points) {
        int size = points.size() - 1;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < points.get(i).coordinates.length; j++) {
                System.out.print(points.get(i).coordinates[j] + " ");
            }
            System.out.print("   DisToZero " + points.get(i).distanceToZero + " isCenter " + points.get(i).isCenter + "\n");
        }
    }

    public static ArrayList<Point> setPoints(Scanner scan, int d, ArrayList<Point> points) {
        int dimension = 0;
        int index = 0;
        points.add(new Point());
        while (scan.hasNextDouble()) {
            if (points.get(index).coordinates == null) {
                points.get(index).coordinates = new double[d];
            }
            double temp = scan.nextDouble();
            points.get(index).coordinates[dimension] = temp;
            dimension++;
            if (dimension == d) {
                points.get(index).distanceToZero = points.get(index).computeDisToZero(points.get(index).coordinates);
                dimension = 0;
                points.add(new Point());
                index++;
            }
        }
        //Letzte Points kann man löschen
        points = sortPoints(points);
        return points;
    }

    public static ArrayList<Point> sortPoints(ArrayList<Point> points) {
        int size = points.size() - 1;
        for (int i = 0; i < size; i++) {
            for (int j = i; j < size; j++) {
                if ((points.get(i).distanceToZero > points.get(j).distanceToZero)) {
                    Point tempPoint = points.get(j);
                    points.remove(j);
                    points.add(i, tempPoint);
                }
            }
        }
        return points;
    }

    public static void main(String[] args) throws IOException {
        Scanner scan;
        scan = new Scanner(new File("D:\\test.txt"));
        int d = scan.nextInt();
        double r = scan.nextDouble();
        ArrayList<Point> points = new ArrayList<Point>();
        points = setPoints(scan, d, points);
        kMeans(points, r);
        printPoints(points);
    }
}


class Point {
    boolean inCenters = false;
    boolean isCenter = false;
    double distanceToZero;
    double[] coordinates;

    public static double computeDisToZero(double coordinates[]) {
        double disToZero = 0.0;
        double temp = 0.0;
        for (int i = 0; i < coordinates.length; i++) {
            temp = Math.pow(coordinates[i], 2);
            disToZero += temp;
        }
        disToZero = Math.sqrt(disToZero);
        return disToZero;
    }
}
