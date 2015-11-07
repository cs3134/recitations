
public class MapTest {
  public static void main(String[] args) {
    Map<String, Integer> map = new ProbingMap<String, Integer>();
    map.put("pikachu", 1);
    map.put("charmander", 2);
    map.put("zapdos", 8);
    map.put("whatever", 2);
    map.put("hamdel", 3);
    map.put("johnjay", 5);
    map.put("again", 5);
    map.put("thiswillcrash", 4);

    System.out.println(map.get("charmander"));
    System.out.println(map.get("pikachu"));
  }
}
