import kotlin.system.measureTimeMillis

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


fun main() {

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

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day02_test")
    val input = readInput("Day02")
    val testResultPart1 = 15
    val testResultPart2 = 12

    println("## Part 1")

    print("Test File: ")
    val part1 = part1(testInput)
    println(part1)
    check(part1 == testResultPart1)

    print("Real File: ")
    val millis = measureTimeMillis {
        println(part1(input))
    }
    println("took" + millis)

    println()

    println("## Part 2")

    print("Test File: ")
    val part2 = part2(testInput)
    println(part2)
    check(part2 == testResultPart2)

    print("Real File: ")
    println(part2(input))
}
