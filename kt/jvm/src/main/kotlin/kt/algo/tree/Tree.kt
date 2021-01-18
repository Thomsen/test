package kt.algo.tree

import java.util.*
import kotlin.collections.HashSet

data class TreeNode (
    var leftNode: TreeNode? = null,
    var value: Int? = null,
    var rightNode: TreeNode? = null
)

fun TreeNode.preOrder() {
    // defensive coding
    if (this == null) return
    print("${this.value}")
    this.leftNode?.preOrder()
    this.rightNode?.preOrder()
}

fun TreeNode.inOrder() {
    if (this == null) return
    this.leftNode?.inOrder()
    print("${this.value}")
    this.rightNode?.inOrder()
}

fun TreeNode.postOrder() {
    if (this == null) return
    this.leftNode?.postOrder()
    this.rightNode?.postOrder()
    print("${this.value}")
}

class BinaryTree (var rootNode: TreeNode) {

    fun preOrder() {
        rootNode.preOrder()
    }

    fun inOrder() {
        rootNode.inOrder()
    }

    fun postOrder() {
        rootNode.postOrder()
    }

    fun bfs(source: TreeNode, target: TreeNode): Int {
        var queue: Queue<TreeNode> = LinkedList()
        var visited: MutableSet<TreeNode> = HashSet()

        queue.offer(source)
        visited.add(source)

        var step = 0

        while (queue.isNotEmpty()) {
            var size = queue.size
            for (i in queue.indices) {
                val current = queue.poll()
                if (current.value == target.value) {
                    return step
                }
                addVisited(current.leftNode, visited, queue)
                addVisited(current.rightNode, visited, queue)
            }
            step ++
        }

        return -1
    }

    private fun addVisited(
        next: TreeNode?,
        visited: MutableSet<TreeNode>,
        queue: Queue<TreeNode>
    ) {
        if (next != null && !visited.contains(next)) {
            queue.offer(next)
            visited.add(next)
        }
    }
}