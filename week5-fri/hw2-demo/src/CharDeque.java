public class CharDeque {

  public static double RESIZE_UPPER = 0.75;
  public static double RESIZE_LOWER = 0.25;

  private char[] arr;
  private int i, j;

  public CharDeque(int capacity) {
    this.arr = new char[capacity];
    this.i = 0;
    this.j = capacity - 1;
  }

  public int capacity() {
    return this.arr.length;
  }

  public double occupancy() {
    return (i + (arr.length - j)) / (double) arr.length;
  }

  public int leftSize() {
    return this.i;
  }

  public int rightSize() {
    return this.capacity() - (this.j + 1);
  }

  public char getLeft(int i) {
    return (i >= 0 && i < this.leftSize()) ? this.arr[i] : 0;
  }

  public char getRight(int i) {
    return (i >= 0 && i < this.rightSize()) ? this.arr[this.j + i + 1] : 0;
  }

  public void pushLeft(char c) {
    if (this.i < this.j) {
      this.arr[this.i++] = c;
    }
    resizeCheck();
  }

  public void pushRight(char c) {
    if (this.j > this.i) {
      this.arr[this.j--] = c;
    }
    resizeCheck();
  }

  public char popLeft() {
    if (this.i > 0) {
      char c = this.arr[--this.i];
      this.arr[this.i] = 0;
      resizeCheck();
      return c;
    } else {
      return 0;
    }
  }

  public char popRight() {
    if (this.j < this.capacity() - 1) {
      char c = this.arr[++this.j];
      this.arr[this.j] = 0;
      resizeCheck();
      return c;
    } else {
      return 0;
    }
  }

  private void resizeCheck() {
    if (occupancy() > RESIZE_UPPER) {
      resizeExpand();
    } else if (occupancy() < RESIZE_LOWER) {
      resizeContract();
    }
  }

  private void resizeExpand() {
    System.out.println("Expanding");
    char[] newArr = new char[arr.length * 2];
    for (int a = 0; a < i; a++) {
      newArr[a] = arr[a];
    }
    int b = newArr.length - 1;
    for (int a = j; a >= 0; a--) {
      newArr[b--] = arr[a];
    }
    j = b;
    arr = newArr;
  }

  private void resizeContract() {
    System.out.println("Contracting");
    char[] newArr = new char[arr.length / 2];
    for (int a = 0; a < i; a++) {
      newArr[a] = arr[a];
    }
    int b = newArr.length - 1;
    for (int a = j; a >= 0; a--) {
      newArr[b--] = arr[a];
    }
    j = b;
    arr = newArr;
  }
}
