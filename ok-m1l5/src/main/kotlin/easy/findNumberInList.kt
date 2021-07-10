package easy

fun findNumberInList(toFind: Int, numbers: List<Int>): Int {
    return numbers.firstOrNull { it == toFind } ?: -1
}
