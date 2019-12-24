package programs;

import exception.PathNotImplementedException;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.stream.IntStream;

public class IntCode {

    private long[] codes;
    private int index;
    private List<Long> outputs;
    private List<Integer> inputs;

    public IntCode(long... codes) {
        this.codes = codes;
        index = 0;
        inputs = new ArrayList<>();
        outputs = new Stack<>();
    }

    public void addInputs(int... inputs) {
        for (int input : inputs) {
            this.inputs.add(input);
        }
    }

    public long getFirstCode() {
        return codes[0];
    }

    public long getOutput(int index) {
        return outputs.get(index);
    }

    public long getLastOutput() {
        return outputs.get(outputs.size() - 1);
    }

    public void run_command(boolean first_output) throws PathNotImplementedException {
        while (index < codes.length) {
            if (codes[index] == 99)
                return;
            Instruction from = Instruction.from(index, sub(codes, index));
            execute(from);
            updateIndex(from);
            if (first_output && from.getOpcode() == 4) return;
        }
    }

    private void updateIndex(Instruction instruction) throws PathNotImplementedException {
        index = switch (instruction.getOpcode()) {
            case 1, 2, 7, 8 -> index + 4;
            case 3, 4 -> index + 2;
            case 5 -> codes[instruction.getPos_1()] != 0 ? (int) codes[instruction.getPos_2()] : index + 3;
            case 6 -> codes[instruction.getPos_1()] == 0 ? (int) codes[instruction.getPos_2()] : index + 3;
            default -> throw new PathNotImplementedException();
        };
    }

    private void execute(Instruction instruction) {
        switch (instruction.getOpcode()) {
            case 1, 2, 7, 8 -> instruction.executeOn(codes);
            case 3 -> instruction.executeInputOn(codes, inputs.remove(0));
            case 4 -> outputs.add(instruction.executeOutputOn(codes));
        }
    }

    private int[] sub(long[] codes, int index) {
        return IntStream.range(index, Math.min(index + 4, codes.length))
                .map(s -> (int) codes[s])
                .toArray();
    }

}
