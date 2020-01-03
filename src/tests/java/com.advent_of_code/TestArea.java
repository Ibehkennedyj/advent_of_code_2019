package com.advent_of_code;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestArea {

    private Day1__TheTyrannyOfTheRocketEquation day1;

    @BeforeEach
    void setUp() {
        day1 = new Day1__TheTyrannyOfTheRocketEquation();
    }

    @Test
    public void test_fuel_per_mass() {
        assertEquals(2, day1.fuelPerMass(12));
        assertEquals(2, day1.fuelPerMass(14));
        assertEquals(3, day1.fuelPerMass(15));
        assertEquals(654, day1.fuelPerMass(1969));
        assertEquals(33583, day1.fuelPerMass(100756));
    }

    @Test
    public void test_fuel_per_module() {
        assertEquals(2, day1.fuelPerMass(12));
        assertEquals(2, day1.fuelPerMass(14));
        assertEquals(3, day1.fuelPerMass(15));
        assertEquals(966, day1.fuelPerMass(1969));
        assertEquals(50346, day1.fuelPerMass(100756));
    }

}
