fun main() {
    fun part1(input: List<String>): Int {
        val gamma: UInt?
        val epsilon: UInt?
        val lineLength = input[0].length
        var zeros = Array(lineLength) { _ -> 0 }
        var ones = Array(lineLength) { _ -> 0 }
        for (line in input) {
            for (index in 0 until lineLength) {
                when (line[index]) {
                    '0' -> zeros[index] += 1
                    '1' -> ones[index] += 1
                }
            }
        }
        var gammaBuilder = ""
        var epsilonBuilder = ""

        for (indx in 0 until lineLength) {
            if (zeros[indx] > ones[indx]) {
                gammaBuilder += '0'
                epsilonBuilder += '1'
            } else {
                gammaBuilder += '1'
                epsilonBuilder += '0'
            }
        }
        try {
            gamma = gammaBuilder.toUInt(2)
            epsilon = epsilonBuilder.toUInt(2)
        } catch (nfe: NumberFormatException) {
            println(nfe)
            return 0
        }
        return (gamma * epsilon).toInt()
    }

    fun part2(input: List<String>): Int {
        var oxygenList = input
        var scrubberList = input

        val lineLength = input[0].length
        for (ind in 0 until lineLength) {
            var oxygenOnes: Int = 0
            var oxygenZeros: Int = 0
            oxygenList.forEach {
                when(it[ind]) {
                    '1' -> oxygenOnes++
                    '0' -> oxygenZeros++
                }
            }
            if (oxygenOnes >= oxygenZeros) {
                oxygenList = oxygenList.filter{ it: String ->it[ind] == '1' }
            } else {
                oxygenList = oxygenList.filter{ it: String -> it[ind] == '0'}
            }
        }
        for (ind in 0 until lineLength) {
            var scrubberOnes: Int = 0
            var scrubberZeros: Int = 0
            scrubberList.forEach {
                when(it[ind]) {
                    '1' -> scrubberOnes++
                    '0' -> scrubberZeros++
                }
            }
            if (scrubberOnes < scrubberZeros) {
                scrubberList = scrubberList.filter{ it: String ->it[ind] == '1' }
            } else {
                scrubberList = scrubberList.filter{ it: String ->it[ind] == '0' }
            }
            if (scrubberList.size == 1)
                break
        }
        check(oxygenList.size == 1)
        check(scrubberList.size == 1)
        return (oxygenList[0].toUInt(2) * scrubberList[0].toUInt(2)).toInt()
    }

    val testInput = readInput("Day03_test")
    check(part1(testInput) == 198)
    check(part2(testInput) == 230)


    val input = readInput("Day03")
    check(part1(input) == 741950)
    check(part2(input) == 903810)
    println(part1(input))
    println(part2(input))
}

