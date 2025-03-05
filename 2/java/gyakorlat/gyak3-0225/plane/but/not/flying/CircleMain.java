package plane.but.not.flying;

import plane.PublicCircle;

public class CircleMain {
    public static void main(String[] args) {
        PublicCircle p = new PublicCircle(2., 3., 10.);

        System.out.println(p.getArea());

        p.setX(5.);
        p.setY(2.);
        p.setRadius(10.);

        System.out.println(p.getArea());
    }
}