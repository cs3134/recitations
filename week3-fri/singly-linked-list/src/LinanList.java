import java.util.Iterator;

public class LinanList<T> implements Iterable<T> {
	private LinanNode<T> beginMarker;
	private LinanNode<T> endMarker;

	public LinanList() {
		endMarker = new LinanNode<T>(null, null);
		beginMarker = new LinanNode<T>(null, endMarker);
	}

	public void add(T data) {
		LinanNode<T> node = new LinanNode<>(data, beginMarker.next);
		beginMarker.next = node;
	}

	public T remove() {
		if (beginMarker.next == endMarker) {
			return null;
		}
		T data = beginMarker.next.data;
		beginMarker.next = beginMarker.next.next;
		return data;
	}

	@Override
	public Iterator<T> iterator() {
		return new LinanIterator<T>(beginMarker, endMarker);
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[ ");
		for (T data : this) {
			sb.append(data);
			sb.append(" ");
		}
		sb.append("]");
		return sb.toString();
	}

	private class LinanNode<NodeT> {
		public NodeT data;
		public LinanNode<NodeT> next;

		public LinanNode(NodeT data, LinanNode<NodeT> next) {
			this.data = data;
			this.next = next;
		}
	}

	private class LinanIterator<IteratorT> implements Iterator<IteratorT> {

		private LinanNode<IteratorT> endMarker;
		private LinanNode<IteratorT> current;

		public LinanIterator(LinanNode<IteratorT> beginMarker,
				LinanNode<IteratorT> endMarker) {
			this.endMarker = endMarker;
			current = beginMarker.next;
		}

		@Override
		public boolean hasNext() {
			return current != endMarker;
		}

		@Override
		public IteratorT next() {
			IteratorT data = current.data;
			current = current.next;
			return data;
		}
	}

	public static void main(String[] args) {
		LinanList<Integer> list = new LinanList<>();
		list.add(1);
		list.add(2);
		list.add(3);
		System.out.println(list);
		list.remove();
		System.out.println(list);
		list.remove();
		list.remove();
		System.out.println(list);
	}
}
