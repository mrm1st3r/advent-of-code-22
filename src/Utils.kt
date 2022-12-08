import java.io.File
import java.math.BigInteger
import java.security.MessageDigest

/**
 * Reads lines from the given input txt file.
 */
fun readInput(name: String) = File("src", "$name.txt")
    .readLines()

/**
 * Converts string to md5 hash.
 */
fun String.md5() = BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray()))
    .toString(16)
    .padStart(32, '0')

class Puzzle<T>(
    private val directory: String,
    private val part1: (List<String>) -> T,
    private val part1TestResult: T,
    private val part2: (List<String>) -> T,
    private val part2TestResult: T,
    ) {

    fun test() {
        val testInput = readInput("$directory/test")
        val puzzleInput = readInput("$directory/puzzle")

        println(directory)
        println()

        println("part 1")

        val part1test = part1(testInput)
        check(part1test == part1TestResult) { "Expect $part1test to be $part1TestResult" }

        println(part1(puzzleInput))

        println()

        println("part 2")

        val part2test = part2(testInput)
        check(part2test == part2TestResult) { "Expect $part2test to be $part2TestResult" }

        println(part2(puzzleInput))
    }
}
