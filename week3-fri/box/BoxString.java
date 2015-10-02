public class BoxString {
  private String data;

  public BoxString(String data) {
    this.data = data;
  }

  public String getData() {
    return data;
  }

  public static void main(String[] args) {
    BoxString myString = new BoxString("cow");
    System.out.println(myString.getData());
  }
}
