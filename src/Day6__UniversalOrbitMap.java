import interfaces.Day;
import interfaces.Reader;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Day6__UniversalOrbitMap implements Day, Reader {


    @Override
    public String get_puzzle_input() {
        return "COM)B\n" +
                "B)C\n" +
                "C)D\n" +
                "D)E\n" +
                "E)F\n" +
                "B)G\n" +
                "G)H\n" +
                "D)I\n" +
                "E)J\n" +
                "J)K\n" +
                "K)L\n" +
                "COM)Z";
    }

    @Override
    public int part_one() {
        return Constellation.from(get_puzzle_input().split("\n")).total_orbit();
    }

    @Override
    public int part_two() {
        return 0;
    }

    public static void main(String[] args) {
        new Day6__UniversalOrbitMap().print_answers();
    }

    static class Constellation {

        List<Planet> planets = new ArrayList<>();

        static Constellation from(String[] x) {
            Constellation constellation = new Constellation();
            for (String z : x) {
                String[] split = z.split("\\)");
                Planet parent = new Planet(split[0]);
                Planet child = new Planet(split[1]);
                child.setParent(parent);
                constellation.updateWith(parent, child);
            }
            return constellation;
        }

        private void updateWith(Planet parent, Planet child) {
            planets.add(child);
            if (planets.indexOf(parent) == -1)
                planets.add(parent);
            int parent_position = planets.indexOf(parent);
            planets.get(parent_position).addChild(child);
        }

        int distance_between(Planet child) {
            int distance = 0;
            while (child.getParent() != null) {
                distance++;
                child = planets.get(planets.indexOf(child.getParent()));
            }
            return distance;
        }

        int total_orbit() {
            Planet base = planets.stream().filter(planet -> planet.getParent() == null).findFirst().get();
            return planets.stream()
                    .mapToInt(this::distance_between)
                    .sum();
        }
    }

    static class Planet {

        String name;
        Planet parent;
        List<Planet> children;

        public Planet(String name) {
            this.name = name;
            children = new ArrayList<>();
        }

        public static Planet from(String input) {
            String[] names_of_planets = input.split("\\)");
            Planet parent = new Planet(names_of_planets[0]);
            Planet this_planet = new Planet(names_of_planets[1]);
            this_planet.setParent(parent);
            return this_planet;
        }

        private void setParent(Planet parent) {
            this.parent = parent;
        }

        private void addChild(Planet child) {
            children.add(child);
        }

        public String getName() {
            return name;
        }

        public Planet getParent() {
            return parent;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Planet planet = (Planet) o;
            return name.equals(planet.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name);
        }
    }

}
