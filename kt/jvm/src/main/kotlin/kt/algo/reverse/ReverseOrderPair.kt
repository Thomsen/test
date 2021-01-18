package kt.algo.reverse

import kt.parser.num
import java.util.*

class ReverseOrderPair {

    /**
     * time: O(n^2)
     * space: O(1)
     */
    fun reversePairs(nums: IntArray): Int {
        var count = 0
        var n = nums.size
        for (i in 0 until n - 1) {
            for (j in i+1 until n) {
                if (nums[i] > nums[j]) {
                    count ++
                }
            }
        }
        return count
    }

    /**
     * merge order reverse
     * time: O(nlogn)
     * space: O(n)
     */
    fun reverseMergePairs(nums: IntArray): Int {
        return mergeSortAndCount(nums, 0, nums.size - 1)
    }

    private fun mergeSortAndCount(nums: IntArray, start: Int, end: Int): Int {
        var count = 0
        if (start < end) {
            val middle = (start + end) / 2
            count += mergeSortAndCount(nums, start, middle)
            count += mergeSortAndCount(nums, middle + 1, end)
            count += mergeAndCount(nums, start, middle, end)
        }
        return count
    }

    private fun mergeAndCount(nums: IntArray, start: Int, middle: Int, end: Int): Int {
        val left = nums.copyOfRange(start, middle + 1)
        val right = nums.copyOfRange(middle + 1, end + 1)

        var i = 0
        var j = 0
        var k = start
        var swaps = 0

        while (i < left.size && j < right.size) {
            if (left[i] <= right[j]) {
                nums[k++] = left[i++]
            } else {
                nums[k++] = right[j++]
                swaps += (middle + 1) - (start + i)
            }
        }

        while (i < left.size) {
            nums[k++] = left[i++]
        }

        while (j < right.size) {
            nums[k++] = right[j++]
        }

        return swaps
    }

    /**
     * bit binary tree
     */
    fun getSum(bitTree: IntArray, index: Int): Int {
        return 0
    }
}