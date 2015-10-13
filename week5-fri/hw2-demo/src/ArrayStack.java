
public class ArrayStack {

  private char[] array;
  private int leftPointer;
  private int rightPointer;

  public ArrayStack() {
    array = new char[10000000];
    leftPointer = 0;
    rightPointer = array.length - 1;
  }

  public void pushLeft(char c) {
    array[leftPointer++] = c;
  }

  public void pushRight(char c) {
    array[rightPointer--] = c;
  }

  public char popLeft() {
    return array[leftPointer--];
  }

  public char popRight() {
    return array[rightPointer++];
  }

  public int sizeLeft() {
    return leftPointer;
  }

  public int sizeRight() {
    return array.length - 1 - rightPointer;
  }

  public char[] toArray() {
    char[] arrayReturn = new char[sizeLeft() + sizeRight()];
    for (int i = 0; i < sizeLeft(); i++) {
      arrayReturn[i] = array[i];
    }

    for (int i = 0; i < sizeRight(); i++) {
      arrayReturn[sizeLeft() + i] = array[rightPointer + i];
    }

    return arrayReturn;
  }
}
