fun main() {
    fun part1(input: List<String>, days: Int): Int {
        var fish: MutableList<Int> = input[0].split(',').map { it.toInt() }.toMutableList()
        for (day in 1..days) {
            val spawningFish = fish.filter { it == 0 }
            // Give the spawn a 9, immediately decrease to 8 for simpler code
            spawningFish.forEach { fish.add(9)}
            fish = fish.map {
                if (it == 0)
                    6
                else
                    it-1
            }.toMutableList()
        }
        return fish.size
    }

    fun part2(input: List<String>, days: Int): Long {
        // Make a map  from internal timer to corresponding amount of fish
        var fish: MutableMap<Int, Long> = input[0].split(',')
            .map { it.toLong() }
            .groupBy { it.toInt() }
            .mapValues { e: Map.Entry<Int, List<Long>> -> e.value.size.toLong() }
            .toMutableMap()
        for (day in 1..days) {
            val spawningFish: Long = fish.getOrDefault(0,0L)
            // Create new spawn
            fish.put(9, spawningFish)
            // Then decrease everyone's timer
            val intermediate: MutableMap<Int, Long> = fish.mapKeys { e: Map.Entry<Int, Long> -> e.key-1 }.toMutableMap()
            // Then move the fish that had reached a 0 to 6
            val restartingFish: Long =  intermediate.getOrDefault(-1,0)
            intermediate.put(6, restartingFish + intermediate.getOrDefault(6,0L))
            intermediate.remove(-1)
            fish = intermediate
        }
        return fish.values.sum()
    }

    val testInput = readInput("Day06_test")
    check(part1(testInput, 18) == 26)
    check(part2(testInput, 18) == 26L)
    check(part2(testInput, 256) == 26984457539L)


    val input = readInput("Day06")
    check(part1(input, 80) == 345387)
    check(part2(input, 80) == 345387L)
    check(part2(input, 256) == 1574445493136L)

    println(part1(input, 80))
    println(part2(input, 256))
}

