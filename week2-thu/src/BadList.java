/**
 * BadList class implements a singly-linked list. Adapted from Weiss, Data
 * Structures and Algorithm Analysis in Java. 3rd ed.
 * http://users.cis.fiu.edu/~weiss/dsaajava3/code/BadList.java
 */
public class BadList<T> {

	private int size;
	private BadNode<T> beginMarker;
	private BadNode<T> endMarker;

	/**
	 * Construct an empty BadList.
	 */
	public BadList() {
		clear();
	}

	/**
	 * Change the size of this collection to zero by initializing the beginning
	 * and end marker.
	 */
	public void clear() {
		beginMarker = new BadNode<T>(null, null);
		endMarker = new BadNode<T>(null, null);
		beginMarker.setNext(endMarker);
		size = 0;
	}

	/**
	 * @return the number of items in this collection.
	 */
	public int size() {
		return size;
	}

	/**
	 * @return boolean indicating if the linked list is empty
	 */
	public boolean isEmpty() {
		return size == 0;
	}

	/**
	 * Gets the Node at position idx, which must range from lower to upper.
	 * When index = -1, returns head. When index = size+1, returns tail.
	 * NOTE: Notice how this is different from the getNode in the homework?
	 * Can you figure out why?
	 * @param idx index to search at.
	 * @param lower lowest valid index.
	 * @param upper highest valid index.
	 * @return internal node corresponding to idx.
	 * @throws IndexOutOfBoundsException if index is not between lower and
	 * upper, inclusive.
	 */
	private BadNode<T> getNode(int idx, int lower, int upper) {
		BadNode<T> p;

		if (idx < lower || idx > upper)
			throw new IndexOutOfBoundsException("getNode index: " + idx + ";"
					+ "size: " + size());
		p = beginMarker;
		for (int i = -1; i < idx; i++)
		    p = p.getNext();
		
		return p;
	}

	/**
	 * Gets the Node at position idx, which must range from 0 to size( ) - 1.
	 * @param idx index to search at.
	 * @return internal node corresponding to idx.
	 * @throws IndexOutOfBoundsException if index is out of range.
	 */
	private BadNode<T> getNode(int idx) {
		return getNode(idx, 0, size() - 1);
	}

	/**
	 * Returns the item at position idx.
	 * @param idx the index to search in.
	 * @throws IndexOutOfBoundsException if index is out of range.
	 */
	public T get(int idx) {
		return getNode(idx).getData();
	}

	/**
	 * Changes the item at position idx.
	 * 
	 * @param idx the index to change.
	 * @param newVal the new value.
	 * @return the old value.
	 * @throws IndexOutOfBoundsException if index is out of range.
	 */
	public T set(int idx, T newVal) {
		BadNode<T> p = getNode(idx);
		T oldVal = p.getData();
		p.setData(newVal);
		return oldVal;
	}

	/**
	 * Adds an item in front of node p, shifting p and all items after it one
	 * position to the right in the list.
	 * @param p Node to add before.
	 * @param x any object.
	 * @throws IndexOutOfBoundsException if idx < 0 or idx > size()
	 */
	private void addAfter(BadNode<T> p, T x) {
		BadNode<T> newNode = new BadNode<T>(x, p.getNext());
		p.setNext(newNode);
		size++;
	}

	/**
	 * Adds an item at specified index. Remaining items shift up one index.
	 * Note that when idx is -1, getNode returns head; for size+1, returns tail.
	 * @param x any object.
	 * @param idx position to add at.
	 * @throws IndexOutOfBoundsException if idx < 0 or idx > size()
	 */
	public void add(int idx, T x) {
            addAfter(getNode(idx-1, -1, size()-1), x);
	}

	/**
	 * Adds an item to this collection, at the end.
	 * @param x any object.
	 */
	public void add(T x) {
	    add(size(), x);
	}

	/**
	 * Removes the object contained in Node p.
	 * NOTE: see how slow this is compared to remove(Node<T> p) in your hw?
	 * Can you figure out why?
	 * @param p the Node containing the object.
	 * @return the item was removed from the collection.
	 */
	private T remove(BadNode<T> p) {
		BadNode<T> current = beginMarker;
		//Look through to find the node whose next is p
		while (current.getNext().getData() != null &&
				current.getNext().getData() != p.getData()) {
			current = current.getNext();
		}
		//Now delete p
		current.setNext(p.getNext());
		size--;
		return p.getData();
	}

	/**
	 * Removes an item from this collection.
	 * @param idx the index of the object.
	 * @return the item was removed from the collection.
	 */
	public T remove(int idx) {
		return remove(getNode(idx));
	}

	/**
	 * Returns a String representation of this collection.
	 */
	public String toString() {
		StringBuilder sb = new StringBuilder("[ ");
		BadNode<T> current = beginMarker.getNext();
                for (int i = 0; i < size(); i++) {
		    sb.append(current.getData() + " ");
                    current = current.getNext();
		}
		sb.append("]");
		return new String(sb);
	}

	/**
	 * Test the linked list.
	 */
	public static void main(String[] args) {
		BadList<Integer> lst = new BadList<>();

		for (int i = 0; i < 10; i++)
			lst.add(i);
		for (int i = 20; i < 30; i++)
			lst.add(0, i);

		lst.remove(0);
		lst.remove(lst.size() - 1);

		System.out.println(lst);
	}
}
