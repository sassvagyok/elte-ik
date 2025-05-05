import static check.CheckThat.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.*;
import org.junit.jupiter.api.extension.*;
import org.junit.jupiter.params.*;
import org.junit.jupiter.params.provider.*;
import check.*;

import vehicle.Car;
import vehicle.Size;
import parking.facility.Space;
import parking.facility.Gate;
import parking.ParkingLot;

public class ParkingLotTest {
    @Test
    public void testConstructorWithInvalidValues() {
        try {
            ParkingLot parkingLot = new ParkingLot(0, 5);
        } catch(IllegalArgumentException e) {
            assertEquals("Az emeletek vagy parkolóhelyek száma nem lehet kisebb mint 1.", e.getMessage());
            return;
        }

        fail();

        try {
            ParkingLot parkingLot = new ParkingLot(0, 0);
        } catch(IllegalArgumentException e) {
            assertEquals("Az emeletek vagy parkolóhelyek száma nem lehet kisebb mint 1.", e.getMessage());
            return;
        }

        fail();

        try {
            ParkingLot parkingLot = new ParkingLot(5, 0);
        } catch(IllegalArgumentException e) {
            assertEquals("Az emeletek vagy parkolóhelyek száma nem lehet kisebb mint 1.", e.getMessage());
            return;
        }

        fail();
    }

    @Test
    public void testTextualRepresentation() {
        ParkingLot parkingLot = new ParkingLot(5, 5);
        Space[][] floorPlan = parkingLot.getFloorPlan();
        Gate gate = new Gate(parkingLot);

        Car smallCar1 = new Car("ASD-001", Size.SMALL, 0);
        Car smallCar2 = new Car("ASD-002", Size.SMALL, 0);
        Car smallCar3 = new Car("ASD-003", Size.SMALL, 0);
        Car smallCar4 = new Car("ASD-004", Size.SMALL, 0);
        Car smallCar5 = new Car("ASD-005", Size.SMALL, 0);
        Car smallCar6 = new Car("ASD-021", Size.SMALL, 2);
        Car smallCar7 = new Car("ASD-022", Size.SMALL, 2);
        Car smallCar8 = new Car("ASD-023", Size.SMALL, 2);
        Car smallCar9 = new Car("ASD-031", Size.SMALL, 3);
        Car smallCar10 = new Car("ASD-032", Size.SMALL, 3);
        Car smallCar11 = new Car("ASD-033", Size.SMALL, 3);
        Car smallCar12 = new Car("ASD-041", Size.SMALL, 4);
        Car smallCar13 = new Car("ASD-042", Size.SMALL, 4);
        Car smallCar14 = new Car("ASD-043", Size.SMALL, 4);
        Car smallCar15 = new Car("ASD-044", Size.SMALL, 4);
        Car smallCar16 = new Car("ASD-045", Size.SMALL, 4);

        Car largeCar1 = new Car("ASD-011", Size.LARGE, 1);
        Car largeCar2 = new Car("ASD-012", Size.LARGE, 1);
        Car largeCar3 = new Car("ASD-024", Size.LARGE, 2);
        Car largeCar4 = new Car("ASD-034", Size.LARGE, 3);

        gate.registerCars(smallCar1, smallCar2, smallCar3, smallCar4, smallCar5);

        gate.deRegisterCar("1");
        gate.deRegisterCar("4");
        gate.deRegisterCar("5");

        gate.registerCar(largeCar1);
        gate.registerCar(largeCar2);

        gate.registerCar(smallCar6);
        gate.registerCar(smallCar7);
        gate.registerCar(smallCar8);
        gate.registerCar(largeCar3);

        gate.deRegisterCar("9");

        gate.registerCar(smallCar9);
        gate.registerCar(smallCar10);
        gate.registerCar(smallCar11);
        gate.registerCar(largeCar4);

        gate.deRegisterCar("12");

        gate.registerCar(smallCar12);
        gate.registerCar(smallCar13);
        gate.registerCar(smallCar14);
        gate.registerCar(smallCar15);
        gate.registerCar(smallCar16);

        gate.deRegisterCar("16");
        gate.deRegisterCar("17");
        gate.deRegisterCar("18");
        gate.deRegisterCar("20");

        assertEquals("X X X S X\nX S S L L\nS X S L L\nL L L L X\nX S S X X\n", parkingLot.toString());
    }
}
