package com.advent_of_code.programs;

import java.lang.reflect.MalformedParametersException;

import static com.advent_of_code.util.Number.*;

public class Instruction {

    private int opcode;
    private int firstPosition;
    private int secondPosition;
    private int thirdPosition;

    static Instruction from(int start, int[] sub) {
        Instruction instruction = new Instruction();

        if (ONE > sub.length || FOUR < sub.length)
            throw new MalformedParametersException();

        int command = sub[ZERO];
        int opcode = command % ONE_HUNDRED;
        instruction.opcode = opcode;

        int firstMode = (command / ONE_HUNDRED) % TEN;
        int secondMode = (command / ONE_THOUSAND) % TEN;
        int thirdMode = (command / TEN_THOUSAND) % TEN;

        switch (opcode) {
            case ONE, TWO, SEVEN, EIGHT:
                instruction.thirdPosition = enumerateThirdPositionInstruction(start, sub, thirdMode);
            case FIVE, SIX:
                instruction.secondPosition = enumerateSecondPositionInstruction(start, sub, secondMode);
            case THREE, FOUR:
                instruction.firstPosition = enumerateFirstPositionInstruction(start, sub, firstMode);
        }

        return instruction;
    }

    private static int enumerateFirstPositionInstruction(int start, int[] sub, int firstMode) {
        return ZERO == firstMode ? sub[ONE] : start + ONE;
    }

    private static int enumerateSecondPositionInstruction(int start, int[] sub, int secondMode) {
        return ZERO == secondMode ? sub[TWO] : start + TWO;
    }

    private static int enumerateThirdPositionInstruction(int start, int[] sub, int thirdMode) {
        return ZERO == thirdMode ? sub[THREE] : start + THREE;
    }

    public int getOpcode() {
        return this.opcode;
    }

    public int getFirstPosition() {
        return this.firstPosition;
    }

    public int getSecondPosition() {
        return this.secondPosition;
    }

    public void executeOn(long[] codes) {
        if (ONE == opcode)
            codes[thirdPosition] = codes[firstPosition] + codes[secondPosition];
        else if (TWO == opcode)
            codes[thirdPosition] = codes[firstPosition] * codes[secondPosition];
        else if (SEVEN == opcode)
            codes[thirdPosition] = codes[firstPosition] < codes[secondPosition] ? ONE : ZERO;
        else if (EIGHT == opcode)
            codes[thirdPosition] = codes[firstPosition] == codes[secondPosition] ? ONE : ZERO;
    }

    public void executeInputOn(long[] codes, int input) {
        codes[firstPosition] = input;
    }

    public long executeOutputOn(long[] codes) {
        return codes[firstPosition];
    }
}
