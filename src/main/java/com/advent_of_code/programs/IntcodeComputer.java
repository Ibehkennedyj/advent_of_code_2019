package com.advent_of_code.programs;

import com.advent_of_code.util.Number;

public class IntcodeComputer {

    private int pointer;

    public int[] runProgram(int[] initialState) {
        while (pointer < initialState.length) {

        }
        return null;
    }

    int[] opcode1(int[] initialState) {
        int pos1 = initialState[pointer + Number.ONE];
        int pos2 = initialState[pointer + Number.TWO];
        int pos3 = initialState[pointer + Number.THREE];

        int value1 = initialState[pos1];
        int value2 = initialState[pos2];

        initialState[pos3] = value1 + value2;
        pointer += Number.THREE;
        return initialState;
    }

    int[] opcode2(int[] initialState) {
        int pos1 = initialState[pointer + Number.ONE];
        int pos2 = initialState[pointer + Number.TWO];
        int pos3 = initialState[pointer + Number.THREE];

        int value1 = initialState[pos1];
        int value2 = initialState[pos2];

        initialState[pos3] = value1 * value2;
        pointer += Number.THREE;
        return initialState;
    }
}
