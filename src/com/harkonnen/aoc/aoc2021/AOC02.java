package com.harkonnen.aoc.aoc2021;

import com.harkonnen.aoc.AOC;

public class AOC02 extends AOC {

    private Integer horizontalPosition;
    private Integer depthPosition;
    private Integer aim;

    @Override
    public void run() {

        System.out.println("Part 1: " + getResult(1));
        System.out.println("Part 2: " + getResult(2));
    }

    private Integer getResult(Integer part) {
        horizontalPosition = 0;
        depthPosition = 0;
        aim = 0;

        for (String input : inputs) {
            String[] tokens = input.split(" ");
            if (part == 1) {
                proceedPart1(tokens);
            }
            if (part == 2) {
                proceedPart2(tokens);
            }
        }

        return horizontalPosition * depthPosition;
    }

    private void proceedPart1(String[] tokens) {
        switch (tokens[0]) {
            case "forward":
                horizontalPosition += Integer.parseInt(tokens[1]);
                break;
            case "down":
                depthPosition += Integer.parseInt(tokens[1]);
                break;
            case "up":
                depthPosition -= Integer.parseInt(tokens[1]);
                break;
            default:
                break;
        }
    }

    private void proceedPart2(String[] tokens) {
        switch (tokens[0]) {
            case "forward":
                horizontalPosition += Integer.parseInt(tokens[1]);
                depthPosition += aim * Integer.parseInt(tokens[1]);
                break;
            case "down":
                aim += Integer.parseInt(tokens[1]);
                break;
            case "up":
                aim -= Integer.parseInt(tokens[1]);
                break;
            default:
                break;
        }
    }
}
