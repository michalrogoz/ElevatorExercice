package domain;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import model.Direction;

@Data
@Slf4j
public class DefaultElevator implements Elevator {
    private int currentFloor = 0;
    private final int maxFloor;
    private Direction direction = Direction.NONE;

    public DefaultElevator(int maxFloor) {
        this.maxFloor = maxFloor;
    }

    public void move(int floor) {
        if (currentFloor < floor) {
            moveUp();
        } else {
            moveDown();
        }
        simulateMovement();

        log.info("Elevator on floor: " + currentFloor + "|" + direction);
    }

    public void stop() {
        log.info("Stopped at: " + currentFloor);

    }

    private void moveDown() {
        if (currentFloor > 0) {
            direction = Direction.DOWN;
            currentFloor--;
        }
    }

    private void moveUp() {
        if (currentFloor < maxFloor) {
            direction = Direction.UP;
            currentFloor++;
        }
    }

    private void simulateMovement() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
