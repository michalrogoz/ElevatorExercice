package domain;

import lombok.Getter;
import model.Request;

@Getter
public class Building {
    private final ElevatorController elevatorController;
    private final DefaultElevator elevator;
    private final int floors;

    public Building(int floors) {
        this.floors = floors;
        elevator = new DefaultElevator(floors);
        elevatorController = new ElevatorController(elevator);
    }

    public void pickup(Request request) {
        elevatorController.getPickups().add(request);
    }

    public void chooseFloor(int request) {
        elevatorController.getChoosenFloors().add(request);
    }

}
