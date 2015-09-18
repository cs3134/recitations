public class BoxGeneric<T> {
  private T data;

  public BoxGeneric(T data) {
    this.data = data;
  }

  public T getData() {
    return data;
  }

  public static void main(String[] args) {
    BoxGeneric<Integer> myBox = new BoxGeneric<>(259);
    System.out.println(myBox.getData());
    BoxGeneric<String> myBox2 = new BoxGeneric<>("cow");
    System.out.println(myBox2.getData());
  }
}
