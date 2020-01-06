package com.advent_of_code.interfaces;

import com.advent_of_code.exception.PartNotImplementedException;

public interface Day {

    int partOne() throws PartNotImplementedException;

    int partTwo() throws PartNotImplementedException;

    default void print_answers() {
        try {
            System.out.println("Part One: " + partOne());
            System.out.println("Part Two: " + partTwo());
        } catch (PartNotImplementedException e) {
            e.printStackTrace();
        }
    }
}
