package pl.stqa.pft.sandbox;

public class DistanceBetweenTwoPoints {

    public static void main(String[] args) {
        Point p1 = new Point(4,1);
        Point p2 = new Point(-3,4);

        System.out.println("Distance between the points is equal to " + p1.distance(p2));
    }
}
