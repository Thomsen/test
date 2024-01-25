package kt.flow.autorefresh

import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.test.runTest
import org.junit.Test
import java.time.LocalDateTime
import kotlin.time.Duration.Companion.seconds
import kotlin.time.ExperimentalTime

class TestTickFlow {

    @Test
    fun `test ticker flow no such method error`() = runTest {
        tickerFlow(2.seconds)
            .map {
                "load data ${LocalDateTime.now()}"
            }.collectLatest {
                print(it)
            }
    }

    @Test
    fun `test ticker flow`() = runTest(timeout = 4.seconds) {
        tickerFlow(2000)
            .map {
                "load data ${LocalDateTime.now()}"
            }.collectLatest {
                println(it)
            }
    }


    @Test
    fun `test synchronized ticker flow`() = runTest {
        results.collectLatest {
            println(it)
        }
    }
}