package com.advent_of_code.programs;

import com.advent_of_code.interfaces.IntInputs;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(Parameterized.class)
public class IntCodeComputerOldTest {

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"1,9,10,3,2,3,11,0,99,30,40,50", new Integer[]{}, 3500},
                {"1,1,1,4,99,5,6,0,99", new Integer[]{}, 30},
                {"2,4,4,5,99,0", new Integer[]{}, 2},
                {"2,3,0,3,99", new Integer[]{}, 2},
                {"1,0,0,0,99", new Integer[]{}, 2},
                {"1002,4,3,4,33", new Integer[]{}, 1002},
                {"3,9,8,9,10,9,4,9,99,-1,8", new Integer[]{8}, 1},
                {"3,9,8,9,10,9,4,9,99,-1,8", new Integer[]{7}, 0},
                {"3,21,1008,21,8,20,1005,20,22,107,8,21,20,1006,20,31," +
                        "1106,0,36,98,0,0,1002,21,125,20,4,20,1105,1,46,104," +
                        "999,1105,1,46,1101,1000,1,20,4,20,1105,1,46,98,99", new Integer[]{7}, 999},
                {"3,21,1008,21,8,20,1005,20,22,107,8,21,20,1006,20,31," +
                        "1106,0,36,98,0,0,1002,21,125,20,4,20,1105,1,46,104," +
                        "999,1105,1,46,1101,1000,1,20,4,20,1105,1,46,98,99", new Integer[]{8}, 1000},
                {"3,21,1008,21,8,20,1005,20,22,107,8,21,20,1006,20,31," +
                        "1106,0,36,98,0,0,1002,21,125,20,4,20,1105,1,46,104," +
                        "999,1105,1,46,1101,1000,1,20,4,20,1105,1,46,98,99", new Integer[]{90794}, 1001}
        });
    }

    private String inputs;
    private Integer[] input;
    private int expected;
    private IntCodeComputer_old computer;

    public IntCodeComputerOldTest(String inputs, Integer[] input, int expected) {
        this.inputs = inputs;
        this.input = input;
        this.expected = expected;
    }

    @BeforeEach
    public void setUp() {
        computer = new IntCodeComputer_old(((IntInputs) () -> inputs).get_int_inputs(","));
    }

    @Test
    public void run_program() {
        assertEquals(expected, computer.run_program(input));
    }
}