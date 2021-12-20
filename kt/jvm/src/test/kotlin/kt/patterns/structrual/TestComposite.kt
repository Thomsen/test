package kt.patterns.structrual

import kotlinx.coroutines.runBlocking
import org.junit.Test
import kotlin.test.assertEquals

class TestComposite {

    @Test
    fun testSingleComposite() {
        val impl = PortfolioRepositoryImpl()
        runBlocking {
            val component = impl.getUserPortfolio("1111")
            assertEquals(22.0, component.getValue())
        }
    }

    @Test
    fun testMultipleComposite() {
        val impl = PortfolioRepositoryImpl()
        runBlocking {
            val component = impl.getUserPortfolio("2222")
            assertEquals(43.3, component.getValue())
        }
    }
}