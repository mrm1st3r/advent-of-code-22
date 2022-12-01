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

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test")
    println("Test file")
    val part1 = part1(testInput)
    println(part1)
    check(part1 == 24000)
    val part2 = part2(testInput)
    println(part2)
    check(part2 == 45000)

    val input = readInput("Day01")
    println("Real file")
    println(part1(input))
    println(part2(input))
}
