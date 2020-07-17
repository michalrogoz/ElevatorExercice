package simulators;

import domain.Building;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ThreadLocalRandom;

import static simulators.SimulationVariables.MAX_NEW_FLOOR_CHOOSE_TIME;
import static simulators.SimulationVariables.MIN_NEW_FLOOR_CHOOSE_TIME;

@Slf4j
public class FloorSelectionSimulator implements Runnable {
    Building building;

    public FloorSelectionSimulator(Building building) {
        this.building = building;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(ThreadLocalRandom.current().nextInt(MIN_NEW_FLOOR_CHOOSE_TIME, MAX_NEW_FLOOR_CHOOSE_TIME));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            int floor = ThreadLocalRandom.current().nextInt(0, 11);
            building.chooseFloor(floor);
            log.info("Floor choosed: " + floor);
        }

    }
}
