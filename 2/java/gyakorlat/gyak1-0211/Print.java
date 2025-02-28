public class Print {
    public static void main(String[] args) {
        for(int i = 1; i <= 4; i++) {
            System.out.println((float)i/2); // vagy (i/2.)
        }
        int i = 4;
        System.out.println("Kiirtam " + i + " szamot");
        System.out.println("fact (2) = " + factorial(2));
        System.out.println("fact (2) = " + Print.factorial(2));
    }
    public static int factorial(int n) {
        if (n < 2) {
            return 1;
        } else {
            return n * factorial(n - 1);
        }
    }
}