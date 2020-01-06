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

        validateParameters(sub);

        int command = sub[ZERO];
        instruction.opcode = extractOpcode(command);

        switch (instruction.opcode) {
            case ONE, TWO, SEVEN, EIGHT -> instruction.thirdPosition = resolveThirdPosition(start, sub, command);
            case FIVE, SIX -> instruction.secondPosition = resolveSecondPosition(start, sub, command);
            case THREE, FOUR -> instruction.firstPosition = resolveFirstPosition(start, sub, command);
            default -> doNothing();
        }

        return instruction;
    }

    private static void doNothing() {
        //TODO: Create a behaviour;
    }

    static int extractOpcode(int command) {
        return command % ONE_HUNDRED;
    }

    static void validateParameters(int[] sub) {
        if (ONE > sub.length || FOUR < sub.length)
            throw new MalformedParametersException();
    }

    private static int resolveFirstPosition(int start, int[] sub, int command) {
        int firstMode = calculateFirstMode(command);

        return ZERO == firstMode ? sub[ONE] : start + ONE;
    }

    private static int resolveSecondPosition(int start, int[] sub, int command) {
        int secondMode = calculateSecondMode(command);

        return ZERO == secondMode ? sub[TWO] : start + TWO;
    }

    private static int resolveThirdPosition(int start, int[] sub, int command) {
        int thirdMode = calculateThirdMode(command);

        return ZERO == thirdMode ? sub[THREE] : start + THREE;
    }

    static int calculateFirstMode(int command) {
        return (command / ONE_HUNDRED) % TEN;
    }

    static int calculateSecondMode(int command) {
        return (command / ONE_THOUSAND) % TEN;
    }

    static int calculateThirdMode(int command) {
        return (command / TEN_THOUSAND) % TEN;
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

    public int getThirdPosition() {
        return this.thirdPosition;
    }

}
