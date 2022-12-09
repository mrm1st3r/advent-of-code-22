package day5

import Puzzle


fun part1(input: List<String>): String {
    val separator = input.indexOf("")
    check(separator > 0)
    val stacks = parseStacks(input.subList(0, separator))

    input.subList(separator + 1, input.size)
        .map(::parseOperation)
        .forEach { executeOperation1(it, stacks) }
    return String(stacks
        .map { it.value.last() }
        .toCharArray())
}

fun part2(input: List<String>): String {
    val separator = input.indexOf("")
    check(separator > 0)
    val stacks = parseStacks(input.subList(0, separator))

    input.subList(separator + 1, input.size)
        .map(::parseOperation)
        .forEach { executeOperation2(it, stacks) }
    return String(stacks
        .map { it.value.last() }
        .toCharArray())
}

fun executeOperation1(op: Operation, stacks: Map<Int, ArrayDeque<Char>>) {
    for (i in 1..op.amount) {
        val crate = stacks[op.from]!!.removeLast()
        stacks[op.to]!!.addLast(crate)
    }
}

fun executeOperation2(op: Operation, stacks: Map<Int, ArrayDeque<Char>>) {
    val crane = ArrayDeque<Char>()
    for (i in 1..op.amount) {
        val crate = stacks[op.from]!!.removeLast()
        crane.addLast(crate)
    }
    for (i in 1..op.amount) {
        val crate = crane.removeLast()
        stacks[op.to]!!.addLast(crate)
    }
}

data class Operation(
    val from: Int,
    val to: Int,
    val amount: Int
)

fun parseOperation(input: String): Operation {
    val result = Regex("""move (\d+) from (\d) to (\d)""").matchEntire(input)
    checkNotNull(result)
    check(result.groupValues.size == 4)
    return Operation(
        from = result.groupValues[2].toInt(),
        to = result.groupValues[3].toInt(),
        amount = result.groupValues[1].toInt(),
    )
}

fun parseStacks(input: List<String>): Map<Int, ArrayDeque<Char>> {
    val stacks = input.last()
        .filter { it.isDigit() }
        .map { it.digitToInt() }
        .associateWith<Int, ArrayDeque<Char>> { ArrayDeque() }

    input.subList(0, input.size - 1).forEach { fillStacks(stacks, it) }

    return stacks
}

fun fillStacks(stacks: Map<Int, ArrayDeque<Char>>, input: String) {
    for (i in 1..stacks.size) {
        val position = (i - 1) * 4 + 1
        if (position <= input.length && input[position].isLetter()) {
            stacks[i]!!.addFirst(input[position])
        }
    }
}

fun main() {
    Puzzle(
        "day5",
        ::part1,
        "CMZ",
        ::part2,
        "MCD"
    ).test()
}
