package com.harkonnen.aoc.aoc2021;

import java.util.List;
import java.util.stream.Collectors;

import com.harkonnen.aoc.AOC;

public class AOC03 extends AOC {

    private int bit0Count[];
    private int bit1Count[];
    private final int NUMBER_OF_BITS = 12;
    
    @Override
    public void run() {
        System.out.println("Part 1: " + getResult(1));
        System.out.println("Part 2: " + getResult(2));
    }

    private Integer getResult(Integer part) {

        int result = 0;

        if (part == 1) {
            
            Integer gamma = 0;
            Integer epsilon = 0;
            
            countBits(inputs);

            for (int i = NUMBER_OF_BITS-1; i >= 0; i--) {
                if (bit1Count[i] > bit0Count[i]) {
                    gamma++;
                } else {
                    epsilon++;
                }
                gamma <<= 1;
                epsilon <<= 1;
            }
            
            result = (gamma * epsilon) >> 2;
        }
        if (part == 2) {

            List<String> subListOxygen = getOxygen(inputs, NUMBER_OF_BITS-1);
            List<String> subListCO2 = getCO2(inputs, NUMBER_OF_BITS-1);

            result = Integer.parseInt(subListOxygen.get(0), 2) * Integer.parseInt(subListCO2.get(0), 2);
        }

        return result;
    }

    private void countBits(List<String> inputs) {
        bit0Count = new int[NUMBER_OF_BITS];
        bit1Count = new int[NUMBER_OF_BITS];

        for (String input : inputs) {
            for (int i = 0; i <= NUMBER_OF_BITS-1; i++) {
                int bit = (Integer.parseInt(input, 2) & (1 << i)) >> i;
                if (bit == 0) bit0Count[i]++;
                if (bit == 1) bit1Count[i]++;
            }
        }
    }

    private List<String> getOxygen(List<String> inputs, int bitNumber) {
        List<String> subList = null;

        if (inputs.size() == 1 || bitNumber < 0) {
            return inputs;
        }

        countBits(inputs);

        if (bit0Count[bitNumber] > bit1Count[bitNumber]) {
            subList = getSubList(inputs, 0, bitNumber);
        } else if (bit1Count[bitNumber] > bit0Count[bitNumber]) {
            subList = getSubList(inputs, 1, bitNumber);
        } else {
            subList = getSubList(inputs, 1, bitNumber);
        }

        bitNumber--;
        
        return getOxygen(subList, bitNumber);
    }

    private List<String> getCO2(List<String> inputs, int bitNumber) {
        List<String> subList = null;

        if (inputs.size() == 1 || bitNumber < 0) {
            return inputs;
        }

        countBits(inputs);

        if (bit0Count[bitNumber] < bit1Count[bitNumber]) {
            subList = getSubList(inputs, 0, bitNumber);
        } else if (bit1Count[bitNumber] < bit0Count[bitNumber]) {
            subList = getSubList(inputs, 1, bitNumber);
        } else {
            subList = getSubList(inputs, 0, bitNumber);
        }

        bitNumber--;

        return getCO2(subList, bitNumber);
    }

    private List<String> getSubList(List<String> inputs, int filterBit, final int bitNumber) {

        return inputs
                .stream()
                .filter(input -> {
                    int bit = (Integer.parseInt(input, 2) & (1 << bitNumber)) >> bitNumber;
                    return bit == filterBit;
                })
                .collect(Collectors.toList());

    }
    
}
