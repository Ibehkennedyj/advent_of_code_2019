package com.advent_of_code;

import com.advent_of_code.exception.PathNotImplementedException;
import com.advent_of_code.interfaces.Day;
import com.advent_of_code.interfaces.IntInputs;
import com.advent_of_code.programs.IntCode;
import com.advent_of_code.programs.IntCodeComputer_old;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Day7__AmplificationCircuit implements Day, IntInputs {

    @Override
    public int part_one() {
        return possibleCombos(List.of("1", "2", "3", "4", "0"))
                .stream()
                .mapToInt(s -> run_program_with_signal(get_int_inputs(","), s, 0))
                .max()
                .orElse(-1);
        //        return -1;
    }

    @Override
    public int part_two() {
        return 0;
    }

    @Override
    public String get_puzzle_input() {
        return "3, 26, 1001, 26, -4, 26, 3, 27, 1002, 27, 2, 27, 1, 27, 26, 27, 4, 27, 1001, 28, -1, 28, 1005, 28, 6, 99, 0, 0, 5";
    }

    public static void main(String[] args) throws PathNotImplementedException {

        final Day7__AmplificationCircuit day7 = new Day7__AmplificationCircuit();
        day7.print_answers();

        final long[] input = {3, 26, 1001, 26, -4, 26, 3, 27, 1002, 27, 2, 27, 1, 27, 26,
                27, 4, 27, 1001, 28, -1, 28, 1005, 28, 6, 99, 0, 0, 5};
        System.out.println(day7.run_program_with_signal(input, new int[]{9, 8, 7, 6, 5}, 0));
    }

    public List<int[]> possibleCombos(List<String> strings) {
        return IntStream.range(10000, 99999)
                .mapToObj(String::valueOf)
                .filter(s -> {
                    for (String x : strings) {
                        if (!s.contains(x))
                            return false;
                    }
                    return true;
                })
                .filter(s -> s.length() == 5)
                .map(s -> Arrays.stream(s.split("")).mapToInt(Integer::parseInt).toArray())
                .collect(Collectors.toList());
    }

    public int run_program_with_signal(long[] input, int[] signal, int thrust_signal) {
        try {
            return run_program_with_signal_with_throw(input, signal, thrust_signal);
        } catch (PathNotImplementedException e) {
            return -1;
        }
    }

    public int run_program_with_signal_with_throw(long[] input, int[] signal, int thrust_signal) throws PathNotImplementedException {
        IntCodeComputer_old comp = new IntCodeComputer_old(input);
        for (int i : signal) {
            IntCode code = new IntCode(input);
            code.addInputs(i, thrust_signal);
            code.runCommand(true);
            thrust_signal = (int) code.getOutput(0);
        }
        return thrust_signal;
    }
}
