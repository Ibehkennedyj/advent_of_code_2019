package com.advent_of_code.interfaces;

import java.util.Arrays;
import java.util.stream.IntStream;

public interface IntInputs extends Reader {

    default IntStream get_int_inputs_stream(String delimiter) {
        return Arrays.stream(get_puzzle_input().split(delimiter))
                .map(String::trim)
                .mapToInt(Integer::parseInt);
    }

    default long[] get_int_inputs(String delimiter) {
        return Arrays.stream(get_puzzle_input().split(delimiter))
                .map(String::trim)
                .mapToLong(Long::parseLong)
                .toArray();
    }

}
