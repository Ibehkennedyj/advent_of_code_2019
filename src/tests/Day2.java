import interfaces.IntInputs;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(Parameterized.class)
public class Day2 {

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"1,9,10,3,2,3,11,0,99,30,40,50", 3500},
                {"1,1,1,4,99,5,6,0,99", 30},
                {"2,4,4,5,99,0", 2},
                {"2,3,0,3,99", 2},
                {"1,0,0,0,99", 2}
        });
    }

    private String input;
    private int output;

    public Day2(String input, int output) {
        this.input = input;
        this.output = output;
    }

    @Test
    public void name() {
        assertEquals(output, new Day2__1202ProgramAlert().run_program(((IntInputs) () -> input).get_int_inputs(",")));
    }
}

