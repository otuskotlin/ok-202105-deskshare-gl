import org.junit.Test

class SoutTest {
   @Test
   fun `sout with time`() {
       soutWithtime {
           time()
       }
   }

   @Test
   fun `sout test`() {
       sout {
           23 + 34
       }
   }
}
