import interfaces.Day;
import interfaces.IntInputs;
import programs.IntCodeComputer;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Day7__AmplificationCircuit implements Day, IntInputs {


    @Override
    public int part_one() {
        return possibleCombos(Stream.of("1", "2", "3", "4", "0"))
                .stream()
                .mapToInt(s -> run_program_with_signal(get_int_inputs(","), s, 0))
                .max()
                .orElse(-1);
    }

    @Override
    public int part_two() {
        return 0;
    }

    @Override
    public String get_puzzle_input() {
        return "3,8,1001,8,10,8,105,1,0,0,21,38,55,72,93,118,199,280,361,442,99999,3,9,1001,9,2,9,1002,9,5,9,101,4,9,9,4,9,99,3,9,1002,9,3,9,1001,9,5,9,1002,9,4,9,4,9,99,3,9,101,4,9,9,1002,9,3,9,1001,9,4,9,4,9,99,3,9,1002,9,4,9,1001,9,4,9,102,5,9,9,1001,9,4,9,4,9,99,3,9,101,3,9,9,1002,9,3,9,1001,9,3,9,102,5,9,9,101,4,9,9,4,9,99,3,9,101,1,9,9,4,9,3,9,1001,9,1,9,4,9,3,9,102,2,9,9,4,9,3,9,101,2,9,9,4,9,3,9,1001,9,1,9,4,9,3,9,102,2,9,9,4,9,3,9,1001,9,1,9,4,9,3,9,102,2,9,9,4,9,3,9,102,2,9,9,4,9,3,9,1002,9,2,9,4,9,99,3,9,1001,9,1,9,4,9,3,9,1002,9,2,9,4,9,3,9,1001,9,2,9,4,9,3,9,1002,9,2,9,4,9,3,9,101,2,9,9,4,9,3,9,102,2,9,9,4,9,3,9,102,2,9,9,4,9,3,9,102,2,9,9,4,9,3,9,101,1,9,9,4,9,3,9,101,1,9,9,4,9,99,3,9,101,2,9,9,4,9,3,9,101,1,9,9,4,9,3,9,101,1,9,9,4,9,3,9,102,2,9,9,4,9,3,9,1002,9,2,9,4,9,3,9,101,2,9,9,4,9,3,9,1002,9,2,9,4,9,3,9,1001,9,2,9,4,9,3,9,1002,9,2,9,4,9,3,9,101,1,9,9,4,9,99,3,9,1001,9,1,9,4,9,3,9,1002,9,2,9,4,9,3,9,1001,9,1,9,4,9,3,9,1001,9,2,9,4,9,3,9,102,2,9,9,4,9,3,9,1001,9,1,9,4,9,3,9,1002,9,2,9,4,9,3,9,1001,9,2,9,4,9,3,9,1001,9,2,9,4,9,3,9,102,2,9,9,4,9,99,3,9,101,1,9,9,4,9,3,9,1002,9,2,9,4,9,3,9,101,2,9,9,4,9,3,9,1002,9,2,9,4,9,3,9,101,2,9,9,4,9,3,9,1002,9,2,9,4,9,3,9,101,1,9,9,4,9,3,9,101,2,9,9,4,9,3,9,1002,9,2,9,4,9,3,9,101,1,9,9,4,9,99";
    }

    public static void main(String[] args) {
//        long[] inputs = ((IntInputs) () -> "3,52,1001,52,-5,52,3,53,1,52,56,54,1007,54,5,55,1005,55,26,1001,54,\n" +
//                "-5,54,1105,1,12,1,53,54,53,1008,54,0,55,1001,55,1,55,2,53,55,53,4,\n" +
//                "53,1001,56,-1,56,1005,56,6,99,0,0,0,0,10").get_int_inputs(",");
//        System.out.println(run_program_with_signal(inputs, new int[]{9,7,8,5,6}, 0));

        new Day7__AmplificationCircuit().print_answers();
    }

    public List<int[]> possibleCombos(Stream<String> stringStream) {
        return IntStream.range(10000, 99999)
                .mapToObj(String::valueOf)
                .filter(s -> stringStream.allMatch(s::contains))
                .map(s -> Arrays.stream(s.split("")).distinct().map(Integer::parseInt).collect(Collectors.toList()))
                .filter(s -> s.size() <= 5)
                .map(s -> {
                    if (s.size() == 4)
                        s.add(0, 0);
                    return s.stream().mapToInt(Integer::intValue).toArray();
                })
                .collect(Collectors.toList());
    }

    static public int run_program_with_signal(long[] input, int[] signal, int thrust_signal) {
        IntCodeComputer comp = new IntCodeComputer(input);
        for (int i : signal) {
            thrust_signal = (int) new IntCodeComputer(input).run_program(true, i, thrust_signal);
        }
        return thrust_signal;
    }
}
