package programs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class IntCodeComputer_old {

    public List<Integer> outputs;
    private List<Integer> inputs;
    private long[] intcode;
    private int input_index;
    private int i;

    public IntCodeComputer_old(long[] intcode) {
        this.intcode = intcode;
        outputs = new ArrayList<>();
        inputs = new ArrayList<>();
    }

    public IntCodeComputer_old(long[] intcode, List<Integer> inputs) {
        this(intcode);
        this.inputs = inputs;
    }

    public long run_program(Integer... inputs) {
        return run_program(false, inputs);
    }

    public long run_program(boolean first_output, Integer... inputs) {
        this.inputs.addAll(Arrays.asList(inputs));
        outputs = new ArrayList<>();
        int input_index = 0;

        while (i <= intcode.length) {

            int command = (int) intcode[i];
            int opcode = command % 100;
            int mode_a = (command / 100) % 10;
            int mode_b = (command / 1000) % 10;
            int mode_out = (command / 10000) % 10;
            if (opcode == 99) {
                if (outputs.isEmpty())
                    return intcode[0];
                return outputs.get(outputs.size() - 1);
            }

            int pos_a = mode_a == 0 ? (int) intcode[i + 1] : i + 1;
            int pos_b = mode_b == 0 ? (int) intcode[i + 2] : i + 2;
            int pos_out = mode_out == 0 ? (int) intcode[i + 3] : i + 3;

            if (opcode == 1) {
                intcode[pos_out] = intcode[pos_a] + intcode[pos_b];
            } else if (opcode == 2) {
                intcode[pos_out] = intcode[pos_a] * intcode[pos_b];
            } else if (opcode == 3) {
                intcode[pos_a] = inputs[input_index++];
            } else if (opcode == 4) {
                if (first_output) return intcode[pos_a];
                outputs.add((int) intcode[pos_a]);
            } else if (opcode == 5) {
                if (intcode[pos_a] != 0)
                    i = (int) intcode[pos_b];
                else
                    i += 3;
            } else if (opcode == 6) {
                if (intcode[pos_a] == 0)
                    i = (int) intcode[pos_b];
                else
                    i += 3;
            } else if (opcode == 7) {
                intcode[pos_out] = intcode[pos_a] < intcode[pos_b] ? 1 : 0;
            } else if (opcode == 8) {
                intcode[pos_out] = intcode[pos_a] == intcode[pos_b] ? 1 : 0;
            }

            update_index(opcode);
        }
        return -1;
    }

    void update_index(int opcode) {
        i = switch (opcode) {
            case 1, 2, 7, 8 -> i + 4;
            case 3, 4 -> i + 2;
            default -> i;
        };
    }

}
