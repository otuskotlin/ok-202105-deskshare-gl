import kotlin.test.Test

class AopTest {
    @Test
    fun `aop test`() {
        val customer = Customer(id = 1, name = "test", address = "pushkin 12")
        println(getAddress(customer))
    }
}

class Customer(val id: Int, val name: String, val address: String)

fun getAddress(customer: Customer): String = cachedBy(customer.id) {customer.address}

fun cachedBy(
    key: Int,
    block: () -> String
): String {
    val address: String = block()
    //println("cache by $key")
    return "$key adress from cache $address"
}

