package kt.algo.tree

fun createTree(): BinaryTree {
    val leftNode = TreeNode(TreeNode(value = 4), 2, TreeNode(value = 5))
    val rightNode = TreeNode(TreeNode(value = 6), 3, TreeNode(value = 7))

    val rootNode = TreeNode(leftNode, 1, rightNode)

    return BinaryTree(rootNode)
}

fun treeTraverse() {
    val tree = createTree()

    print("front traverse: ")
    tree.preOrder()

    println()
    print("middle traverse: ")
    tree.inOrder()

    println()
    print("after traverse: ")
    tree.postOrder()

    println()
    print("bfs: ")
    print(tree.bfs(tree.rootNode, TreeNode(value = 5)))
}