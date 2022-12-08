package day2

import Puzzle

enum class Shape(val score: Int) {
    ROCK(1),
    PAPER(2),
    SCISSORS(3)
}

val elfCodes = mapOf(
        Pair('A', Shape.ROCK),
        Pair('B', Shape.PAPER),
        Pair('C', Shape.SCISSORS)
)

val humanCodes = mapOf(
        Pair('X', Shape.ROCK),
        Pair('Y', Shape.PAPER),
        Pair('Z', Shape.SCISSORS)
)

// second wins against first
val winningCombinations = listOf(
        Pair(Shape.ROCK, Shape.PAPER),
        Pair(Shape.SCISSORS, Shape.ROCK),
        Pair(Shape.PAPER, Shape.SCISSORS),
)

fun winAgainst(v: Shape): Shape {
    return winningCombinations.find { it.first == v }!!.second
}

fun loseAgainst(v: Shape): Shape {
    return winningCombinations.find { it.second == v }!!.first
}

fun parseShapes(line: String): Pair<Shape, Shape> {
    return Pair(elfCodes[line[0]]!!, humanCodes[line[2]]!!)
}

fun parseShapesPart2(line: String): Pair<Shape, Shape> {
    val elf = elfCodes[line[0]]!!
    val human = when (line[2]) {
        'X' -> loseAgainst(elf)
        'Z' -> winAgainst(elf)
        else -> elf
    }
    return Pair(elf, human)
}

fun score(shapes: Pair<Shape, Shape>): Int {
    return when {
        shapes.first == shapes.second -> 3
        winningCombinations.contains(shapes) -> 6
        else -> 0
    } + shapes.second.score
}

fun main() {

    fun part1(input: List<String>): Int {
        return input
                .map(::parseShapes)
                .sumOf(::score)
    }

    fun part2(input: List<String>): Int {
        return input
                .map(::parseShapesPart2)
                .sumOf(::score)
    }

    Puzzle(
        "day2",
        ::part1,
        15,
        ::part2,
        12
    ).test()
}
