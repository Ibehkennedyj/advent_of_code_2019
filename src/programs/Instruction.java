package programs;

import java.lang.reflect.MalformedParametersException;

public class Instruction {

    private int opcode;
    private int pos_1;
    private int pos_2;
    private int pos_3;

    static Instruction from(int start, int[] sub) {
        Instruction instruction = new Instruction();

        if (sub.length < 1 || sub.length > 4)
            throw new MalformedParametersException();

        int command = sub[0];
        int opcode = command % 100;
        instruction.opcode = opcode;

        int mode_1 = (command / 100) % 10;
        int mode_2 = (command / 1000) % 10;
        int mode_3 = (command / 10000) % 10;
        switch (opcode) {
            case 1, 2, 7, 8:
                instruction.pos_3 = mode_3 == 0 ? sub[3] : start + 3;
            case 5, 6:
                instruction.pos_2 = mode_2 == 0 ? sub[2] : start + 2;
            case 3, 4:
                instruction.pos_1 = mode_1 == 0 ? sub[1] : start + 1;
        }

        return instruction;
    }

    public int getOpcode() {
        return opcode;
    }

    public int getPos_1() {
        return pos_1;
    }

    public int getPos_2() {
        return pos_2;
    }

    public void executeOn(long[] codes) {
        if (opcode == 1)
            codes[pos_3] = codes[pos_1] + codes[pos_2];
        else if (opcode == 2)
            codes[pos_3] = codes[pos_1] * codes[pos_2];
        else if (opcode == 7)
            codes[pos_3] = codes[pos_1] < codes[pos_2] ? 1 : 0;
        else if (opcode == 8)
            codes[pos_3] = codes[pos_1] == codes[pos_2] ? 1 : 0;
    }

    public void executeInputOn(long[] codes, int input) {
        codes[pos_1] = input;
    }

    public long executeOutputOn(long[] codes) {
        return codes[pos_1];
    }
}
