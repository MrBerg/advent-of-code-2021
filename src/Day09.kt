fun main() {
    fun part1(input: List<String>): Int {
        val heightmap: List<List<Int>> = input.map { it.split("").filter { !(it.isBlank()) }.map { it.toInt() }}
        val xTotal = heightmap[0].size
        val yTotal = heightmap.size
        var risk: Int = 0
        for (y in 0 until yTotal) {
            for (x in 0 until xTotal) {
                val current: Int = heightmap[y][x]
                val up: Int = heightmap.elementAtOrElse(y-1, { listOf(0) }).elementAtOrElse(x,{ 10 })
                val down: Int = heightmap.elementAtOrElse(y+1, { listOf(0) }).elementAtOrElse(x, { 10 })
                val left: Int = heightmap.elementAtOrElse(y, { listOf(0) }).elementAtOrElse(x-1, { 10 })
                val right: Int = heightmap.elementAtOrElse(y, { listOf(0) }).elementAtOrElse(x+1, { 10 })
                if (current < up && current < down && current < left && current < right)
                    risk += current + 1
            }
        }
        return risk
    }
    fun neighbors(center: Pair<Int, Int>, size: Pair<Int, Int>): Set<Pair<Int, Int>> {
        var neighbors: MutableSet<Pair<Int, Int>> = mutableSetOf<Pair<Int,Int>>()
        if (center.first-1 >= 0)
            neighbors.add(Pair(center.first-1, center.second))
        if (center.first+1 < size.first)
            neighbors.add(Pair(center.first+1, center.second))
        if (center.second-1 >= 0)
            neighbors.add(Pair(center.first, center.second-1))
        if (center.second+1 < size.second)
            neighbors.add(Pair(center.first, center.second+1))
        return neighbors
    }

    fun part2(input: List<String>): Int {
        val heightmap: List<List<Int>> = input.map { it.split("").filter { !(it.isBlank()) }.map { it.toInt() }}
        val xTotal = heightmap[0].size
        val yTotal = heightmap.size
        val size: Pair<Int, Int> = Pair(yTotal, xTotal)
        var lowPoints: MutableSet<Pair<Int,Int>> = mutableSetOf<Pair<Int, Int>>()
        // Just for checking the solution
        var highPoints: Int = 0

        var basinSizes: MutableList<Int> = ArrayList<Int>()
        for (y in 0 until yTotal) {
            for (x in 0 until xTotal) {
                val current: Int = heightmap[y][x]
                if (current == 9)
                    highPoints++
                val up: Int = heightmap.elementAtOrElse(y-1, { listOf(0) }).elementAtOrElse(x,{ 10 })
                val down: Int = heightmap.elementAtOrElse(y+1, { listOf(0) }).elementAtOrElse(x, { 10 })
                val left: Int = heightmap.elementAtOrElse(y, { listOf(0) }).elementAtOrElse(x-1, { 10 })
                val right: Int = heightmap.elementAtOrElse(y, { listOf(0)}).elementAtOrElse(x+1, { 10 })
                if (current < up && current < down && current < left && current < right)
                    lowPoints.add(Pair(y,x))
            }
        }
        for (point in lowPoints) {
            var basin: MutableSet<Pair<Int, Int>> = mutableSetOf<Pair<Int, Int>>(point)
            var yetToCheck: MutableSet<Pair<Int, Int>> = neighbors(point, size).toMutableSet()
            while(!yetToCheck.isEmpty()) {
                // skirt the concurrentmodificationexception
                val yetToCheckCopy: Set<Pair<Int,Int>> = yetToCheck.toSet()
                for (currentPoint in yetToCheckCopy) {
                    val currentHeight: Int = heightmap[currentPoint.first][currentPoint.second]
                    // Points of height 9 can't be a part of a basin
                    if (currentHeight < 9) {
                        basin.add(currentPoint)
                        // Remove all the already checked points before adding them
                        val newPoints = neighbors(currentPoint, size).minus(basin)
                        yetToCheck.addAll(newPoints)
                    }
                    yetToCheck.remove(currentPoint)
                }
            }
            basinSizes.add(basin.size)
        }
        check(size.first*size.second == basinSizes.sum()+highPoints)
        basinSizes.sortByDescending { it }
        return basinSizes.take(3).fold(1, { acc: Int, next: Int -> acc * next})
    }

    val testInput = readInput("Day09_test")
    check(part1(testInput) == 15)
    check(part2(testInput) == 1134)


    val input = readInput("Day09")
    check(part1(input) == 532)
    check(part2(input) == 1110780)
    println(part1(input))
    println(part2(input))
}

