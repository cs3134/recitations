/**
 * This is the singly-linked list node class. BAD IDEA. DON'T USE.
 * @author annalawson
 * @param <NodeT> type of data store in node
 */
public class Node<NodeT> {
	private NodeT data;
	private Node<NodeT> next;

	public Node(NodeT d, Node<NodeT> n) {
	    data = d;
		next = n;
	}

	//Now we need setters and getters
	public NodeT getData() {
		return data;
	}
	public void setData(NodeT d) {
		data = d;
	}
	public Node<NodeT> getNext() {
		return next;
	}
	public void setNext(Node<NodeT> n) {
		next = n;
	}
}

