/**
 * LinkedList class implements a doubly-linked list.
 * Adapted from Weiss, Data Structures and Algorithm Analysis in Java. 3rd ed.
 * http://users.cis.fiu.edu/~weiss/dsaajava3/code/SimpleLinkedList.java
 */
public class LinkedList<T>{

  private int size;
  private Node<T> head;
  private Node<T> tail;

  /**
   * Doubly-Linked-List Node class
   */
  private static class Node<T> {
    public T data;
    public Node<T> next;
    public Node<T> prev;
    
    public Node(T d, Node<T> p, Node<T> n) {
      data = d;
      next = n;
      prev = p;
    }
  }

  /**
   * Construct an empty LinkedList.
   */
  public LinkedList() {
    clear();
  }

  /**
   * Make an empty list by making head point to a node with null data.
   */
  public void clear() {
    head = new Node<>(null, null, null);
    tail = new Node<>(null, head, null);
    head.next = tail;
    size = 0;
  }

  /**
   * Returns the number of nodes in the list
   */
  public int size() {
    return size;
  }

  /**
   * Returns true if list is empty, false otherwise.
   */
  public boolean isEmpty() {
    return size() == 0;
  }

  /**
   * Gets the Node at position index, which must range from lower to upper.
   * @param index index to search at.
   * @param lower lowest valid index.
   * @param upper highest valid index.
   * @return internal node corresponding to index.
   * @throws IndexOutOfBoundsException if index is not between lower and
   * upper, inclusive.
   */
  private Node<T> getNode(int index, int lower, int upper) {
    Node<T> current;

    if (index < lower || index > upper)
      throw new IndexOutOfBoundsException("getNode index: " + index
          + "; size: " + size());


    if (index < size() / 2) { // Search through list from the beginning
      current = head.next;
      for (int i = 0; i < index; i++)
        current = current.next;
    } else { // search through the list from the end
      current = tail;
      for (int i = size(); i > index; i--)
        current = current.prev;
    }
    return current;
  }
  
  /**
   * Gets the Node at position index, which must range from 0 to size( ) - 1.
   * 
   * @param index index to search at.
   * @return internal node corresponding to index.
   * @throws IndexOutOfBoundsExceptionif index is out of range.
   */
  private Node<T> getNode(int index) {
    return getNode(index, 0, size() - 1);
  }

  /**
   * Returns the item at position index.
   * @param index the index to search in.
   * @throws IndexOutOfBoundsException if index is out of range.
   */
  public T get(int index) {
    return getNode(index, 0, size - 1).data;
  }

  /**
   * Changes the data at the given index. Returns the old data.
   * @param index the index to change.
   * @param newData to store.
   * @return the old data.
   */
  public T set(int index, T newData) {
    Node<T> node = getNode(index);
    T oldData = node.data;

    node.data = newData;
    return oldData;
  }

  /**
   * Adds an item in front of node p, shifting p and all items after it one
   * position to the right in the list.
   * @param p Node to add before.
   * @param x any object.
   * @throws IndexOutOfBoundsException if index < 0 or index > size()
   */
  private void addBefore(Node<T> node, T x) {
    Node<T> newNode = new Node<>(x, node.prev, node);
    newNode.prev.next = newNode;
    node.prev = newNode;
    size++;
  }

  /**
   * Adds an item at specified index. Remaining items shift up one index.
   * @param x any object.
   * @param index position to add at.
   * @throws IndexOutOfBoundsException if index < 0 or index > size()
   */
  public void add(int index, T x) {
    addBefore(getNode(index, 0, size()), x);
  }

  /**
   * Adds to the end of the list.
   * @param x any object.
   */
  public void add(T x) {
    add(size(), x);
  }

  /**
   * Removes the object contained in Node p.
   * @param node the Node containing the object.
   * @return the item was removed from the collection.
   */
  private T remove(Node<T> node) {
    node.next.prev = node.prev;
    node.prev.next = node.next;
    size--;
    return node.data;
  }

  /**
   * Removes the node at given index and returns the data at that node.
   */
  public T remove(int index) {
    return remove(getNode(index));
  }
  
  /**
   * Obtains an Iterator object used to traverse the collection.
   * @return an iterator positioned prior to the first element.
   */
  public java.util.Iterator<T> iterator() {
    return new LinkedListIterator();
  }
  
  /**
   * This is the implementation of the LinkedListIterator. It maintains a
   * notion of a current position and of course the implicit reference to the
   * SimpleLinkedList.
   */
  private class LinkedListIterator implements java.util.Iterator<T> {
    private Node<T> current = head.next;
    private boolean okToRemove = false;

    public boolean hasNext() {
      return current != tail;
    }

    public T next() {
      if (!hasNext())
        throw new java.util.NoSuchElementException();

      T nextItem = current.data;
      current = current.next;
      okToRemove = true;
      return nextItem;
    }

    public void remove() {
      if (!okToRemove)
        throw new IllegalStateException();

      LinkedList.this.remove(current.prev);
      okToRemove = false;
    }
  }

  /**
   * Returns the list as a string (ex: "[1, 2, 3, 4, 5]").
   */
  public String toString() {
    String output = "[";
    Node<T> current = head.next; //skip sentinel node
    //add first n-1 items followed by a comma
    while (current.next.next != null) {
      output += current.data + ", ";
      current = current.next;
    } //now add last item
    output += current.data + "]";
    return output;
  }
}
