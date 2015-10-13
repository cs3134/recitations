import java.util.ArrayDeque;

public class FastBuffer implements Buffer {

  private ArrayDeque<Character> left;
  private ArrayDeque<Character> right;

  public FastBuffer() {
    clear();
  }

  private void clear() {
    left = new ArrayDeque<Character>();
    right = new ArrayDeque<Character>();
  }

  @Override
  public int size() {
    return left.size() + right.size();
  }

  @Override
  public void load(char[] initial, int cursorPosition) {
    clear();

    for (char c : initial) {
      left.push(c);
    }

    setCursor(cursorPosition);
  }

  @Override
  public char[] toArray() {
    char[] array = new char[size()];
    int i = 0;
    for (Character c : left) {
      array[left.size() - 1 - i] = c.charValue();
      i++;
    }
    for (Character c : right) {
      array[i] = c.charValue();
      i++;
    }
    return array;
  }

  @Override
  public int getCursor() {
    return left.size();
  }

  @Override
  public void setCursor(int j) {
    while (getCursor() != j) {
      if (j < getCursor()) {
        moveLeft();
      }
      if (j > getCursor()) {
        moveRight();
      }
    }
  }

  @Override
  public void moveRight() {
    left.push(right.pop());
  }

  @Override
  public void moveLeft() {
    right.push(left.pop());
  }

  @Override
  public void insertLeft(char c) {
    left.push(c);
  }

  @Override
  public char deleteRight() {
    return right.pop();
  }

  @Override
  public char deleteLeft() {
    return left.pop();
  }
}
