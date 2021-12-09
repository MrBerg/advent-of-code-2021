import java.nio.charset.Charset

fun main() {
    fun part1(input: List<String>): Int {
        val outputValues: List<List<String>> = input.map { it.split('|')[1].split(' ').filter { !(it.isBlank()) }}
        var occurrences: Int = 0
        outputValues.forEach {
            it.forEach {
                val segmentsActive: Int = it.length
                if (segmentsActive == 2 || segmentsActive == 3 || segmentsActive == 4 || segmentsActive == 7)
                    occurrences++
            }
        }
        return occurrences
    }
    fun createMapping(uniqueValues: List<String>): Map<String, String> {
        var mapping: MutableMap<String, String> = HashMap(10)
        var undetermined: MutableList<String> = ArrayList(6)
        for (value in uniqueValues) {
            when (value.length) {
                2 -> mapping.put(value, "1")
                3 -> mapping.put(value, "7")
                4 -> mapping.put(value, "4")
                7 -> mapping.put(value, "8")
                else -> undetermined.add(value)
            }
        }
        // We need to solve the 5-length ones first
        undetermined.sortBy { it.length }

        // Stupid way of doing reverse mapping for later use
        val one: Set<Char> = mapping.filterValues { it == "1" }.keys.take(1)[0].toSet()
        val four: Set<Char> = mapping.filterValues { it == "4" }.keys.take(1)[0].toSet()
        var five: Set<Char> = setOf()
        for (value in undetermined) {
            val valueSet = value.toSet()
            if (valueSet.size == 5) {
                // Possible: 2,3,5. 3 is the only that shares all of 1's segments and 5 shares 3 of 4's segments
                if ((valueSet.minus(one)).size == 3)
                    mapping.put(value, "3")
                else if ((valueSet.minus(four)).size == 2) {
                    five = valueSet
                    mapping.put(value, "5")
                } else
                    mapping.put(value, "2")
            } else if (valueSet.size == 6) {
                // Possible: 0,6,9. 6 is the only one that does not have all of 1's segments and 6,9 share all of 5's segments
                if ((valueSet.minus(five)).size == 1) {
                    if ((valueSet.minus(one)).size == 4)
                        mapping.put(value, "9")
                    else
                        mapping.put(value, "6")
                } else
                    mapping.put(value, "0")
            }
        }
        check(mapping.size == 10)
        return mapping
    }

    fun part2(input: List<String>): Int {
        // This time we also want to sort all the entry strings for later comparisons since they are not presorted
        val uniqueValues: List<List<String>> = input.map {
            it.split('|')[0].split(' ').filter { !(it.isBlank()) }.map { it.toCharArray().sorted().joinToString("")}}
        val outputValues: List<List<String>> = input.map {
            it.split('|')[1].split(' ').filter { !(it.isBlank()) }.map { it.toCharArray().sorted().joinToString("")}}
        check(outputValues.size == uniqueValues.size)
        var sum: Int = 0
        for (index in 0 until outputValues.size) {
            val currentMapping: Map<String, String> = createMapping(uniqueValues[index])
            check(outputValues[index].size == 4)
            val outputDigits: Int = outputValues[index].map { currentMapping.get(it) }.joinToString("").toInt()
            sum += outputDigits
        }
        return sum
    }

    val testInput = readInput("Day08_test")
    check(part1(testInput) == 26)
    check(part2(testInput) == 61229)


    val input = readInput("Day08")
    check(part1(input) == 239)
    //check(part2(input) == 903810)
    println(part1(input))
    println(part2(input))
}

