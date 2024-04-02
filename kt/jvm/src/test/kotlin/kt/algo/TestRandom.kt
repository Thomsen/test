package kt.algo

import org.junit.Test
import java.text.DecimalFormat



class TestRandom {
    @Test
    fun `test coefficient`() {
        var count = 0

        for (i in 0..99) {
            val df = DecimalFormat("#.##")
            val randomValue = df.format(Math.random()).toFloat()

            println("$i = $randomValue")
            if (randomValue < 0.01) {
                println("第 $i 次执行操作")
                count++
            }
        }

        if (count == 0) {
            println("在 100 次循环中没有执行操作。")
        } else {
            println("在 100 次循环中总共执行了 $count 次操作。")
        }
    }
}