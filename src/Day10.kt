fun main() {
    fun match(left: String, right: String): Boolean {
        return when(left) {
            "(" -> right.equals(")")
            "[" -> right.equals("]")
            "{" -> right.equals("}")
            "<" -> right.equals(">")
            else -> false
        }
    }
    fun errorScore(cha: String): Int {
        return when(cha) {
            ")" -> 3
            "]" -> 57
            "}" -> 1197
            ">" -> 25137
            else -> 0
        }
    }

    fun part1(input: List<String>): Int {
        var errorSum: Int = 0
        for (line in input) {
            var stack: MutableList<String> = mutableListOf()
            val splitLine: List<String> = line.split("").filter{ !it.isBlank() }
            for (cha in splitLine) {
                if ("([{<".contains(cha)) {
                    stack.add(cha)
                } else {
                    val last = stack.removeAt(stack.size-1)
                    if (!match(last, cha)) {
                        errorSum += errorScore(cha)
                        break
                    }
                }
            }
        }
        return errorSum
    }
    fun autoCompleteScore(cha: String): Int {
        return when(cha) {
            ")" -> 1
            "]" -> 2
            "}" -> 3
            ">" -> 4
            else -> 0
        }
    }
    fun completeRow(stack: List<String>): List<String> {
        var completion: MutableList<String> = mutableListOf()
        var mutableStack: MutableList<String> = mutableListOf()
        mutableStack.addAll(stack)
        while (!mutableStack.isEmpty()) {
            val toMatch = mutableStack.removeAt(mutableStack.size-1)
            when (toMatch) {
                "(" -> completion.add(")")
                "[" -> completion.add("]")
                "{" -> completion.add("}")
                "<" -> completion.add(">")
            }
        }
        return completion
    }
    fun part2(input: List<String>): Long {
        var scores: MutableList<Long> = mutableListOf()
        for (line in input) {
            var syntaxError = false
            var stack: MutableList<String> = mutableListOf()
            val splitLine: List<String> = line.split("").filter{ !it.isBlank() }
            for (cha in splitLine) {
                if ("([{<".contains(cha)) {
                    stack.add(cha)
                } else {
                    val last = stack.removeAt(stack.size-1)
                    if (!match(last, cha)) {
                        syntaxError = true
                        break
                    }
                }
            }
            if (syntaxError)
                continue
            val completion: List<String> = completeRow(stack)
            var score: Long = 0
            for (cha in completion)
                score = score * 5 + autoCompleteScore(cha)
            scores.add(score)
        }
        val sortedScores: List<Long> = scores.sorted()
        return sortedScores[scores.size/2]
    }

    val testInput = readInput("Day10_test")
    check(part1(testInput) == 26397)
    check(part2(testInput) == 288957L)


    val input = readInput("Day10")
    check(part1(input) == 278475)
    check(part2(input) == 3015539998)
    println(part1(input))
    println(part2(input))
}

