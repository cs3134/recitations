import java.util.Iterator;

public class LinanIterator<T> implements Iterator<T> {

	private LinanNode<T> endMarker;
	private LinanNode<T> current;

	public LinanIterator(LinanNode<T> beginMarker, LinanNode<T> endMarker) {
		this.endMarker = endMarker;
		current = beginMarker.next;
	}

	@Override
	public boolean hasNext() {
		return current != endMarker;
	}

	@Override
	public T next() {
		T data = current.data;
		current = current.next;
		return data;
	}
}
