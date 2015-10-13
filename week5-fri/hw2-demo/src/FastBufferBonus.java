public class FastBufferBonus implements Buffer {

  public CharDeque deque;

  public FastBufferBonus() {
    this(1);
  }

  public FastBufferBonus(int capacity) {
    this.deque = new CharDeque(capacity);
  }

  public int size() {
    return this.deque.leftSize() + this.deque.rightSize();
  }

  public void load(char initial[], int cursorPosition) {
    if (initial.length <= this.deque.capacity()) {
      for (int i = 0; i < cursorPosition; i++) {
        this.deque.pushLeft(initial[i]);
      }
      for (int i = initial.length - 1; i >= cursorPosition; i--) {
        this.deque.pushRight(initial[i]);
      }
    }
  }

  public char[] toArray() {
    char[] res = new char[this.size()];
    int i = 0;
    for (int j = 0; j < this.deque.leftSize(); j++) {
      res[i++] = this.deque.getLeft(j);
    }
    for (int j = 0; j < this.deque.rightSize(); j++) {
      res[i++] = this.deque.getRight(j);
    }
    return res;
  }

  public int getCursor() {
    return this.deque.leftSize();
  }

  public void setCursor(int j) {
    if (j < this.size()) {
      int offset = j - this.getCursor();
      if (offset < 0) {
        this.moveLeft(offset * -1);
      } else {
        this.moveRight(offset);
      }
    }
  }

  public void moveLeft() {
    this.moveLeft(1);
  }

  private void moveLeft(int n) {
    for (int i = 0; i < n; i++) {
      this.deque.pushRight(this.deque.popLeft());
    }
  }

  public void moveRight() {
    this.moveRight(1);
  }

  private void moveRight(int n) {
    for (int i = 0; i < n; i++) {
      this.deque.pushLeft(this.deque.popRight());
    }
  }

  public void insertLeft(char c) {
    this.deque.pushLeft(c);
  }

  public char deleteLeft() {
    return this.deque.popLeft();
  }

  public char deleteRight() {
    return this.deque.popRight();
  }

  public static void main(String[] args) {
    FastBufferBonus buffer = new FastBufferBonus();
    for (int i = 0; i < 100; i++) {
      buffer.insertLeft('a');
      System.out.println(buffer.toArray());
    }

    for (int i = 0; i < 100; i++) {
      buffer.deleteLeft();
      System.out.println(buffer.deque.occupancy());
      System.out.println(buffer.toArray());
    }
  }
}
