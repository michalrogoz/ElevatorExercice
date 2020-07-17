package domain;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import model.Direction;
import model.Request;

import java.util.*;

@Data
@Slf4j
public class ElevatorController implements Runnable {
    private Set<Request> pickups = Collections.synchronizedSet(new LinkedHashSet<>());
    private SortedSet<Integer> choosenFloors = Collections.synchronizedSortedSet(new TreeSet<>());

    private DefaultElevator elevator;

    public ElevatorController(DefaultElevator elevator) {
        this.elevator = elevator;
    }

    @Override
    public void run() {
        while (true) {
            controlElevator();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void controlElevator() {
        while (!choosenFloors.isEmpty()) {
            processChoosenFloors();
        }
        if (!pickups.isEmpty()) {
            Request next = pickups.iterator().next();
            log.info("Processing request: " + next);
            sendElevatorToFloor(next);
        }
    }

    private void processChoosenFloors() {
        int currentFloor = elevator.getCurrentFloor();
        int targetFloor = determineTargetFloor(currentFloor);

        while (currentFloor != targetFloor) {
            elevator.move(targetFloor);
            currentFloor = elevator.getCurrentFloor();
            if (choosenFloors.contains(currentFloor)) {
                elevator.stop();
                choosenFloors.remove(currentFloor);
            }
            targetFloor = determineTargetFloor(currentFloor);
        }
        elevator.setDirection(Direction.NONE);
    }


    private void sendElevatorToFloor(Request next) {
        int currentFloor = elevator.getCurrentFloor();
        int targetFloor = next.getFloor();
        while (currentFloor != targetFloor) {
            elevator.move(targetFloor);
            currentFloor = elevator.getCurrentFloor();
            Request tempRequest = new Request(currentFloor, elevator.getDirection());
            if (pickups.contains(tempRequest)) {
                elevator.stop();
                removeAllRequestsFromThisFloor(tempRequest);
            }
        }
        elevator.stop();
        removeAllRequestsFromThisFloor(next);
    }


    private int determineTargetFloor(int currentFloor) {
        if (!choosenFloors.isEmpty()) {
            switch (elevator.getDirection()) {
                case UP:
                    return choosenFloors.last();
                case DOWN:
                    return choosenFloors.first();
                case NONE:
                    return choosenFloors.iterator().next();
            }
        }
        return currentFloor;
    }

    private void removeAllRequestsFromThisFloor(Request request) {
        pickups.removeIf(r -> r.getFloor() == request.getFloor());
    }


}
