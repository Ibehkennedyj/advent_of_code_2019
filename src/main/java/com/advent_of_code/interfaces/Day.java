package com.advent_of_code.interfaces;

import com.advent_of_code.exception.PathNotImplementedException;

public interface Day {

    int part_one() throws PathNotImplementedException;

    int part_two();

    default void print_answers() {
        try {
            System.out.println("Part One: " + part_one());
            System.out.println("Part Two: " + part_two());
        } catch (PathNotImplementedException e) {
            e.printStackTrace();
        }
    }
}
