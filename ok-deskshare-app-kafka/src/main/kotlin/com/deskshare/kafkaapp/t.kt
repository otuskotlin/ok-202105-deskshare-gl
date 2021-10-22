import kotlinx.coroutines.*

fun main() = runBlocking<Unit> {
    val a = async {
        println("I'm computing part of the answer")
        delay(600)
        6
    }
    val b = async {
        println("I'm computing another part of the answer")
        delay(200)
        7
    }
    println("The answer is ${a.await() * b.await()}")
}
