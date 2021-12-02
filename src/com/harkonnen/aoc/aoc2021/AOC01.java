package com.harkonnen.aoc.aoc2021;

import com.harkonnen.aoc.AOC;

public class AOC01 extends AOC {

    @Override
    public void run() {
        System.out.println("Part 1: " + getResult(1));
        System.out.println("Part 2: " + getResult(3));
    }

    private Integer getResult(Integer offset) {
        Integer result = 0;

        for (int i = 0; i < inputs.size() - offset; i++) {
            Integer currentSum = calcSum(i, offset);
            Integer nextSum = calcSum(i + 1, offset);
            if (hasIncreased(currentSum, nextSum)) {
                result++;
            }
        }
        return result;
    }

    private Integer calcSum(Integer index, Integer offset) {
        return inputs
                .subList(index, index + offset)
                .stream()
                .mapToInt(Integer::valueOf)
                .sum();
    }

    private boolean hasIncreased(Integer current, Integer next) {
        return next > current;
    }
}
