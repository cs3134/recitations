/**
 * Simulates python's range function
 */
public class Range implements Iterable<Integer> {

	private SimpleLinkedList<Integer> list;

	public Range(int min, int max, int increment) {

		if ((max - min) * increment <= 0) {
			throw new IllegalArgumentException();
		}

		list = new SimpleLinkedList<Integer>();

		for (int i = min; (increment > 0) ? i < max : i > max; i += increment) {
			list.add(i);
		}
	}

	public Range(int min, int max) {
		this(min, max, 1);
	}

	public java.util.Iterator<Integer> iterator() {
		return list.iterator();
	}

	public static void main(String[] args) {
		for (int i : new Range(0, 5, 1)) {
			System.out.println(i);
		}
	}
}
