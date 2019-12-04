package interfaces;

public interface Day {

    int part_one();

    int part_two();

    default void print_answers() {
        System.out.println("Part One: " + part_one());
        System.out.println("Part Two: " + part_two());
    }
}
