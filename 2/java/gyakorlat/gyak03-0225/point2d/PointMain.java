package point2d;

public class PointMain {
    public static void main(String[] args) {
        Point p = new Point(2., 1.);

        System.out.println(p.toString());
        p.setX(10.);

        p.move(1., 0.);
        System.out.println(p.toString());

        p.mirror(1., 1.1);
        System.out.println(p.toString()); // ha toString() a neve, akkor nem muszáj kiirni

        p.mirrorP(p);
        System.out.println(p.toString());

        System.out.println(p.distance(p));
    }
}