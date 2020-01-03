package com.advent_of_code.programs;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class IntcodeComputerTest {

    IntcodeComputer computer;

    @BeforeEach
    void setUp() {
        computer = new IntcodeComputer();
    }

    @Test
    void runProgram() {
        assertAll(
                () -> runProgram(new int[]{1, 9, 10, 3, 2, 3, 11, 0, 99, 30, 40, 50},
                        new int[]{3500, 9, 10, 70, 2, 3, 11, 0, 99, 30, 40, 50}, 1),
                () -> runProgram(new int[]{1, 0, 0, 0, 99}, new int[]{2, 0, 0, 0, 99}, 2),
                () -> runProgram(new int[]{2, 3, 0, 3, 99}, new int[]{2, 3, 0, 6, 99}, 3),
                () -> runProgram(new int[]{2, 4, 4, 5, 99, 0}, new int[]{2, 4, 4, 5, 99, 9801}, 4),
                () -> runProgram(new int[]{1, 1, 1, 4, 99, 5, 6, 0, 99},
                        new int[]{30, 1, 1, 4, 2, 5, 6, 0, 99}, 5)
        );
    }

    void runProgram(int[] initialState, int[] finalState, int number) {
        assertEquals(finalState, computer.runProgram(initialState), String.valueOf(number));
    }
}