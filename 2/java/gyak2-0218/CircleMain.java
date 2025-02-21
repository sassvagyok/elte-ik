public class CircleMain {
    public static void main(String[] args) {
        Circle k = new Circle(2, 3, 10);

        System.out.println(k.toString());

        k.enlarge(3);
        System.out.println(k.toString());

        System.out.println(k.getArea());
    }
}