import java.io.*;

public class kMeansIII {

    public static void kMeans(Point points[], int r, Center numberOfCenters) {
        int size = points.length - 1;
        for (int i = 0; i < size; i++) {
            if ((points[i].isCenter == true) || (points[i].inCenters == true)) {
                continue;
            } else {
                findCenter(points, r, i, numberOfCenters);
            }
        }
    }

    public static void findCenter(Point points[], int r, int indexOfCurrentPoint, Center numberOfCenters) {
        int size = numberOfCenters.Centers;
        for (int i = 0; i < size; i++) {
            if (points[numberOfCenters.indexOfCenters[i]].inCenters == true){
                continue;
            }
            int disPoint = distToPoint(points[indexOfCurrentPoint], points[numberOfCenters.indexOfCenters[i]]);//Wir betrachten nur die Punkte, die schon als Centers makiert wurden.
            if (disPoint <= r) {
                if (points[numberOfCenters.indexOfCenters[i]].isCenter == true) {
                    points[indexOfCurrentPoint].inCenters = true;
                    break;
                }
            }
        }
        if ((points[indexOfCurrentPoint].inCenters == false) && (points[indexOfCurrentPoint].isCenter == false)) {
            points[indexOfCurrentPoint].isCenter = true;
            //Merken welsche Punkten Centers sind
            numberOfCenters.indexOfCenters[numberOfCenters.Centers] = indexOfCurrentPoint;
            numberOfCenters.Centers++;
        }
    }

    public static int distToPoint(Point a, Point b) {
        double dis = 0.0;
        double temp = 0.0;
        int dimension = a.coordinates.length;
        for (int i = 0; i < dimension; i++) {
            temp = (a.coordinates[i] - b.coordinates[i]);
            temp = Math.pow(temp, 2);
            dis += temp;
        }
        //dis = Math.sqrt(dis);
        return (int) dis;
    }

    public static Point[] dymArray(Point points[]) {
        Point[] pointsTemp = points;
        points = new Point[pointsTemp.length + 1];
        System.arraycopy(pointsTemp, 0, points, 0, pointsTemp.length);
        return points;
    }

    public static void main(String[] args) throws IOException {
        //BufferedReader bf = new BufferedReader(new FileReader("D:\\test.txt"));
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String[] firstLine = bf.readLine().split(" ");
        int d = Integer.parseInt(firstLine[0]);
        double rD = Double.parseDouble(firstLine[1]);
        rD *= 1000;
        int r = (int) rD;
        r *= r;
        Point[] points = new Point[1];
        int dimension = 0;
        int index = 0;
        points[0] = new Point();
        while (bf.ready()) {
            if (points[index].coordinates == null) {
                points[index].coordinates = new int[d];
            }
            String[] line = bf.readLine().split(" ");
            for (int i = 0; i < d; i++) {
                double temp = Double.parseDouble(line[i]);
                temp *= 1000;
                points[index].coordinates[dimension] = (int) temp;
                dimension++;
                if (dimension == d) {
                    dimension = 0;
                    points = dymArray(points);
                    index++;
                    points[index] = new Point();
                }
            }
        }
        Center numberOfCenters = new Center();
        numberOfCenters.indexOfCenters = new int[points.length];
        kMeans(points, r, numberOfCenters);
        System.out.print(numberOfCenters.Centers);
    }
}

class Center {
    int Centers = 0;
    int[] indexOfCenters;
}

class Point {
    boolean inCenters = false;
    boolean isCenter = false;
    int[] coordinates;
}
