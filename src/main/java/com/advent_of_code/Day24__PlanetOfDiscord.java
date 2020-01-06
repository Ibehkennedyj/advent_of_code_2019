package com.advent_of_code;

import com.advent_of_code.interfaces.Day;
import com.advent_of_code.interfaces.Reader;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import static java.lang.String.valueOf;

public class Day24__PlanetOfDiscord implements Day, Reader {

    private static final Logger logger = Logger.getLogger(Day24__PlanetOfDiscord.class.getName());

    @Override
    public int partOne() {
        int[][] grid = readGrid();
        List<Long> biodiversityRatings = new ArrayList<>();
        while (!biodiversityRatings.contains(getBiodiversityRating(grid))) {
            biodiversityRatings.add(getBiodiversityRating(grid));
            grid = updateGrid(grid);
        }

        logger.info("BioDiversity Rating -> %d" + getBiodiversityRating(grid));
        return (int) getBiodiversityRating(grid);
    }

    @Override
    public int partTwo() {
        return 0;
    }

    public int[][] readGrid() {
        String[] lines = getPuzzleInput().split("\n");
        int[][] grid = new int[5][5];
        for (int i = 0; i < 5; i++) {
            String line = lines[i];
            for (int j = 0; j < 5; j++) {
                grid[i][j] = switch (line.charAt(j)) {
                    case '.' -> 0;
                    case '#' -> 1;
                    default -> throw new UnsupportedOperationException();
                };
            }
        }
        return grid;
    }

    public int[][] updateGrid(int[][] grid) {
        int[][] update = new int[5][5];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                int up = j - 1 < 0 ? 0 : grid[i][j - 1];
                int down = j + 1 >= 5 ? 0 : grid[i][j + 1];
                int left = i - 1 < 0 ? 0 : grid[i - 1][j];
                int right = i + 1 >= 5 ? 0 : grid[i + 1][j];
                int adjacentValue = up + down + left + right;
                if (grid[i][j] == 1)
                    update[i][j] = adjacentValue == 1 ? 1 : 0;
                else if (grid[i][j] == 0)
                    update[i][j] = adjacentValue == 2 || adjacentValue == 1 ? 1 : 0;
            }
        }
        return update;
    }

    public long getBiodiversityRating(int[][] grid) {
        long biodiversityRating = 0;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                int b = (5 * i) + j;
                biodiversityRating += grid[i][j] * Math.pow(2, b);
            }
        }
        return biodiversityRating;
    }

    public static void main(String[] args) {
        Day24__PlanetOfDiscord day24 = new Day24__PlanetOfDiscord();
        logger.info(() -> valueOf(day24.getBiodiversityRating(day24.readGrid())));
        day24.print_answers();
    }

    @Override
    public String getPuzzleInput() {
        return "..###\n" +
                ".####\n" +
                "...#.\n" +
                ".#..#\n" +
                "#.###";
    }
}
