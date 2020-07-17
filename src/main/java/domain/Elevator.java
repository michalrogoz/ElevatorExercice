package domain;

public interface Elevator {
    void move(int floor);

    void stop();

    default void openDoor() {
        throw new UnsupportedOperationException();
    }

    default void closeDoor() {
        throw new UnsupportedOperationException();
    }
}