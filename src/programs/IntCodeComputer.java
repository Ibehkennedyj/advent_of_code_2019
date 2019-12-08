package programs;

import java.util.ArrayList;
import java.util.List;

public class IntCodeComputer {

    public int run_program(int input, int[] in) {

        List<Integer> output = new ArrayList<>();

        for (int i = 0; i <= in.length; ) {

            int command = in[i];
            int opcode = command % 100;
            int mode_a = (command / 100) % 10;
            int mode_b = (command / 1000) % 10;
            int mode_out = (command / 10000) % 10;
            if (opcode == 99) {
                if (output.isEmpty())
                    return in[0];
                return output.get(output.size() - 1);
            }

            int pos_a = mode_a == 0 ? in[i + 1] : i + 1;
            int pos_b = mode_b == 0 ? in[i + 2] : i + 2;
            int pos_out = mode_out == 0 ? in[i + 3] : i + 3;

            if (opcode == 1) {
                in[pos_out] = in[pos_a] + in[pos_b];
                i += 4;
            } else if (opcode == 2) {
                in[pos_out] = in[pos_a] * in[pos_b];
                i += 4;
            } else if (opcode == 3) {
                in[pos_a] = input;
                i += 2;
            } else if (opcode == 4) {
                output.add(in[pos_a]);
                i += 2;
            } else if (opcode == 5) {
                if (in[pos_a] != 0)
                    i = in[pos_b];
                else
                    i += 3;
            } else if (opcode == 6) {
                if (in[pos_a] == 0)
                    i = in[pos_b];
                else
                    i += 3;
            } else if (opcode == 7) {
                in[pos_out] = in[pos_a] < in[pos_b] ? 1 : 0;
                i += 4;
            } else if (opcode == 8) {
                in[pos_out] = in[pos_a] == in[pos_b] ? 1 : 0;
                i += 4;
            }
        }
        return -1;
    }
    
}
