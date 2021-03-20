package kt.algo.dp

import org.junit.Test
import kotlin.test.assertEquals

class TestCoinChange {

    private var coinChange: CoinChange = CoinChange()

    @Test
    fun testCoin1() {
        val coins = intArrayOf(1, 2, 5)
        val amount = 11
        assertEquals(3, coinChange.coinChange(coins, amount))
        // timeout
//        assertEquals(20, coinChange.coinChange(coins, 100))
    }

    @Test
    fun testCoinMemo1() {
        val coins = intArrayOf(1, 2, 5)
        val amount = 11
        assertEquals(3, coinChange.coinChangeMemo(coins, amount))
        assertEquals(20, coinChange.coinChangeMemo(coins, 100))
    }

    @Test
    fun testCoinDp1() {
        val coins = intArrayOf(1, 2, 5)
        val amount = 11
        assertEquals(3, coinChange.coinChangeDp(coins, amount))
        assertEquals(20, coinChange.coinChangeDp(coins, 100))
    }

}