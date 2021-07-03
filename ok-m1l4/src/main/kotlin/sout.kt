fun sout(block: () -> Any?) {
    val result = block();
    print(result)
}

fun soutWithtime(block: LogContext.() -> Any?) {
    val result = block(LogContext());
    print(result)
}

class LogContext {
    fun time() = System.currentTimeMillis()
}
