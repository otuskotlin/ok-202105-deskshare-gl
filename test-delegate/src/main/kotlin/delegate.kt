import kotlin.reflect.KProperty

class PropDelegate {
    operator fun getValue(erf: Any?, kProp: KProperty<*>): String {
        return "my name is ${kProp.name}"
    }
}

class Property {
    val prop: String by PropDelegate()
}

fun main() {
    val p1 = Property()
    val p2: String by PropDelegate()

    println(p1.prop)
    println(p2)
}
