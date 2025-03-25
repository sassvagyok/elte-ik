public enum Pelda {
    ZERO(10),
    ONE(11),
    TWO(12);

    private int value;

    Pelda(int value) {
        System.out.println("Constructor of " + this.toString());
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}