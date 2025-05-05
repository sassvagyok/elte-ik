package parking;

import vehicle.Size;
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

    public String toString() {
        String parkingLotAsText = "";

        for (int i = floorPlan.length - 1; i >= 0; i--) {
            for (int j = 0; j < floorPlan[i].length; j++) {
                if (!floorPlan[i][j].isTaken()) {
                    parkingLotAsText += "X";
                } else if (floorPlan[i][j].getOccupyingCarSize().equals(Size.LARGE)) {
                    parkingLotAsText += "L";
                } else if (floorPlan[i][j].getOccupyingCarSize().equals(Size.SMALL)) {
                    parkingLotAsText += "S";
                }

                if (j < floorPlan[i].length - 1) {
                    parkingLotAsText += " ";
                }
            }
            parkingLotAsText += "\n";
        }

        return parkingLotAsText;
    }
}