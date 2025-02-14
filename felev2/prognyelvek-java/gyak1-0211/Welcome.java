public class Welcome {
    public static void main(String[] args) {
        System.console().printf("Mi a neved?");
        String name = System.console().readLine();
        System.console().printf("Hello, " + name + "!");
    }
}