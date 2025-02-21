public class Parse {
    public static void main(String[] args) {
        int szam1 = Integer.parseInt(args[0]);
        int szam2 = Integer.parseInt(args[1]);

        for (int i = szam1; i <= szam2; i++) {
            System.out.println(i/2.);
        }
    }
}