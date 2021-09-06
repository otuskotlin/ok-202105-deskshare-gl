import com.deskshare.cor.dsl.chain
import com.deskshare.cor.dsl.configuration
import com.deskshare.cor.dsl.worker
import kotlinx.coroutines.runBlocking
import java.time.Instant
import kotlin.test.Test
import kotlin.test.assertEquals

class CorTest {
    @Test
    fun `test CoR chain`() {
        val ctx = TestContext()
        val chain = chain<TestContext> {
            title = "tets of cor"
            description = ""

            configuration {
                logger { msg: String ->
                    val builder = StringBuilder()
                        .append(Instant.now().toString())
                        .append(" ")
                        .append(msg)
                    println(builder.toString())
                }
                logPropagation = true
            }

            worker {
                title = "foo worker"
                description = ""
                supports { foo == 0 }
                handle { foo++ }
                onError { e: Throwable -> println("error foo") }
            }

            worker {
                title = "bar worker"
                description = ""
                supports { bar == 0 }
                handle { bar++ }
                onError { e: Throwable -> println("error bar") }
            }

            chain {
                title = "sub chain"
                description = ""
                supports { bar == 1 && foo == 1}
                worker {
                    title = "bar worker 2"
                    description = ""
                    supports { bar == 1 }
                    handle { bar += 10 }
                }
            }
        }.build()

        runBlocking { chain.exec(ctx) }

        assertEquals(1, ctx.foo)
        assertEquals(11, ctx.bar)
    }
}

data class TestContext(var foo: Int = 0, var bar: Int = 0)



