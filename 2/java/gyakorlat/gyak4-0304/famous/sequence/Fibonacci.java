package famous.sequence;

public class Fibonacci {
    public static int fib(int n) {
        if (n < 0) {
            System.err.println("Negatív szám!");

            return 0;
        } else if (n < 2) {
            return n;
        } else {
            return fib(n - 2) + fib(n - 1);
        }
    }
}