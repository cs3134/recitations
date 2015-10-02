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
