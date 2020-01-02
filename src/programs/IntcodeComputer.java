package programs;

public class IntcodeComputer {

    private int pointer;

    public int[] runProgram(int[] initialState) {
        while (pointer < initialState.length) {

        }
        return null;
    }

    int[] opcode1(int[] initialState) {
        int pos1 = initialState[pointer + 1];
        int pos2 = initialState[pointer + 2];
        int pos3 = initialState[pointer + 3];

        int value1 = initialState[pos1];
        int value2 = initialState[pos2];

        initialState[pos3] = value1 + value2;
        pointer += 3;
        return initialState;
    }

    int[] opcode2(int[] initialState) {
        int pos1 = initialState[pointer + 1];
        int pos2 = initialState[pointer + 2];
        int pos3 = initialState[pointer + 3];

        int value1 = initialState[pos1];
        int value2 = initialState[pos2];

        initialState[pos3] = value1 * value2;
        pointer += 3;
        return initialState;
    }
}
