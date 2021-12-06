import java.util.Collections

fun main() {
    fun part1(input: List<String>): Int {
        // Use the fact that all coordinates are < 1000 in our input even though it's not mentioned
        var field: MutableList<MutableList<Int>> = ArrayList(1000)
        for (row in 0 until 1000)
            field.add(Collections.nCopies(1000,0).toMutableList())
        val regex = Regex("(\\d+),(\\d+) -> (\\d+),(\\d+)")
        for (row in input) {
            val result = regex.matchEntire(row)
            if (result == null)
                break
            val groupList = result!!.groupValues.drop(1)
            check(groupList.size == 4)
            val x1: Int = groupList[0].toInt()
            val y1: Int = groupList[1].toInt()
            val x2: Int = groupList[2].toInt()
            val y2: Int = groupList[3].toInt()
            if (x1 == x2) {
                for (y in Math.min(y1,y2)..Math.max(y1,y2)) {
                    field[x1][y]++
                }
            } else if (y1 == y2) {
                for (x in Math.min(x1,x2)..Math.max(x1,x2)) {
                    field[x][y1]++
                }
            } else {
                // Diagonal lines, just skip for now
            }
        }
        var result: Int = 0
        field.forEach { row -> row.filter { it >= 2 }.forEach { result++ }}
        return result
    }

    // The only difference in this part is the else clause, could refactor to have a method return all points on a line
    // and a flag for diagonals
    fun part2(input: List<String>): Int {
        // Use the fact that all coordinates are < 1000 in our input even though it's not mentioned
        var field: MutableList<MutableList<Int>> = ArrayList(1000)
        for (row in 0 until 1000)
            field.add(Collections.nCopies(1000,0).toMutableList())
        val regex = Regex("(\\d+),(\\d+) -> (\\d+),(\\d+)")
        for (row in input) {
            val result = regex.matchEntire(row)
            if (result == null)
                break
            val groupList = result!!.groupValues.drop(1)
            check(groupList.size == 4)
            val x1: Int = groupList[0].toInt()
            val y1: Int = groupList[1].toInt()
            val x2: Int = groupList[2].toInt()
            val y2: Int = groupList[3].toInt()
            if (x1 == x2) {
                for (y in Math.min(y1,y2)..Math.max(y1,y2)) {
                    field[x1][y]++
                }
            } else if (y1 == y2) {
                for (x in Math.min(x1,x2)..Math.max(x1,x2)) {
                    field[x][y1]++
                }
            } else {
                val slope: Int
                if ((x2-x1)*(y2-y1) > 0) {
                    slope = 1
                } else {
                    slope = -1
                }
                val constant: Int = y1-slope*x1
                for (x in Math.min(x1,x2)..Math.max(x1,x2)) {
                    field[x][slope*x+constant]++
                }
            }
        }
        var result: Int = 0
        field.forEach { row -> row.filter { it >= 2 }.forEach { result++ }}
        return result
    }

    val testInput = readInput("Day05_test")
    //println(part1(testInput))
    check(part1(testInput) == 5)
    //println(part2(testInput))
    check(part2(testInput) == 12)


    val input = readInput("Day05")
    check(part1(input) == 6856)
    check(part2(input) == 20666)
    println(part1(input))
    println(part2(input))
}

