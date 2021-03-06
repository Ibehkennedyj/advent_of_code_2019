import interfaces.Day;
import interfaces.IntInputs;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class Day4__SecureContainer implements Day, IntInputs {
    @Override
    public int part_one() {
        final int[] range = get_int_inputs("-");
        return (int) IntStream.rangeClosed(range[0], range[1])
                .filter(this::does_not_decrease)
                .filter(this::has_same_adjacent_number)
                .count();
    }

    public boolean does_not_decrease(int i) {
        String s = ("" + i);
        for (int j = 1; j < s.length(); j++) {
            if (s.charAt(j) < s.charAt(j - 1))
                return false;
        }
        return true;
    }

    public boolean has_same_adjacent_number(int i) {
        return !get_adjacent_lengths(i).isEmpty();
    }

    public boolean has_isolated_pair(int i) {
        return get_adjacent_lengths(i).contains(2);
    }

    private List<Integer> get_adjacent_lengths(int i) {
        List<Integer> list = new ArrayList<>();
        String[] s = ("" + i).split("");
        int count = 1;
        for (int j = 1; j < s.length; j++) {
            if (s[j].equals(s[j - 1]))
                count++;
            else {
                if (count > 1)
                    list.add(count);
                count = 1;
            }
        }
        if (count > 1) list.add(count);
        return list;
    }

    @Override
    public int part_two() {
        final int[] range = get_int_inputs("-");
        return (int) IntStream.rangeClosed(range[0], range[1])
                .filter(this::does_not_decrease)
                .filter(this::has_isolated_pair)
                .count();
    }

    @Override
    public String get_puzzle_input() {
        return "271973-785961";
    }

    public static void main(String[] args) {
        new Day4__SecureContainer().print_answers();
    }
}
