package parking.facility;

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

public class GateTest {
    @Test
    public void testFindAnyAvailableSpaceForCar() {
        ParkingLot parkingLot = new ParkingLot(5, 10);
        Space[][] floorPlan = parkingLot.getFloorPlan();
        Gate gate = new Gate(parkingLot);
        Car smallCar = new Car("ASD-000", Size.SMALL, 0);
        Car largeCar = new Car("ASD-030", Size.LARGE, 3);

        assertEquals(floorPlan[0][0], gate.findAnyAvailableSpaceForCar(smallCar));
        assertEquals(floorPlan[0][1], gate.findAnyAvailableSpaceForCar(largeCar));
    }

    @ParameterizedTest(name = "{0}, {1}, preferredFloorFloor {2}")
    @CsvSource(textBlock = """
        ASD-000, SMALL, 0
        ASD-010, LARGE, 1
        ASD-020, SMALL, 2
        ASD-030, LARGE, 3
        ASD-040, SMALL, 4
    """)
    @DisableIfHasBadStructure
    public void testFindPreferredAvailableSpaceForCar(String plate, Size size, int preferredFloor) {
        ParkingLot parkingLot = new ParkingLot(5, 10);
        Space[][] floorPlan = parkingLot.getFloorPlan();
        Gate gate = new Gate(parkingLot);
        Car car = new Car(plate, size, preferredFloor);
        Space space = gate.findPreferredAvailableSpaceForCar(car);

        assertEquals(preferredFloor, space.getFloorNumber());

        if (size.equals(Size.LARGE)) {
            assertEquals(1, space.getSpaceNumber());
        } else if (size.equals(Size.SMALL)) {
            assertEquals(0, space.getSpaceNumber());
        }
    }

    @ParameterizedTest(name = "{0}, {1}, preferredFloorFloor {2}")
    @CsvSource(textBlock = """
        ASD-000, SMALL, 0
        ASD-010, LARGE, 1
        ASD-020, SMALL, 2
        ASD-030, LARGE, 3
        ASD-040, SMALL, 4
    """)
    @DisableIfHasBadStructure
    public void testRegisterCar(String plate, Size size, int preferredFloor) {
        ParkingLot parkingLot = new ParkingLot(5, 10);
        Space[][] floorPlan = parkingLot.getFloorPlan();
        Gate gate = new Gate(parkingLot);
        Car car = new Car(plate, size, preferredFloor);

        assertTrue(gate.registerCar(car));
        assertEquals(plate, floorPlan[preferredFloor][0].getCarLicensePlate());

        if (size.equals(Size.LARGE)) {
            assertEquals(plate, floorPlan[preferredFloor][1].getCarLicensePlate());
        }
    }

    @ParameterizedTest(name = "{0}, {1}, preferredFloorFloor {2} => floor {3}, space {4}")
    @CsvSource(textBlock = """
        ASD-000, SMALL, 0
        ASD-010, LARGE, 1
        ASD-020, SMALL, 2
        ASD-030, LARGE, 3
        ASD-040, SMALL, 4
    """)
    @DisableIfHasBadStructure
    public void testDeRegisterCar(String plate, Size size, int preferredFloor) {
        ParkingLot parkingLot = new ParkingLot(5, 10);
        Space[][] floorPlan = parkingLot.getFloorPlan();
        Gate gate = new Gate(parkingLot);
        Car car = new Car(plate, size, preferredFloor);

        gate.registerCar(car);
        gate.deRegisterCar(car.getTicketId());

        assertFalse(floorPlan[preferredFloor][0].isTaken());

        if (size.equals(Size.LARGE)) {
            assertFalse(floorPlan[preferredFloor][1].isTaken());
        }
    }
}
