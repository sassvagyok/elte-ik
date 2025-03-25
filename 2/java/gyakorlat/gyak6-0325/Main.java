public class Main {
    public static void main(String[] args) {
        Pelda p = Pelda.ONE;
        System.out.println(p);
        System.out.println(p.ordinal()); // hanyadik elem?
        System.out.println(p.getValue());
        
        Pelda[] peldak = Pelda.values();

        for (Pelda pelda : peldak) {
            System.out.println(pelda + " ordinal: " + pelda.ordinal() + " value: " + pelda.getValue());
        }
    }
}