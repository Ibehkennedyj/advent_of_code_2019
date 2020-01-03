package com.advent_of_code.programs;

import com.advent_of_code.exception.PathNotImplementedException;

import java.util.*;
import java.util.stream.IntStream;

import static com.advent_of_code.util.Number.*;

public class IntCode {

    private long[] codes;
    private int index;
    private List<Long> outputs;
    private List<Integer> inputs;

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

    public void runCommand(boolean firstOutput) throws PathNotImplementedException {
        while (index < codes.length) {

            if (NINETY_NINE == codes[index])
                return;

            Instruction instruction = Instruction.from(index, sub(codes, index));

            execute(instruction);

            updateIndex(instruction);

            if (firstOutput && FOUR == instruction.getOpcode())
                return;
        }
    }

    private void updateIndex(Instruction instruction) throws PathNotImplementedException {
        index = switch (instruction.getOpcode()) {
            case ONE, TWO, SEVEN, EIGHT -> index + FOUR;

            case THREE, FOUR -> index + TWO;

            case FIVE -> ZERO != codes[instruction.getFirstPosition()] ? (int) codes[instruction.getSecondPosition()] : index + THREE;

            case SIX -> ZERO == codes[instruction.getFirstPosition()] ? (int) codes[instruction.getSecondPosition()] : index + THREE;

            default -> throw new PathNotImplementedException();
        };
    }

    private void execute(Instruction instruction) {
        switch (instruction.getOpcode()) {
            case ONE, TWO, SEVEN, EIGHT -> instruction.executeOn(codes);

            case THREE -> instruction.executeInputOn(codes, inputs.remove(ZERO));

            case FOUR -> outputs.add(instruction.executeOutputOn(codes));
        }
    }

    private int[] sub(long[] codes, int index) {
        return IntStream.range(index, Math.min(index + FOUR, codes.length))
                .map(s -> (int) codes[s])
                .toArray();
    }

}
