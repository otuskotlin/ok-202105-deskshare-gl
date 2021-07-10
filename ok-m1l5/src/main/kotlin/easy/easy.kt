package easy

import kotlinx.coroutines.*

suspend fun main() = coroutineScope {
    val numbers = generateNumbers()
    val toFind = 10
    val toFindOther = 1000

    val deferredList1 = async(CoroutineName("test")) { findNumberInList(toFind, numbers) }
    val deferredList2 = async(CoroutineName("test")) { findNumberInList(toFindOther, numbers) }

    val foundNumbers = listOf(
        deferredList1.await(),
        deferredList2.await()
    )

    foundNumbers.forEach {
        if (it != -1) {
            println("Your number found!")
        } else {
            println("Not found number $toFind")
        }
    }
}
