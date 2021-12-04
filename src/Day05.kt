fun main() {
    fun part1(input: List<String>): Int {
        return input.size
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

    val testInput = readInput("Day05_test")
    check(part1(testInput) == 198)
    check(part2(testInput) == 230)


    val input = readInput("Day05")
    check(part1(input) == 741950)
    check(part2(input) == 903810)
    println(part1(input))
    println(part2(input))
}

