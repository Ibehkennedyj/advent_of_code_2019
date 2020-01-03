package com.advent_of_code;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day4 {

    Day4__SecureContainer day4;

    @BeforeEach
    void setUp() {
        day4 = new Day4__SecureContainer();
    }

    @Test
    void does_not_decrease() {
        final Integer[] test_cases = {111111, 223450, 123789};

        final List<Boolean> expected = Stream.of(test_cases)
                .map(day4::does_not_decrease)
                .collect(toList());

        assertEquals(List.of(true, false, true), expected);
    }

    @Test
    void has_same_adjacent_number() {
        final Integer[] test_cases = {111111, 223450, 123789};

        final List<Boolean> expected = Stream.of(test_cases)
                .map(day4::has_same_adjacent_number)
                .collect(toList());

        assertEquals(List.of(true, true, false), expected);
    }

    @Test
    void has_isolated_pair() {
        final Integer[] test_cases = {112233, 123444, 111122};

        final List<Boolean> expected = Stream.of(test_cases)
                .map(day4::has_isolated_pair)
                .collect(toList());

        assertEquals(List.of(true, false, true), expected);
    }
}