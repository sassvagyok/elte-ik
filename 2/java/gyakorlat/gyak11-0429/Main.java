import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

interface Animal {
    default void shout() {
        System.out.println(" is shouting.");
    }
    void step(); // public abstract void step == void step();
}

class Dog implements Animal {
    @Override
    public void shout() {
        System.out.println("Dog");
        Animal.super.shout();
    }

    @Override
    public void step() {
        System.out.println("Step is made by a dog.");
    }
}

class Cat implements Animal, Comparable<Cat> {
    @Override
    public void shout() {
        System.out.println("Cat");
        Animal.super.shout();
    }

    @Override
    public void step() {
        System.out.println("Step is made by a cat.");
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Cat) { // obj.getClass().equals(this.getClass())
            Cat c = (Cat)obj;
            return true;
        }

        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(/*list, asd, dsa, ...*/);
    }

    @Override
    public int compareTo(Cat c) {
        /*
        this < c
        return -1 vagy negativ
        this == c
        return 0
        this > c
        return 1 vagy pozitiv
        */
       return 0;
    }
}


public class Main {
    public static void main(String[] args) {
        List<Animal> animals = new LinkedList<>(); // mindegy milyen, csak lista legyen

        animals.add(new Dog());
        animals.add(new Dog());
        animals.add(new Cat());
        animals.add(0, new Cat());

        for (Animal animal : animals) {
            animal.shout();
            animal.step();
        }
    }
}