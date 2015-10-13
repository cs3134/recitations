
public class Tree<T extends Comparable<T>> {
  private Node<T> root;

  public Tree() {
    root = new Node<T>(null, null, null);
  }

  public void add(T data) {
    if (root.data == null) {
      root.data = data;
      return;
    }
    add(root, data);
  }

  private void add(Node<T> current, T data) {
    if (data.compareTo(current.data) >= 0) {
      if (current.right == null) {
        Node<T> newNode = new Node<T>(data, null, null);
        current.right = newNode;
        return;
      } else {
        add(current.right, data);
      }
    } else {
      if (current.left == null) {
        Node<T> newNode = new Node<T>(data, null, null);
        current.left = newNode;
        return;
      } else {
        add(current.left, data);
      }
    }
  }

  public String toString() {
    StringBuilder sb = new StringBuilder();
    toString(root, sb, 0);
    return sb.toString().trim();
  }

  private void toString(Node<T> current, StringBuilder sb, int level) {
    if (current == null) {
      return;
    }
    for (int i = 0; i < level; i++) {
      sb.append(" ");
    }
    sb.append(current + "\n");
    toString(current.left, sb, level + 1);
    toString(current.right, sb, level + 1);
  }

  public static void main(String[] args) {
    Tree<Integer> tree = new Tree<>();
    tree.add(9);
    tree.add(8);
    tree.add(7);
    tree.add(15);
    tree.add(10);
    System.out.println(tree);
  }

  private class Node<NodeT extends Comparable<NodeT>> {
    public NodeT data;
    public Node<NodeT> left;
    public Node<NodeT> right;

    public Node(NodeT data, Node<NodeT> left, Node<NodeT> right) {
      this.data = data;
      this.left = left;
      this.right = right;
    }

    public String toString() {
      return data.toString();
    }
  }
}
