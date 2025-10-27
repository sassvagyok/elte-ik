public class Distance {
    public static void main(String[] args) {
        Point[] points = new Point[args.length / 2];

        for (int i = 0; i < args.length; i += 2) {
            points[i / 2] = new Point(Double.parseDouble(args[i]), Double.parseDouble(args[i + 1]));
        }

        double finalDistance = 0.;
        if (args.length >= 4) {
            for (int i = 0; i < points.length - 1; i++) {
                finalDistance += points[i].distance(points[i + 1]);
            }
        }

        System.out.println(finalDistance);
    }
}