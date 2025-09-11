package hallgato;

import java.util.ArrayList;

public class Hallgato {
    private String name;
    private String nationality;
    private double average;

    public static void main(String[] args) {
        ArrayList<Hallgato> hallgatok = new ArrayList<>();
        ArrayList<Hallgato> osztondijas = new ArrayList<>();

        Hallgato h1 = new Hallgato("János", "Magyar", 2.4);
        Hallgato h2 = new Hallgato("John", "Brit", 3.1);
        Hallgato h3 = new Hallgato("Mátyás", "Magyar", 2.9);
        Hallgato h4 = new Hallgato("Károly", "Magyar", 4.9);
        Hallgato h5 = new Hallgato("Ferenc", "Magyar", 4.0);
        Hallgato h6 = new Hallgato("Zsigmond", "Magyar", 4.3);

        hallgatok.add(h1);
        hallgatok.add(h2);
        hallgatok.add(h3);
        hallgatok.add(h4);
        hallgatok.add(h5);
        hallgatok.add(h6);

        Hallgato best = null;
        double bestAverage = Integer.MIN_VALUE;

        Hallgato worst = null;
        double worstAverage = Integer.MAX_VALUE;

        for (Hallgato h : hallgatok) {
            if (h.getAverage() > bestAverage) {
                best = h;
                bestAverage = h.getAverage();
            }

            if (h.getAverage() < worstAverage) {
                worst = h;
                worstAverage = h.getAverage();
            }

            if (h.getAverage() >= 4.0) {
                osztondijas.add(h);
            }
        }

        System.out.println("Legjobb: " + best.getName() + "\nLegrosszabb: " + worst.getName() + "\nÖsztöndíjat kap:");
        for (Hallgato h : osztondijas) {
            System.out.println(h.getName());
        }
    }

    public Hallgato(String name, String nationality, double average) {
        this.name = name;
        this.nationality = nationality;
        this.average = average;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public double getAverage() {
        return average;
    }

    public void setAverage(double average) {
        this.average = average;
    }
}
