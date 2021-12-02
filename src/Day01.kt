fun main() {
    fun part1(input: List<String>): Int {
        var lastVal = input[0].toInt()
        var returnVal = 0
        for (num in input.subList(1, input.size)) {
            if (num.toInt() > lastVal) {
                returnVal++
            }
            lastVal = num.toInt()
        }
        return returnVal
    }

    fun part2(input: List<String>): Int {
        var triplet = input[0].toInt() + input[1].toInt() + input[2].toInt()
        var increases = 0
        for (idx in 3 until input.size) {
            val newTriplet = triplet + input[idx].toInt() - input[idx-3].toInt()
            if (newTriplet > triplet) {
                increases++
            }
            triplet = newTriplet
        }
        return increases
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test")
    check(testInput[testInput.size-1] == "177")
    check(part1(testInput) == 2)

    val testInput2 = readInput("Day01_test2")
    check(part1(testInput2) == 7)
    check(part2(testInput2) == 5)
    val input = readInput("Day01")
    println(part1(input))
    println(part2(input))
}
