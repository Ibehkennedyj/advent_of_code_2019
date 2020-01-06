package com.advent_of_code;

import com.advent_of_code.interfaces.Day;
import com.advent_of_code.interfaces.IntInputs;

public class Day1__TheTyrannyOfTheRocketEquation implements Day, IntInputs {

    int fuelPerMass(int mass) {
        return mass / 3 - 2;
    }

    int fuelPerModule(int module_mass) {
        int answer = 0;
        while (fuelPerMass(module_mass) > 0)
            answer += module_mass = fuelPerMass(module_mass);
        return answer;
    }

    public int partOne() {
        return getIntInputsStream("\n")
                .map(this::fuelPerMass)
                .sum();
    }

    public int partTwo() {
        return getIntInputsStream("\n")
                .map(this::fuelPerModule)
                .sum();
    }

    public static void main(String[] args) {
        new Day1__TheTyrannyOfTheRocketEquation().print_answers();
    }

    @Override
    public String getPuzzleInput() {
        return "147308\n" +
                "51605\n" +
                "71317\n" +
                "110882\n" +
                "92545\n" +
                "126856\n" +
                "104937\n" +
                "92433\n" +
                "107850\n" +
                "119538\n" +
                "82733\n" +
                "52216\n" +
                "105704\n" +
                "123682\n" +
                "105919\n" +
                "136265\n" +
                "100540\n" +
                "84245\n" +
                "72006\n" +
                "111652\n" +
                "85116\n" +
                "85841\n" +
                "71374\n" +
                "144196\n" +
                "125493\n" +
                "113529\n" +
                "64637\n" +
                "87489\n" +
                "138161\n" +
                "120897\n" +
                "53384\n" +
                "83310\n" +
                "126144\n" +
                "120672\n" +
                "107681\n" +
                "101369\n" +
                "77469\n" +
                "141056\n" +
                "140426\n" +
                "114920\n" +
                "124454\n" +
                "130867\n" +
                "64611\n" +
                "104813\n" +
                "138808\n" +
                "114234\n" +
                "148654\n" +
                "59031\n" +
                "91367\n" +
                "83316\n" +
                "106192\n" +
                "127495\n" +
                "139980\n" +
                "119024\n" +
                "149567\n" +
                "57007\n" +
                "61075\n" +
                "65637\n" +
                "75293\n" +
                "61670\n" +
                "104044\n" +
                "77230\n" +
                "80201\n" +
                "137094\n" +
                "99733\n" +
                "50801\n" +
                "68922\n" +
                "148404\n" +
                "79980\n" +
                "62716\n" +
                "67666\n" +
                "72694\n" +
                "81951\n" +
                "108427\n" +
                "111721\n" +
                "55532\n" +
                "94442\n" +
                "88562\n" +
                "101088\n" +
                "111656\n" +
                "111649\n" +
                "92085\n" +
                "91730\n" +
                "114744\n" +
                "59355\n" +
                "55842\n" +
                "100926\n" +
                "146370\n" +
                "147829\n" +
                "62160\n" +
                "91447\n" +
                "115745\n" +
                "141815\n" +
                "106837\n" +
                "68151\n" +
                "89077\n" +
                "60357\n" +
                "89856\n" +
                "75040\n" +
                "139131";
    }

}
