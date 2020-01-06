package com.advent_of_code.programs;

import java.util.*;
import java.util.stream.IntStream;

import static com.advent_of_code.util.Number.*;

public class IntCode {

    private long[] codes;
    private int index;
    private List<Long> outputs;
    private List<Integer> inputs;
    private Instruction instruction;

    public IntCode(long... codes) {
        this.codes = codes;
        index = ZERO;
        inputs = new ArrayList<>();
        outputs = new Stack<>();
    }

    public void addInputs(Integer... inputs) {
        this.inputs.addAll(Arrays.asList(inputs));
    }

    public long getFirstCode() {
        return codes[ZERO];
    }

    public long getOutput(int index) {
        return outputs.get(index);
    }

    public void runCommand(boolean firstOutput) {
        while (isValidIndex() && isProgramUnfinished() && isNotHaltInstruction(firstOutput)) {

            instruction = Instruction.from(index, sub(codes, index));

            execute();

            index = resolveIndex();
        }
    }

    private boolean isNotHaltInstruction(boolean firstOutput) {
        return instruction == null || !(firstOutput && FOUR == instruction.getOpcode());
    }

    private boolean isValidIndex() {
        return index < codes.length;
    }

    private boolean isProgramUnfinished() {
        return NINETY_NINE != codes[index];
    }

    private int[] sub(long[] codes, int index) {
        return IntStream.range(index, Math.min(index + FOUR, codes.length))
                .map(s -> (int) codes[s])
                .toArray();
    }

    private int resolveIndex() {
        return switch (instruction.getOpcode()) {
            case ONE, TWO, SEVEN, EIGHT -> index + FOUR;

            case THREE, FOUR -> index + TWO;

            case FIVE -> ZERO != codes[instruction.getFirstPosition()] ? (int) codes[instruction.getSecondPosition()] : index + THREE;

            case SIX -> ZERO == codes[instruction.getFirstPosition()] ? (int) codes[instruction.getSecondPosition()] : index + THREE;

            default -> throw new UnsupportedOperationException();
        };
    }

    private void execute() {
        switch (instruction.getOpcode()) {
            case ONE, TWO, SEVEN, EIGHT -> codes[instruction.getThirdPosition()] = enumerateState();

            case THREE -> codes[instruction.getFirstPosition()] = inputs.remove(ZERO);

            case FOUR -> outputs.add(extractOutputFromCodes());

            default -> doNothing();
        }
    }

    private long enumerateState() {

        int instructionFirstPosition = instruction.getFirstPosition();
        int instructionSecondPosition = instruction.getSecondPosition();
        int instructionThirdPosition = instruction.getThirdPosition();

        return switch (instruction.getOpcode()) {
            case ONE -> codes[instructionFirstPosition] + codes[instructionSecondPosition];
            case TWO -> codes[instructionFirstPosition] * codes[instructionSecondPosition];
            case SEVEN -> codes[instructionFirstPosition] < codes[instructionSecondPosition] ? ONE : ZERO;
            case EIGHT -> codes[instructionFirstPosition] == codes[instructionSecondPosition] ? ONE : ZERO;
            default -> codes[instructionThirdPosition];
        };
    }

    private long extractOutputFromCodes() {
        return codes[instruction.getFirstPosition()];
    }

    private void doNothing() {
        //TODO: Create a behaviour;
    }

}
