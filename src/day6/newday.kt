package day6

import Puzzle


fun part1(input: List<String>): Int {
    return input[0].withIndex()
        .windowed(4)
        .first(::allLettersDiffer)
        .last().index + 1
}

fun allLettersDiffer(letters: List<IndexedValue<Char>>): Boolean {
    val charSet = letters
        .map { it.value }
        .toSet()
    return (charSet.size == letters.size)
}

fun part2(input: List<String>): Int {
    return input[0].withIndex()
        .windowed(14)
        .first(::allLettersDiffer)
        .last().index + 1
}

fun main() {
    Puzzle(
        "day6",
        ::part1,
        7,
        ::part2,
        19
    ).test()
}
