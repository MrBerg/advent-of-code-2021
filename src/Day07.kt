fun main() {
    fun part1(input: List<String>): Int {
        val positions: List<Int> = input[0].split(',').map { it.toInt() }
        // Assume the optimal position is within the current spread
        val min = positions.minOf { it }
        val max = positions.maxOf { it }
        var minFuelcost: Int = Int.MAX_VALUE
        for (pos in min..max) {
            val fuelCost = positions.map { Math.abs(it - pos) }.sum()
            if (fuelCost < minFuelcost)
                minFuelcost = fuelCost
        }
        return minFuelcost
    }

    fun part2(input: List<String>): Int {
        val positions: List<Int> = input[0].split(',').map { it.toInt() }
        // Assume the optimal position is within the current spread
        val min = positions.minOf { it }
        val max = positions.maxOf { it }
        var minFuelcost: Int = Int.MAX_VALUE
        for (pos in min..max) {
            val fuelCost = positions.map { (1 .. Math.abs(it - pos)).sum() }.sum()
            if (fuelCost < minFuelcost)
                minFuelcost = fuelCost
        }
        return minFuelcost
    }

    val testInput = readInput("Day07_test")
    check(part1(testInput) == 37)
    check(part2(testInput) == 168)


    val input = readInput("Day07")
    check(part1(input) == 325528)
    check(part2(input) == 85015836)
    println(part1(input))
    println(part2(input))
}

