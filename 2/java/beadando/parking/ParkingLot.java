package parking;

import parking.facility.Space;

public class ParkingLot {
    private final Space[][] floorPlan;

    public ParkingLot(int floorNumber, int spaceNumber) {
        if (floorNumber < 1 || spaceNumber < 1 ) {
            throw new IllegalArgumentException("Az emeletek vagy parkolóhelyek száma nem lehet kisebb mint 1.");
        }

        this.floorPlan = new Space[floorNumber][spaceNumber];

        for (int i = 0; i < floorNumber; i++) {
            for (int j = 0; j < spaceNumber; j++) {
                this.floorPlan[i][j] = new Space(i, j);
            }
        }
    }

    public Space[][] getFloorPlan() {
        return this.floorPlan;
    }
}