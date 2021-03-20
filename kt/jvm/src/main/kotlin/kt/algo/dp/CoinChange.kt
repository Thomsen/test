package kt.algo.dp

class CoinChange {

    fun coinChange(coins: IntArray, amount: Int): Int {
        return baseCase(coins, amount)
    }

    private fun baseCase(coins: IntArray, amount: Int): Int {
        if (amount < 0) return -1
        if (amount == 0) return 0

        var count = Int.MAX_VALUE

        for (coin in coins) {
            val subprogram = baseCase(coins, amount - coin)
            if (subprogram != -1) {
                // min count and 1+subprogram
                count = count.coerceAtMost(1 + subprogram)
            }
        }

        if (count == Int.MAX_VALUE) {
            count = -1
        }

        return count
    }

    private lateinit var memo: MutableMap<Int, Int>

    fun coinChangeMemo(coins: IntArray, amount: Int): Int {
        memo = mutableMapOf()
        return baseMemo(coins, amount)
    }

    private fun baseMemo(coins: IntArray, amount: Int): Int {
        if (memo.containsKey(amount)) return memo[amount] ?: -1
        if (amount < 0) return -1
        if (amount == 0) return 0

        var count = Int.MAX_VALUE

        for (coin in coins) {
            val subprogram = baseMemo(coins, amount - coin)
            if (subprogram != -1) {
                // min count and 1+subprogram
                count = count.coerceAtMost(1 + subprogram)
            }
        }

        if (count == Int.MAX_VALUE) {
            count = -1
        }

        memo.putIfAbsent(amount, count)

        return count
    }

    fun coinChangeDp(coins: IntArray, amount: Int): Int {
        val dpVector = Array(amount + 1) { amount + 1 }
        dpVector[0] = 0
        for (i in dpVector.indices) {
            for (coin in coins) {
                if (i - coin >= 0) {
                    dpVector[i] = dpVector[i].coerceAtMost( 1 + dpVector[i - coin])
                }
            }
        }

        return if ((dpVector[amount] == amount + 1)) -1 else dpVector[amount]
    }

}