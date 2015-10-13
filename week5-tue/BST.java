import java.util.Stack;
import java.util.Queue;
import java.util.List;
import java.util.LinkedList;

public class BST {

  int data;
  BST left, right;

  public BST(int data) {
    this(data, null, null);
  }

  public BST(int data, BST left, BST right) {
    this.data = data;
    this.left = left;
    this.right = right;
  }

  public void insert(int data) {
    if (data <= this.data) {
      if (this.left == null) {
        this.left = new BST(data);
      } else {
        this.left.insert(data);
      }
    } else {
      if (this.right == null) {
        this.right = new BST(data);
      } else {
        this.right.insert(data);
      }
    }
  }

  public BST find(int data) {
    if (data == this.data) {
      return this;
    } else if (data <= this.data) {
      if (this.left == null) {
        return null;
      } else {
        return this.left.find(data);
      }
    } else {
      if (this.right == null) {
        return null;
      } else {
        return this.right.find(data);
      }
    }
  }

  /* Recursive traversals */

  public List<Integer> preOrder() {
    /* data, left, right */
    List<Integer> traversal = new LinkedList<>();
    traversal.add(this.data);
    if (this.left != null) {
      for (Integer data : this.left.preOrder()) {
        traversal.add(data);
      }
    }
    if (this.right != null) {
      for (Integer data : this.right.preOrder()) {
        traversal.add(data);
      }
    }
    return traversal;
  }

  public List<Integer> inOrder() {
    /* left, data, right */
    List<Integer> traversal = new LinkedList<>();
    if (this.left != null) {
      for (Integer data : this.left.inOrder()) {
        traversal.add(data);
      }
    }
    traversal.add(this.data);
    if (this.right != null) {
      for (Integer data : this.right.inOrder()) {
        traversal.add(data);
      }
    }
    return traversal;
  }

  public List<Integer> postOrder() {
    /* left, right, data */
    List<Integer> traversal = new LinkedList<>();
    if (this.left != null) {
      for (Integer data : this.left.postOrder()) {
        traversal.add(data);
      }
    }
    if (this.right != null) {
      for (Integer data : this.right.postOrder()) {
        traversal.add(data);
      }
    }
    traversal.add(this.data);
    return traversal;
  }

  /* Iterative traversals */

  public List<Integer> depthFirst() {
    /* same as preOrder */
    List<Integer> traversal = new LinkedList<>();
    Stack<BST> stack = new Stack<BST>();
    stack.push(this);
    while (!stack.empty()) {
      BST node = stack.pop();
      traversal.add(node.data);
      if (node.right != null) {
        stack.push(node.right);
      }
      if (node.left != null) {
        stack.push(node.left);
      }
    }
    return traversal;
  }

  public List<Integer> breadthFirst() {
    /* "level-by-level" */
    List<Integer> traversal = new LinkedList<Integer>();
    Queue<BST> queue = new LinkedList<BST>();
    queue.add(this);
    while (queue.peek() != null) {
      BST node = queue.poll();
      traversal.add(node.data);
      if (node.left != null) {
        queue.add(node.left);
      }
      if (node.right != null) {
        queue.add(node.right);
      }
    }
    return traversal;
  }

  public String toString() {
    StringBuilder s = new StringBuilder();
    s.append("(");
    s.append(this.data);
    if (this.left != null) {
      s.append(" ");
      s.append(this.left.toString());
    }
    if (this.right != null) {
      s.append(" ");
      s.append(this.right.toString());
    }
    s.append(")");
    return s.toString();
  }
}
