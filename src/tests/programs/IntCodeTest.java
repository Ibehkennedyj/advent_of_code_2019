package programs;

import exception.PathNotImplementedException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IntCodeTest {

    @Test
    void day_2_tests() {
        assertAll(
                () -> {
                    IntCode comp = new IntCode(1, 9, 10, 3, 2, 3, 11, 0, 99, 30, 40, 50);
                    comp.run_command(false);
                    assertEquals(3500, comp.getFirstCode());
                },
                () -> {
                    IntCode comp = new IntCode(1, 1, 1, 4, 99, 5, 6, 0, 99);
                    comp.run_command(false);
                    assertEquals(30, comp.getFirstCode());
                },
                () -> {
                    IntCode comp = new IntCode(1, 0, 0, 0, 99);
                    comp.run_command(false);
                    assertEquals(2, comp.getFirstCode());
                }
        );
    }

    @Test
    void day_5_tests() {
        assertAll(() -> {
                    IntCode comp = new IntCode(3, 0, 4, 0, 99);
                    comp.addInputs(2);
                    comp.run_command(false);
                    assertEquals(2, comp.getOutput(0));
                },
                () -> {
                    IntCode comp = new IntCode(3, 9, 8, 9, 10, 9, 4, 9, 99, -1, 8);
                    comp.addInputs(2);
                    comp.run_command(true);
                    assertEquals(0, comp.getOutput(0));
                },
                () -> {
                    IntCode comp = new IntCode(3, 9, 7, 9, 10, 9, 4, 9, 99, -1, 8);
                    comp.addInputs(2);
                    comp.run_command(true);
                    assertEquals(1, comp.getOutput(0));
                },
                () -> {
                    IntCode comp = new IntCode(3, 3, 1108, -1, 8, 3, 4, 3, 99);
                    comp.addInputs(8);
                    comp.run_command(false);
                    assertEquals(1, comp.getOutput(0));
                },
                () -> {
                    IntCode comp = new IntCode(3, 21, 1008, 21, 8, 20, 1005, 20, 22, 107, 8, 21, 20, 1006,
                            20, 31, 1106, 0, 36, 98, 0, 0, 1002, 21, 125, 20, 4, 20, 1105, 1, 46, 104, 999, 1105,
                            1, 46, 1101, 1000, 1, 20, 4, 20, 1105, 1, 46, 98, 99);
                    comp.addInputs(7);
                    comp.run_command(true);
                    assertEquals(999, comp.getOutput(0));
                },
                () -> {
                    IntCode comp = new IntCode(3, 21, 1008, 21, 8, 20, 1005, 20, 22, 107, 8, 21, 20, 1006, 20, 31,
                            1106, 0, 36, 98, 0, 0, 1002, 21, 125, 20, 4, 20, 1105, 1, 46, 104,
                            999, 1105, 1, 46, 1101, 1000, 1, 20, 4, 20, 1105, 1, 46, 98, 99);
                    comp.addInputs(8);
                    comp.run_command(true);
                    assertEquals(1000, comp.getOutput(0));
                },
                () -> {
                    IntCode comp = new IntCode(3, 21, 1008, 21, 8, 20, 1005, 20, 22, 107, 8, 21, 20, 1006, 20, 31,
                            1106, 0, 36, 98, 0, 0, 1002, 21, 125, 20, 4, 20, 1105, 1, 46, 104,
                            999, 1105, 1, 46, 1101, 1000, 1, 20, 4, 20, 1105, 1, 46, 98, 99);
                    comp.addInputs(1001);
                    comp.run_command(false);
                    assertEquals(1001, comp.getOutput(0));
                }
        );
    }

    @Test
    void test_5_8() throws PathNotImplementedException {
        IntCode comp = new IntCode(1105, 9, 4, 99, 104, 1000, 99);
        comp.run_command(false);
        assertEquals(1000, comp.getOutput(0));
        comp = new IntCode(1106, 9, 4, 99, 104, 1000, 99);
        comp.run_command(false);
        IntCode finalComp = comp;
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> finalComp.getOutput(0));
        comp = new IntCode(1107, 3, 4, 0, 99);
        comp.run_command(false);
        assertEquals(1, comp.getFirstCode());
        comp = new IntCode(1108, 3, 4, 0, 99);
        comp.run_command(false);
        assertEquals(0, comp.getFirstCode());

        comp = new IntCode(5, 1, 4, 99, 4, 5, 99);
        comp.run_command(false);
        assertEquals(5, comp.getOutput(0));
        comp = new IntCode(6, 1, 4, 99, 104, 1000, 99);
        comp.run_command(false);
        IntCode finalComp1 = comp;
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> finalComp1.getOutput(0));
        comp = new IntCode(7, 2, 2, 0, 99);
        comp.run_command(false);
        assertEquals(0, comp.getFirstCode());
        comp = new IntCode(8, 2, 2, 0, 99);
        comp.run_command(false);
        assertEquals(1, comp.getFirstCode());

        comp = new IntCode(3, 0, 99);
        comp.addInputs(1000);
        comp.run_command(false);
        assertEquals(1000, comp.getFirstCode());
        comp = new IntCode(3, 0, 3, 2, 99);
        comp.addInputs(1000);
        IntCode finalComp2 = comp;
        assertThrows(IndexOutOfBoundsException.class, () -> finalComp2.run_command(false));
    }
}