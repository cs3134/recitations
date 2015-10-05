# Monday October 5

## Trees and BSTs
Notes loosely adapted from Prof Peter Allen's 3137 notes at (http://www.cs.columbia.edu/~allen/S14/NOTES/trees.pdf). 

### What are trees?
Trees are made up of **nodes** and **edges** (connections between nodes). These nodes and connections are similar to those we saw with linked lists, but we're going to structure them differently here. Linked lists can represent linear data, trees can represent linear or nonlinear data.

Each node has one "parent" node, and any number of "child" nodes. Think family tree if humans reproduced individually. Or a family tree of one gender (Great grandma Susan had three daughters, Molly, Jo, and Sally, who each had two daughters).

All tree's have a **root**: the root is a special node. It's the one node that doesn't have a parent, i.e. it's parent is null. The root is kind of like the "head" for a linked list; it's our access point to the data in our data structure.

Here's an example of a tree:

![alt text](https://github.com/cs3134/recitation-devo/blob/master/week5-mon/images/tree_example.jpg)
Image source: http://www.powayusd.com/pusdtbes/cs/class4.htm

Some more terminology:

We call a sequence of connected nodes a **path**. There is a path from the root to every node, but **not** a path from any node to any other node (there coulde be, but only if the two nodes have an ancestor/descendant relationship). The **path length** is the number of edges between those two nodes.

For any node other than the root, that node and its descendants form a **subtree** of the larger tree (think of a branch on a tree). 

Some confusing terms people mix up: height vs level/depth.

The **height** of any node is longest path to a leaf. A leaf's height is 0, and the root's height is length of the path from the root to the farthest leaf.

On the flip side, **depth**  of a node is the length of the path from the root to your node. Here, the root's depth is 0, and the leaf's depth is the number of edges from the leaf to the root. Sometimes people use the term **level** as synonymous with depth, othertimes they mean level(anyNode) = depth(anyNode) + 1, so that level(root) = 1 (starting at 1 instead of 0).


### General Trees

In a general tree, a node can have any number of children. But wait! How can a node have an unknown number of instance variables? How would we declare this? We don't! We use a linked list to store child nodes. Each node just has a reference to a first child (instead of "first child" "second child" "third child" ...."10000th child"). Then, to form a linked list, each node has a reference to it's "next sibling." (Note that the root's next sibling would be null).

```bash
public class GeneralTreeNode<T> {
	T data; //the info we're storing
	GeneralTreeNode<T> nextSibling; //here's how we make the linked list
	GeneralTreeNode<T> firstChild; //pointer to "head" of linked list of children
}
```

Here's an example of a general tree using linked lists:

![alt text](https://github.com/cs3134/recitation-devo/blob/master/week5-mon/images/general_tree.jpg)
Image source: http://www.gamedev.net/page/resources/_/technical/general-programming/trees-part-1-r1374

### Binary Trees
A tree where each node can have a maximum (inclusive) of 2 children. Image a 2-child family cap.

Since a node can only have up to two children, we can now skip the linked list and just have two instance variables to point children: leftChild and rightChild:

```bash
public class BinaryTreeNode<T>{
	T data;
	BinaryTreeNode<T> leftChild;
	BinaryTreeNode<T> rightChild;
}
```

Some binary tree terminology:

A binary tree is **full** if every node has either zero or two children, but not one.

A binary tree is **perfect** / **complete** if it is full *and* all leaves are at the same depth.

Recursion:

A lot of binary tree methods, such as calculateTreeHeight, or countNodesInTree, can be done recursively on subtrees.

For example, the number of nodes in a tree equals 1 + the number of nodes in the root's left child's subtree + the number of nodes in the root's right child's subtree.

So, we could say:

```bash
public static int countNodesInTree(BinaryTreeNode n) {
	if (n == null)
	    return 0;
	else
	    return (1 + countNodesInTree(n.left) + countNodeInTree(n.right));
	}
}

```

###Binary Tree Traversals









