import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day6 {

    Day6__UniversalOrbitMap day6 = new Day6__UniversalOrbitMap();

    @Test
    void map_1_planet_from_input() {
        String input = "COM)B";
        Day6__UniversalOrbitMap.Planet planet = Day6__UniversalOrbitMap.Planet.from(input);
        assertEquals("B", planet.getName());
        assertEquals(new Day6__UniversalOrbitMap.Planet("COM"), planet.getParent());
    }

    @Test
    void map_planets_from_input() {
        String input = day6.get_puzzle_input();
        Set<Day6__UniversalOrbitMap.Planet> planets = new HashSet<>();
        Arrays.stream(input.split("\n"))
                .map(Day6__UniversalOrbitMap.Planet::from)
                .forEach(planet -> {
                    planets.add(planet);
                    planets.add(planet.getParent());
                });
        assertEquals(13, planets.size());
    }

    @Test
    void get_distance_between_2_planets() {
        String input = day6.get_puzzle_input();
        Set<Day6__UniversalOrbitMap.Planet> planets = new HashSet<>();
        Arrays.stream(input.split("\n"))
                .map(Day6__UniversalOrbitMap.Planet::from)
                .forEach(planet -> {
                    planets.add(planet);
                    planets.add(planet.getParent());
                });
        assertEquals(5, distance(planets, Day6__UniversalOrbitMap.Planet.from("E)F"), new Day6__UniversalOrbitMap.Planet("COM")));
    }

    @Test
    void get_distance_between_parent_and_child() {
        Day6__UniversalOrbitMap.Planet child = Day6__UniversalOrbitMap.Planet.from("COM)B");
        Day6__UniversalOrbitMap.Planet parent = new Day6__UniversalOrbitMap.Planet("COM");
        Set<Day6__UniversalOrbitMap.Planet> planets = Set.of(child, parent);
        assertEquals(1, distance(planets, child, parent));
    }

    @Test
    void ensure_planets_are_in_constellation() {
    }

    int distance(Set<Day6__UniversalOrbitMap.Planet> in, Day6__UniversalOrbitMap.Planet a, Day6__UniversalOrbitMap.Planet b) {
        List<Day6__UniversalOrbitMap.Planet> list = List.copyOf(in);
        int distance = 1;
        while (!a.getParent().equals(b)) {
            int index = list.indexOf(a.getParent());
            a = list.get(index);
            distance++;
        }
        return distance;
    }

    class Constellation {

        int distance_between(Day6__UniversalOrbitMap.Planet a, Day6__UniversalOrbitMap.Planet b) {
            return 1;
        }
    }


}