import kotlin.random.Random

class RandomDistribution : Sequence<Double> by sequence({
    while (true) {
        yield(Random.nextDouble())
        yield(Random.nextDouble())
    }
}) {
    fun scale(shift: Double, standard: Double) = map { it * standard + shift }
}

fun maisn() {
    val distribution = RandomDistribution().scale(20.0, 2.5);
    for (value in distribution.take(10)) {
        println(value)
    }
}
