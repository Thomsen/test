package kt.algo.backtrack

import java.util.*

class FullAlignment {

    var res = LinkedList<List<Int?>>()

    fun permute(nums: IntArray): List<List<Int?>> {
        val track = LinkedList<Int?>()
        backtrack(nums, track)
        return res
    }

    private fun backtrack(nums: IntArray, track: LinkedList<Int?>) {
        if (track.size == nums.size) {
            res.add(LinkedList(track))
            return
        }

        for (i in nums.indices) {
            if (track.contains(nums[i])) {
                continue
            }
            track.add(nums[i])
            backtrack(nums, track)
            track.removeLast()
        }
    }
}