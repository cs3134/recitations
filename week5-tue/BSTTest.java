public class BSTTest {

  public static void main(String[] args) {
    BST t = new BST(
      5,
      new BST(
        3,
        new BST(2),
        new BST(4)
      ),
      new BST(
        7,
        new BST(6),
        new BST(8)
      )
    );

    System.out.println(t);

    // insert
    t.insert(1);
    t.insert(9);
    System.out.println(t);

    // find
    System.out.println(t.find(10));
    System.out.println(t.find(3));

    // recursive traversals
    System.out.println(t.preOrder());
    System.out.println(t.inOrder());
    System.out.println(t.postOrder());

    // iterative traversals
    System.out.println(t.depthFirst());
    System.out.println(t.breadthFirst());
  }
}
