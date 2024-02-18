package racinggame.domain;

import java.util.Objects;

public class Car implements Comparable<Car> {

    private static final int INITIAL_POSITION = 0;

    private final Name name;
    private Position position;

    public Car(String name) {
        this(name, INITIAL_POSITION);
    }

    Car(String name, int position) {
        this.name = new Name(name);
        this.position = new Position(position);
    }

    public void move(MoveCondition moveCondition) {
        if (moveCondition.isMovable()) {
            position = position.increase();
        }
    }

    public boolean isRival(Car other) {
        return position.equals(other.position);
    }

    public String getName() {
        return name.getName();
    }

    public int getPosition() {
        return position.getPosition();
    }

    @Override
    public int compareTo(Car other) {
        return position.getPosition() - other.getPosition();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return Objects.equals(name, car.name) && Objects.equals(position, car.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, position);
    }
}
