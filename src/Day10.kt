fun main() {
    fun match(left: String, right: String): Boolean {
        return when(left) {
            "(" -> right == ")"
            "[" -> right == "]"
            "{" -> right == "}"
            "<" -> right == ">"
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
        var errorSum = 0
        for (line in input) {
            val stack: MutableList<String> = mutableListOf()
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
        val completion: MutableList<String> = mutableListOf()
        val mutableStack: MutableList<String> = mutableListOf()
        mutableStack.addAll(stack)
        while (mutableStack.isNotEmpty()) {
            when (mutableStack.removeAt(mutableStack.size-1)) {
                "(" -> completion.add(")")
                "[" -> completion.add("]")
                "{" -> completion.add("}")
                "<" -> completion.add(">")
            }
        }
        return completion
    }
    fun part2(input: List<String>): Long {
        val scores: MutableList<Long> = mutableListOf()
        for (line in input) {
            var syntaxError = false
            val stack: MutableList<String> = mutableListOf()
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

