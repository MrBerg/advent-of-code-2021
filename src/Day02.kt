fun main() {
    fun part1(input: List<String>): Int {
        var pos = 0
        var depth = 0
        for (line in input) {
            val splitLine = line.split(' ')
            val (direction, size) = Pair(splitLine[0], splitLine[1])
            when (direction) {
                "forward" -> pos += size.toInt()
                "up" -> depth -= size.toInt()
                "down" -> depth += size.toInt()
            }
        }
        return pos * depth
    }

    fun part2(input: List<String>): Int {
        var pos = 0
        var depth = 0
        var aim = 0
        for (line in input) {
            val splitLine = line.split(' ')
            val (direction, size) = Pair(splitLine[0],splitLine[1])
            when (direction) {
                "forward" -> { pos += size.toInt()
                    depth += aim * size.toInt() }
                "up" -> aim -= size.toInt()
                "down" -> aim += size.toInt()
            }
        }
        return pos * depth
    }

    val testInput = readInput("Day02_test")
    check(part1(testInput) == 150)
    check(part2(testInput) == 900)


    val input = readInput("Day02")
    check(part1(input) == 1690020)
    check(part2(input) == 1408487760)
    println(part1(input))
    println(part2(input))
}

