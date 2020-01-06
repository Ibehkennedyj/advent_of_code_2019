package com.advent_of_code.interfaces;

import java.util.Arrays;
import java.util.stream.IntStream;

public interface IntInputs extends Reader {

    default IntStream getIntInputsStream(String delimiter) {
        return Arrays.stream(getPuzzleInput().split(delimiter))
                .map(String::trim)
                .mapToInt(Integer::parseInt);
    }

    default long[] getIntInputs(String delimiter) {
        return Arrays.stream(getPuzzleInput().split(delimiter))
                .map(String::trim)
                .mapToLong(Long::parseLong)
                .toArray();
    }

}
