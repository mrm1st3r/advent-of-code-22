package day1

import Puzzle

fun main() {
    fun elfCalories(input: List<String>): MutableList<Int> {
        val calories = MutableList(input.size) { 0 }

        var currentElf = 0
        for (item in input) {
            if (item.isEmpty()) {
                currentElf++
            } else {
                calories[currentElf] = calories[currentElf] + item.toInt()
            }
        }
        return calories
    }

    fun part1(input: List<String>): Int {
        val calories = elfCalories(input)

        return calories.max()
    }

    fun part2(input: List<String>): Int {
        return elfCalories(input)
                .sorted()
                .reversed()
                .subList(0, 3)
                .sum()
    }

    Puzzle(
        "day1",
        ::part1,
        24000,
        ::part2,
        45000
    ).test()
}
