package famous.sequence;

public class TriangularNumbers {
    public static int getTriangularNumber(int n) {
        return n * (n + 1) / 2;
    }

    public static int getTriangularNumberAlternative(int n) {
        return getTriangularNumber(n);
    }
}