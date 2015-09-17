/**
 * BetterList class implements a singly-linked list. Adapted from Weiss, Data
 * Structures and Algorithm Analysis in Java. 3rd ed.
 * http://users.cis.fiu.edu/~weiss/dsaajava3/code/BetterList.java
 */
public class BetterList<T> implements Iterable<T> {

        private int size;
        private Node<T> beginMarker;
        private Node<T> endMarker;

        /**
         * This is the singly-linked list node class.
         */
        private class Node<NodeT> {
                public Node(NodeT d, Node<NodeT> n) {
                        data = d;
                        next = n;
                }

                public NodeT data;
                public Node<NodeT> next;
        }

        /**
         * Construct an empty BetterList.
         */
        public BetterList() {
                clear();
        }

        /**
         * Change the size of this collection to zero by initializing the beginning
         * and end marker.
         */
        public void clear() {
                beginMarker = new Node<T>(null, null);
                endMarker = new Node<T>(null, null);
                beginMarker.next = endMarker;
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
        private Node<T> getNode(int idx, int lower, int upper) {
                Node<T> p;

                if (idx < lower || idx > upper)
                        throw new IndexOutOfBoundsException("getNode index: " + idx + ";"
                                        + "size: " + size());
                p = beginMarker;
                for (int i = -1; i < idx; i++)
                    p = p.next;
                
                return p;
        }

        /**
         * Gets the Node at position idx, which must range from 0 to size( ) - 1.
         * @param idx index to search at.
         * @return internal node corresponding to idx.
         * @throws IndexOutOfBoundsException if index is out of range.
         */
        private Node<T> getNode(int idx) {
                return getNode(idx, 0, size() - 1);
        }

        /**
         * Returns the item at position idx.
         * @param idx the index to search in.
         * @throws IndexOutOfBoundsException if index is out of range.
         */
        public T get(int idx) {
                return getNode(idx).data;
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
                Node<T> p = getNode(idx);
                T oldVal = p.data;
                p.data = newVal;
                return oldVal;
        }

        /**
         * Adds an item in front of node p, shifting p and all items after it one
         * position to the right in the list.
         * @param p Node to add before.
         * @param x any object.
         * @throws IndexOutOfBoundsException if idx < 0 or idx > size()
         */
        private void addAfter(Node<T> p, T x) {
                Node<T> newNode = new Node<T>(x, p.next);
                p.next = newNode;
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
        private T remove(Node<T> p) {
                Node<T> current = beginMarker;
                //Look through to find the node whose next is p
                while (current.next.data != null && current.next.data != p.data) {
                        current = current.next;
                }
                //Now delete p
                current.next = p.next;
                size--;
                return p.data;
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
                for (T x : this) {
                        sb.append(x + " ");
                }
                sb.append("]");
                return new String(sb);
        }

        /**
         * Obtains an Iterator object used to traverse the collection.
         * @return an iterator positioned prior to the first element.
         */
        public java.util.Iterator<T> iterator() {
                return new BetterListIterator();
        }

        /**
         * This is the implementation of the BetterListIterator. It maintains a
         * notion of a current position and of course the implicit reference to the
         * BetterList.
         */
        private class BetterListIterator implements java.util.Iterator<T> {
                private Node<T> prev = beginMarker;
                private Node<T> current = beginMarker.next;
                private boolean okToRemove = false;

                public boolean hasNext() {
                        return current != endMarker;
                }

                public T next() {
                        if (!hasNext())
                                throw new java.util.NoSuchElementException();

                        T nextItem = current.data;
                        prev = current;
                        current = current.next;
                        okToRemove = true;
                        return nextItem;
                }

                public void remove() {
                        if (!okToRemove)
                                throw new IllegalStateException();

                        BetterList.this.remove(prev);
                        // ensures that remove() cannot be called twice during a single step
                        // in iteration
                        okToRemove = false;
                }
        }

        /**
         * Test the linked list.
         */
        public static void main(String[] args) {
                BetterList<Integer> lst = new BetterList<>();

                for (int i = 0; i < 10; i++)
                        lst.add(i);
                for (int i = 20; i < 30; i++)
                        lst.add(0, i);

                lst.remove(0);
                lst.remove(lst.size() - 1);

                System.out.println(lst);
        }
}
