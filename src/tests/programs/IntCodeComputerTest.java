package programs;

import interfaces.IntInputs;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(Parameterized.class)
public class IntCodeComputerTest {


    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"1,9,10,3,2,3,11,0,99,30,40,50", -1, 3500},
                {"1,1,1,4,99,5,6,0,99", -1, 30},
                {"2,4,4,5,99,0", -1, 2},
                {"2,3,0,3,99", -1, 2},
                {"1,0,0,0,99", -1, 2},
                {"1002,4,3,4,33", -1, 1002},
                {"3,9,8,9,10,9,4,9,99,-1,8", 8, 1},
                {"3,9,8,9,10,9,4,9,99,-1,8", 9, 0},
                {"3,21,1008,21,8,20,1005,20,22,107,8,21,20,1006,20,31," +
                        "1106,0,36,98,0,0,1002,21,125,20,4,20,1105,1,46,104," +
                        "999,1105,1,46,1101,1000,1,20,4,20,1105,1,46,98,99", 7, 999},
                {"3,21,1008,21,8,20,1005,20,22,107,8,21,20,1006,20,31," +
                        "1106,0,36,98,0,0,1002,21,125,20,4,20,1105,1,46,104," +
                        "999,1105,1,46,1101,1000,1,20,4,20,1105,1,46,98,99", 8, 1000},
                {"3,21,1008,21,8,20,1005,20,22,107,8,21,20,1006,20,31," +
                        "1106,0,36,98,0,0,1002,21,125,20,4,20,1105,1,46,104," +
                        "999,1105,1,46,1101,1000,1,20,4,20,1105,1,46,98,99", 9, 1001}
        });
    }

    private String inputs;
    private int input;
    private int expected;
    private IntCodeComputer computer;


    public IntCodeComputerTest(String inputs, int input, int expected) {
        this.inputs = inputs;
        this.input = input;
        this.expected = expected;
    }

    @Before
    public void setUp() {
        computer = new IntCodeComputer();
    }

    @Test
    public void run_program() {
        assertEquals(expected, computer.run_program(input, ((IntInputs) () -> inputs).get_int_inputs(",")));
    }
}