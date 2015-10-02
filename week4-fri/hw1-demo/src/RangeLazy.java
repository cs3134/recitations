import java.util.Iterator;

public class RangeLazy implements Iterable<Integer> {

	private int min;
	private int max;
	private int increment;

	public RangeLazy(int min, int max, int increment) {
		// check for invalid min max increments
		// (max - min) * increment should be > 0
		if ((max - min) * increment <= 0) {
			throw new IllegalArgumentException();
		}

		this.min = min;
		this.max = max;
		this.increment = increment;
	}

	public RangeLazy(int min, int max) {
		this(min, max, 1);
	}

	public java.util.Iterator<Integer> iterator() {
		return new RangeIterator();
	}

	private class RangeIterator implements Iterator<Integer> {

		private int iteratorMin;
		private int iteratorMax;
		private int iteratorIncrement;
		private int current;

		public RangeIterator() {
			iteratorMin = min;
			iteratorMax = max;
			iteratorIncrement = increment;
			current = iteratorMin;
		}

		@Override
		public boolean hasNext() {
			return iteratorIncrement > 0 ? (current < iteratorMax)
					: (current > iteratorMax);
		}

		@Override
		public Integer next() {
			int returnValue = current;
			current += iteratorIncrement;
			return returnValue;
		}
	}

	public static void main(String[] args) {
		for (Integer j : new RangeLazy(1, 8, 1)) {
			System.out.print(j + " ");
		}
		System.out.println();

		for (Integer j : new RangeLazy(1, 8, 2)) {
			System.out.print(j + " ");
		}
		System.out.println();

		for (Integer j : new RangeLazy(10, 4, -2)) {
			System.out.print(j + " ");
		}
		System.out.println();
	}
}
