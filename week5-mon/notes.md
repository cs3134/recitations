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

The **level** of a node, which is also sometimes called the depth, is the length of the path from the root to your node. Here, the root's level is 0, and the leaf's level is the number of edges from the leaf to the root.

Here are levels. The height is the opposite. 
![alt text](https://github.com/cs3134/recitation-devo/blob/master/week5-mon/images/tree-levels.jpg)
Image source: http://www.gamedev.net/page/resources/_/technical/general-programming/trees-part-1-r1374


### General Trees

In a general tree, a node can have any number of children. But wait! How can a node have an unknown number of instance variables? How would we declare this? We don't! We use a linked list to store child nodes. Each node just has a reference to a first child (instead of "first child" "second child" "third child" ...."10000th child"). Then, to form a linked list, each node has a reference to it's "next sibling." (Note that the root's next sibling would be null).

```bash
public class GeneralTreeNode<T> {
	T data; //the info we're storing
	GeneralTreeNode nextSibling; //here's how we make the linked list
	GeneralTreeNode firstChild; //pointer to "head" of linked list of children
}

Here's an example of a general tree using linked lists:

![alt text](https://github.com/cs3134/recitation-devo/blob/master/week5-mon/images/general_tree.jpg)
Image source: http://www.gamedev.net/page/resources/_/technical/general-programming/trees-part-1-r1374

### Binary Trees






