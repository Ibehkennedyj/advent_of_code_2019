package com.advent_of_code;

import com.advent_of_code.exception.PathNotImplementedException;
import com.advent_of_code.interfaces.Day;
import com.advent_of_code.interfaces.Reader;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Day22__SlamShuffle implements Day, Reader {
    @Override
    public int part_one() {
        final String[] commands = get_puzzle_input().split("\n");
        long position = 2019;
        return (int) executeCommands(commands, position, 10007);
    }

    private long executeCommands(String[] commands, long position, long deckSize) {
        for (String command : commands)
            position = shuffleDeck(deckSize, position, command);
        return position;
    }

    public <T> List<T> shuffleDeck(List<T> factoryDeck, String command) {
        if (command.equals("deal into new stack"))
            factoryDeck = newStack(factoryDeck);
        else if (command.contains("cut"))
            factoryDeck = cut(factoryDeck, Integer.parseInt(command.split(" ")[1]));
        else if (command.contains("increment"))
            factoryDeck = increment(factoryDeck, Integer.parseInt(command.split(" ")[3]));
        return factoryDeck;
    }

    public long shuffleDeck(long deckSize, long position, String command) {
        if (command.equals("deal into new stack"))
            position = newStack(deckSize, position);
        else if (command.contains("cut"))
            position = cut(deckSize, Long.parseLong(command.split(" ")[1]), position);
        else if (command.contains("increment"))
            position = increment(deckSize, Long.parseLong(command.split(" ")[3]), position);
        return position;
    }

    @Override
    public int part_two() {
        final String[] commands = get_puzzle_input().split("\n");
        List<Long> subSet = List.of(0L, 1L, 2L);
        long resetCount = 0;
        long deckSize = 119315717514047L;
        do {
            resetCount++;
            subSet = subSet.stream()
                    .map(l -> executeCommands(commands, l, deckSize))
                    .collect(Collectors.toList());
        } while (!subSet.equals(List.of(0L, 1L, 2L)));
        System.out.println(resetCount);
        //        long count = deckSize % resetCount;
        //        long position = 2019;
        //        for (int i = 0; i < count; i++) {
        //            position = executeCommands(commands, position, deckSize);
        //        }
        //        return (int) position;
        return -1;
    }

    public <T> List<T> newStack(List<T> deck) {
        List<T> newStack = new ArrayList<>();
        deck.forEach(i -> newStack.add(0, i));
        return newStack;
    }

    public long newStack(long deckSize, long cardPosition) {
        return deckSize - cardPosition - 1;
    }

    public long cut(long deckSize, long n, long cardPosition) {
        cardPosition -= n;
        if (cardPosition < 0)
            return cardPosition + deckSize;
        if (cardPosition >= deckSize)
            return cardPosition - deckSize;
        return cardPosition;
    }

    public long increment(long deckSize, long n, long cardPosition) {
        return (n * cardPosition) % deckSize;
    }

    public <T> List<T> cut(List<T> deck, int n) {
        List<T> newStack = new ArrayList<>();
        if (n < 0)
            n = deck.size() + n;
        newStack.addAll(deck.subList(n, deck.size()));
        newStack.addAll(deck.subList(0, n));
        return newStack;
    }

    public <T> List<T> increment(List<T> deck, int n) {
        List<T> newStack = new ArrayList<>(deck);
        for (int i = 0; i < deck.size(); i++) {
            newStack.set((i * n) % deck.size(), deck.get(i));
        }
        return newStack;
    }

    public static void main(String[] args) throws PathNotImplementedException {
        Day22__SlamShuffle day22 = new Day22__SlamShuffle();
        day22.print_answers();
        //        System.out.println(day22.part_one());
    }

    @Override
    public String get_puzzle_input() {
        return "deal with increment 61\n" +
                "cut 7724\n" +
                "deal into new stack\n" +
                "cut -7151\n" +
                "deal with increment 22\n" +
                "deal into new stack\n" +
                "deal with increment 11\n" +
                "cut -2506\n" +
                "deal with increment 14\n" +
                "cut 9670\n" +
                "deal with increment 59\n" +
                "cut 3341\n" +
                "deal into new stack\n" +
                "cut 9816\n" +
                "deal with increment 3\n" +
                "cut -7547\n" +
                "deal with increment 31\n" +
                "cut 7178\n" +
                "deal into new stack\n" +
                "deal with increment 52\n" +
                "deal into new stack\n" +
                "deal with increment 70\n" +
                "cut 3702\n" +
                "deal with increment 62\n" +
                "cut -6554\n" +
                "deal with increment 68\n" +
                "cut 1356\n" +
                "deal with increment 58\n" +
                "cut -9486\n" +
                "deal with increment 5\n" +
                "cut 3969\n" +
                "deal into new stack\n" +
                "deal with increment 9\n" +
                "cut 1376\n" +
                "deal with increment 70\n" +
                "cut 4921\n" +
                "deal with increment 38\n" +
                "deal into new stack\n" +
                "cut -4708\n" +
                "deal with increment 56\n" +
                "deal into new stack\n" +
                "cut 6672\n" +
                "deal with increment 53\n" +
                "cut -6567\n" +
                "deal with increment 28\n" +
                "cut -6494\n" +
                "deal with increment 57\n" +
                "deal into new stack\n" +
                "cut 3002\n" +
                "deal with increment 53\n" +
                "cut 5450\n" +
                "deal with increment 5\n" +
                "cut 7672\n" +
                "deal with increment 63\n" +
                "cut -9864\n" +
                "deal with increment 66\n" +
                "cut 5734\n" +
                "deal with increment 23\n" +
                "cut 9172\n" +
                "deal with increment 8\n" +
                "cut 3219\n" +
                "deal with increment 49\n" +
                "cut -975\n" +
                "deal with increment 52\n" +
                "deal into new stack\n" +
                "deal with increment 10\n" +
                "cut 6050\n" +
                "deal with increment 68\n" +
                "deal into new stack\n" +
                "cut -3778\n" +
                "deal with increment 25\n" +
                "cut 9259\n" +
                "deal with increment 41\n" +
                "cut -268\n" +
                "deal with increment 44\n" +
                "deal into new stack\n" +
                "cut -1431\n" +
                "deal with increment 48\n" +
                "cut -1885\n" +
                "deal with increment 75\n" +
                "cut 8570\n" +
                "deal with increment 49\n" +
                "deal into new stack\n" +
                "deal with increment 62\n" +
                "deal into new stack\n" +
                "deal with increment 35\n" +
                "deal into new stack\n" +
                "deal with increment 30\n" +
                "cut -3800\n" +
                "deal with increment 4\n" +
                "deal into new stack\n" +
                "deal with increment 27\n" +
                "cut 2827\n" +
                "deal with increment 2\n" +
                "cut -2346\n" +
                "deal with increment 19\n" +
                "cut 6615\n" +
                "deal with increment 38\n" +
                "cut 2739\n" +
                "deal into new stack";
    }
}
