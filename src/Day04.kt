fun main() {
    fun createBoard(input: List<String>): List<List<Int>> {
        var returnVal: MutableList<List<Int>> = ArrayList(5)
        val whitespaces: Regex = Regex("\\s\\s?")
        for (row in input) {
            val parsedRow: List<Int> = row.split(whitespaces).filter { !it.isBlank() }.map { it.toInt() }
            check(parsedRow.size == 5)
            returnVal.add(parsedRow)
        }
        check(returnVal.size == 5)
        return returnVal
    }

    fun checkForWin(board: List<List<Int>>, drawnSoFar: List<Int>): Boolean {
        for (row in board) {
            if (drawnSoFar.containsAll(row))
                return true
        }
        // TODO: only transpose each board once somewhere earlier
        val columns: List<List<Int>> = board.mapIndexed { ind: Int, el: List<Int> -> el.mapIndexed { dex: Int, _: Int -> board[dex][ind] }}
        for (col in columns) {
            if (drawnSoFar.containsAll(col))
                return true
        }
        return false
    }

    fun calculatePoints(board: List<List<Int>>, drawnSoFar: List<Int>): Int {
        val numberCalled: Int = drawnSoFar[drawnSoFar.size-1]
        var pointTotal = 0
        for (row in board) {
            pointTotal += row.subtract(drawnSoFar).sum()
        }
        return pointTotal * numberCalled
    }

    fun part1(input: List<String>): Int {
        val drawing: List<Int> = input[0].split(',').map { it.toInt() }
        // Just treat the empty line before each board as part of it in this calculation
        val boardAmount: Int = (input.size-1) / 6
        var boards: MutableList<List<List<Int>>> = ArrayList(boardAmount)

        for (i in 0 until boardAmount) {
            val firstIdx: Int = 2+i*6
            boards.add(createBoard(input.subList(firstIdx, firstIdx+5)))
        }
        for (num in 6 .. drawing.size) {
            for (board in boards) {
                if (checkForWin(board, drawing.subList(0,num))) {
                    return calculatePoints(board, drawing.subList(0,num))
                }
            }
        }
        // We should never come this far
        return -1
    }

    fun part2(input: List<String>): Int {
        val drawing: List<Int> = input[0].split(',').map { it.toInt() }
        val boardAmount: Int = (input.size-1) / 6
        var boards: MutableList<List<List<Int>>> = ArrayList(boardAmount)
        var wonBoards: MutableList<List<List<Int>>> = ArrayList(boardAmount)

        for (i in 0 until boardAmount) {
            val firstIdx: Int = 2+i*6
            boards.add(createBoard(input.subList(firstIdx, firstIdx+5)))
        }
        for (num in 6 .. drawing.size) {
            for (board in boards.subtract(wonBoards)) {
                if (checkForWin(board, drawing.subList(0,num))) {
                    wonBoards.add(board)
                    if (wonBoards.size == boardAmount)
                        return calculatePoints(board, drawing.subList(0,num))
                }
            }
        }
        return -1
    }

    val testInput = readInput("Day04_test")
    check(part1(testInput) == 4512)
    check(part2(testInput) == 1924)


    val input = readInput("Day04")
    println(part1(input))
    check(part1(input) == 69579)
    println(part2(input))
    check(part2(input) == 14877)

}

