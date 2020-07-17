import domain.Building;
import simulators.FloorSelectionSimulator;
import simulators.WaitingPassengerSimulator;

public class Simulation {

    public static void main(String[] args) {
        Building building = new Building(10);

        Runnable controller = building.getElevatorController();
        Thread controllerThread = new Thread(controller);
        controllerThread.start();

        Runnable floorSelectionSimulator = new FloorSelectionSimulator(building);
        Thread floorSelectionThread = new Thread(floorSelectionSimulator);
        floorSelectionThread.start();

        Runnable waitingPassengerSimulator = new WaitingPassengerSimulator(building);
        Thread passangerThread = new Thread(waitingPassengerSimulator);
        passangerThread.start();

    }

}
