import com.deskshare.cor.dsl.chain
import com.deskshare.cor.dsl.worker
import kotlin.test.Test

class CorTest {
    @Test
    fun `test CoR chain`() {
        chain<TestContext> {
            worker {
                title = "test worker"
                description = ""
                supports { some == 0 }
                handle {}
                onError { e: Throwable -> println("error") }
            }
        }
    }
}

data class TestContext(var some: Int = 0)



