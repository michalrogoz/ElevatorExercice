package model;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Request {
    private int floor;
    private Direction direction;

    @Override
    public String toString() {
        return "floor=" + floor + ", direction=" + direction;
    }
}
