package kt.algo.tree

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

}