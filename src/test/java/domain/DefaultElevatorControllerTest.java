package domain;

import model.Request;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static model.Direction.DOWN;
import static model.Direction.UP;
import static org.assertj.core.api.Assertions.assertThat;

class DefaultElevatorControllerTest {

    Building building = new Building(10);

    @Test
    void elevatorShouldVisitAllChoosenFloors() {
        List<Integer> choosenFloors = Arrays.asList(2, 4, 1, 6, 5);

        choosenFloors.forEach(f -> building.chooseFloor(f));
        building.getElevatorController().controlElevator();

        assertThat(building.getElevatorController().getChoosenFloors()).isEmpty();
    }

    @Test
    void elevatorShouldGoToPickupPointVisitingOtherPickupsWithTheSameDirection() {
        List<Request> pickups = Arrays.asList(new Request(5, UP), new Request(3, DOWN), new Request(4, UP));

        pickups.forEach(r -> building.pickup(r));
        building.getElevatorController().controlElevator();

        assertThat(building.getElevatorController().getPickups()).contains(new Request(3, DOWN));
    }

    @Test
    void elevatorShouldProcessAllPickupPoints() {
        List<Request> pickups = Arrays.asList(new Request(5, UP), new Request(3, DOWN), new Request(4, UP));

        pickups.forEach(r -> building.pickup(r));
        building.getElevatorController().controlElevator();
        building.getElevatorController().controlElevator();

        assertThat(building.getElevatorController().getPickups()).isEmpty();
    }


}