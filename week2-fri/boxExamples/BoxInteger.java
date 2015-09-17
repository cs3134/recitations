public class BoxInteger {
  private int data;

  public BoxInteger(int data) {
    this.data = data;
  }

  public int getData() {
    return data;
  }

  public static void main(String[] args) {
    BoxInteger myInteger = new BoxInteger(259);
    System.out.println(myInteger.getData());
  }
}
