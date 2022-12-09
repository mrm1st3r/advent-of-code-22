package day4

import Puzzle


fun findSubRange(ranges: Pair<IntRange, IntRange>): Boolean {
    val left = ranges.first
    val right = ranges.second
    return (left.first >= right.first && left.last <= right.last)
            || (left.first <= right.first && left.last >= right.last)
}

fun convertToIntRanges(s: String): Pair<IntRange, IntRange> {
    val split = s.split(",")
    check(split.size == 2)
    return Pair(toIntRange(split[0]), toIntRange(split[1]))
}

fun toIntRange(s: String): IntRange {
    val split = s.split("-")
    check(split.size == 2)
    return IntRange(split[0].toInt(), split[1].toInt())
}

fun findOverlap(pair: Pair<IntRange, IntRange>): Boolean {
    val left = pair.first
    val right = pair.second

    return (left.first <= right.first && left.last >= right.first)
            || (right.first <= left.first && right.last >= left.first)
}

fun part1(input: List<String>): Int {
    return input
        .map(::convertToIntRanges)
        .filter(::findSubRange)
        .size
}

fun part2(input: List<String>): Int {
    return input
        .map(::convertToIntRanges)
        .filter(::findOverlap)
        .size
}

fun main() {
    Puzzle(
        "day4",
        ::part1,
        2,
        ::part2,
        4
    ).test()
}
