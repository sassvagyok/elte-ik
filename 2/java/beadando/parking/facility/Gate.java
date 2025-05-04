package parking.facility;

import vehicle.Car;
import vehicle.Size;
import parking.facility.Space;
import parking.ParkingLot;

import java.util.ArrayList;

public class Gate {
    private final ArrayList<Car> cars = new ArrayList<Car>();
    private final ParkingLot parkingLot;

    public Gate(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    private Space findTakenSpaceByCar(Car c) {
        for (Space[] floor : parkingLot.getFloorPlan()) {
            for (Space space : floor) {
                if (space.isTaken() && space.getCarLicensePlate().equals(c.getLicensePlate())) {
                    return space;
                }
            }
        }

        return null;
    }

    private Space findAvailableSpaceOnFloor(int floor, Car c) {
        Space[] spaces = parkingLot.getFloorPlan()[floor];

        for (int i = 0; i < spaces.length; i++) {
            if (!spaces[i].isTaken()) {
                if (c.getSpotOccupation().equals("LARGE")) {
                    if (i + 1 < spaces.length && !spaces[i + 1].isTaken()) {
                        return spaces[i];
                    }
                } else {
                    return spaces[i];
                }
            }
        }

        return null;
    }

    public Space findAnyAvailableSpaceForCar(Car c) {
        for (int i = 0; i < parkingLot.getFloorPlan().length; i++) {
            Space availableSpace = findAvailableSpaceOnFloor(i, c);

            if (availableSpace != null) {
                return availableSpace;
            }
        }

        return null;
    }

    public Space findPreferredAvailableSpaceForCar(Car c) {
        int preferredFloor = c.getPreferredFloor();

        Space availableSpace = findAvailableSpaceOnFloor(preferredFloor, c);

        if (availableSpace != null) {
            return availableSpace;
        }

        for (int i = 1; i < parkingLot.getFloorPlan().length; i++) {
            if (preferredFloor - i >= 0) {
                availableSpace = findAvailableSpaceOnFloor(preferredFloor - i, c);
            }

            if (preferredFloor + i < parkingLot.getFloorPlan().length) {
                availableSpace = findAvailableSpaceOnFloor(preferredFloor + i, c);
            }

            if (availableSpace != null) {
                return availableSpace;
            }
        }

        return null;
    }

    public boolean registerCar(Car c) {
        Space preferredAvailableSpace = findPreferredAvailableSpaceForCar(c);

        if (preferredAvailableSpace != null) {
            preferredAvailableSpace.addOccupyingCar(c);
            cars.add(c);

            return true;
        }

        return false;
    }

    public void registerCars(Car... cars) {
        for (Car car : cars) {
            Space availableSpace = findAnyAvailableSpaceForCar(car);

            if (availableSpace != null) {
                availableSpace.addOccupyingCar(car);
                this.cars.add(car);
            } else {
                System.out.println("Nem található hely az adott autó számára!");
            }
        }
    }

    public void deRegisterCar(String ticketId) {
        Car foundCar = null;

        for (Car car : cars) {
            if (car.getTicketId().equals(ticketId)) {
                foundCar = car;
            }
        }

        if (foundCar != null) {
            Space takenSpace = findTakenSpaceByCar(foundCar);

            takenSpace.removeOccupyingCar();
        }
    }
}