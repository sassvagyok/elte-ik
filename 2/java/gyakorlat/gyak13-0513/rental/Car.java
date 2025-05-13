package rental;

public class Car {
    private String brand;
    private String licensePlate;
    private double price;
    private static final double MAX_PRICE = 500.0;
    private static final Car CAR_OF_THE_YEAR = new Car("Alfa Romeo", "ABC 123", MAX_PRICE);

    private Car(String brand, String licensePlate, double price) {
        this.brand = brand;
        this.licensePlate = licensePlate;
        this.price = price;
    }

    public boolean isCheaperThan(Car other) {
        return this.price < other.getPrice();
    }

    public void decreasePrice() {
        if (this.price > 10 && this.price + 10 <= this.MAX_PRICE) {
            this.price = this.price + 10;
        }
    }

    public static Car make(String brand, String licensePlate, double price) {
        for (char letter : brand.toCharArray()) {
            if (!Character.isLetter(letter)) {
                return null;
            }
        }

        if (!isValidLicensePlate(licensePlate)) {
            return null;
        }

        if (price < 0 || price > MAX_PRICE) {
            return null;
        }

        return new Car(brand, licensePlate, price);
    }

    private static boolean isValidLicensePlate(String licensePlate) {
        if (licensePlate.length() != 7) {
            return false;
        }

        char[] plate = licensePlate.toCharArray();

        for (int i = 0; i < 7; i++) {
            if (i < 3) {
                if (!Character.isUpperCase(plate[i])) {
                    return false;
                }
            }

            if (i == 3 && plate[i] != ' ') {
                return false;
            }

            if (i > 3 && i < 7) {
                if (!Character.isDigit(plate[i])) {
                    return false;
                }
            }
        }

        return true;
    }

    public String toString() {
        return String.format("%s (%s) %.1f EUR", this.brand, this.licensePlate, this.price);
    }

    public double getPrice() {
        return this.price;
    }
}