# Monday October 5

## Trees and BSTs
Notes loosely adapted from Prof Peter Allen's 3137 notes at (http://www.cs.columbia.edu/~allen/S14/NOTES/trees.pdf). 

### What are trees?
Trees are made up of **nodes** and **edges** (connections between nodes). These nodes and connections are similar to those we saw with linked lists, but we're going to structure them differently here. Linked lists can represent linear data, trees can represent linear or nonlinear data.

Each node has one "parent" node, and any number of "child" nodes. Think family tree if humans reproduced individually. Or a family tree of one gender (Great grandma Susan had three daughters, Molly, Jo, and Sally, who each had two daughters).

All tree have a **root**: the root is a special node. It's the one node that doesn't have a parent, i.e. it's parent is null. The root is kind of like the "head" for a linked list; it's our access point to the data in our data structure.

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

The **internal path length** is the the sum of the paths from *each* node to the root.

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

####Preorder: Root, Left subtree, Right subtree (recursively)

```bash
// an preOrderTraversal that prints the list
public static void preOrderTraversal(BinaryTreeNode n) {
	if (n != null) {

      // Root. This line depends what you're trying to do with the traversal
      //Here's we're just printing. Apply whatever you're doing to the root.
      System.out.print(t.element + " ");

	  // recurse on left subtree
      preOrderTraversal(n.left);

      // recurse on right subtree
      preOrderTraversal(n.right);
    }
}
```


####Inorder: Left subtree, Root, Right subtree

```bash
// an inOrderTraversal that prints the list
public static void inOrderTraversal(BinaryTreeNode n) {
	if (n != null) {

	  // recurse on left subtree
      inOrderTraversal(n.left);

      // Root. This line depends what you're trying to do with the traversal
      //Here's we're just printing. Apply whatever you're doing to the root.
      System.out.print(n.data + " ");

      // recurse on right subtree
      inOrderTraversal(n.right);
    }
}
```

####Postorder: Left subtree, Right subtree, root

```bash
// an postOrderTraversal that prints the list
public static void postOrderTraversal(BinaryTreeNode n) {
	if (n != null) {

	  // recurse on left subtree
      postOrderTraversal(n.left);

      // recurse on right subtree
      postOrderTraversal(n.right);

      // Root. This line depends what you're trying to do with the traversal
      //Here's we're just printing. Apply whatever you're doing to the root.
      System.out.print(t.element + " ");
    }
}
```

#### Level-Order, a.k.a. Breadth first search

Starting at the root, we vist all nodes on a given level before moving to the next lower level. Use a queue to make sure items are visiting in this FIFO order. 
Take root. Add its children to queue. When done with root, remove first item in queue. Add all of that node's children to the end of the queue. Continue until no more nodes.

```bash
public static void LevelOrderTraversal (BinaryTreeNode n) {
	Queue myQueue = any queue implementation
	myQueue.add(n);
	while (myQueue.size() > 0) {
        BinaryTreeNode currentNode = myQueue.remove();
        if (currentNode != null) {
            System.out.print(currentNode); //do whatever here to "visit" the node
            myQueue.add(currentNode.left); //add left child
            myQueue.add(currentNode.right); //add right child
        }
    }
}
```

Here, we're working in terms of horizontal tree levels, rather than tackling the problem by breaking into subtrees, so instead of recursing, we use a queue.


###Binary Search Trees

All nodes in left child subtree have smaller data than current node. All nodes in right child subtree have larger data than current subtree. We sort by comparing the "key" value of a node. If our tree holds integers, we'd just compare the ints. If our tree holds BankAccount objects, maybe the "key" that we sort by is the account balance (the idea is that for objects that don't have an inherent order, you're going to have to decide how to compare them. You'll probably want to implement comparable (i.e. write a compareTo() method)).


Now, we can use binary search to find or insert!

####Insert or search for item with key "k" in a binary search tree:

* If k > root's key, search right subtree
* If k < root's key, search left subtree
* If k == root's key, we found it!
  * If seaching, return the node.
  * If inserting, then this data is already in the tree. You may want to update the node (maybe by incrementing a count?) to show that you now have another item with the same value.

* If a null reference is found:
  * If searching, the item didn't exist. Return null.
  * If inserting: The item is not already in the tree, so we want to insert at the current location (i.e. insert a new node as the child of the last non null node we visited).


If your BST is reasonably balanced, runtimes will be ~ the runtimes for binary search. 

However, what does a BST look like if the root is the largest item in the tree?
What would the runtime be?

Runtime for a search varies from O(Log N) to O(N).

####Deleting from a BST
* If leaf node: delete
* If node has one child: put child where the parent is
* If node has two child nodes: Next largest node is leftmost node of right subtree. Put that where the current node was to delete the current node.


Wrapping private recursive functions in a public function


```bash

public static void inOrderTraversal() {
	inOrderTraversal(rootNode);
}

private static void inOrderTraversal(BinaryTreeNode n) {
	if (n != null) {
      inOrderTraversal(n.left);
      System.out.print(n.data + " ");
      inOrderTraversal(n.right);
    }
}

* User doesn't have to pass rootNode -- easier for them, and safer bc we can keep rootNode private. User doesn't need access to this. 

* Also, if some parameters need to be error checked before we recursive, this is a great way to do that. You can check in the public, non-recursive method, before calling the recursive method so that you don't have a bunch of error checking in the recursive method.



















