package day3

import Puzzle

fun splitCompartments(s: String): Pair<String, String> {
    val middle = s.length / 2
    return Pair(s.substring(0, middle), s.substring(middle, s.length))
}

fun findOverlap(compartments: Pair<String, String>): String {
    return compartments.first.filter {
       compartments.second.contains(it)
    }
}

fun prioritize(item: Char): Int {
    return when {
        item.isLowerCase() -> item.code - 96
        else -> item.code - 38
    }
}

fun findBadge(elfs: List<String>): Char {
    check(elfs.size == 3)
    val a = findOverlap(Pair(elfs[0], elfs[1]))
    return findOverlap(Pair(a, elfs[2])).first()
}

fun main() {

    fun part1(input: List<String>): Int {
        return input
            .asSequence()
            .map(::splitCompartments)
            .map(::findOverlap)
            .map{it.first()}
            .map(::prioritize)
            .sum()
    }

    fun part2(input: List<String>): Int {
        return input
            .windowed(3, 3)
            .map(::findBadge)
            .map(::prioritize)
            .sum()
    }

    Puzzle(
        "day3",
        ::part1,
        157,
        ::part2,
        70
    ).test()
}
