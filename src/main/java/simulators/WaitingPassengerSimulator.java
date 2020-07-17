package simulators;

import domain.Building;
import lombok.extern.slf4j.Slf4j;
import model.Direction;
import model.Request;

import java.util.concurrent.ThreadLocalRandom;

import static simulators.SimulationVariables.MAX_NEW_PICKUP_TIME;
import static simulators.SimulationVariables.MIN_NEW_PICKUP_TIME;

@Slf4j
public class WaitingPassengerSimulator implements Runnable {
    Building building;

    public WaitingPassengerSimulator(Building building) {
        this.building = building;
    }

    @Override
    public void run() {
        generatePassangerRequest();

        while (true) {

            try {
                Thread.sleep(ThreadLocalRandom.current().nextInt(MIN_NEW_PICKUP_TIME, MAX_NEW_PICKUP_TIME));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Request r = generatePassangerRequest();
            building.pickup(r);
            log.info("New request: " + r);
        }
    }

    private Request generatePassangerRequest() {
        int floor = ThreadLocalRandom.current().nextInt(0, 11);
        Direction direction = ThreadLocalRandom.current().nextBoolean() ? Direction.UP : Direction.DOWN;

        return new Request(floor, direction);
    }
}
