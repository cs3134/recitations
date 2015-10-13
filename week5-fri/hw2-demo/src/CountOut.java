import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class CountOut {

  public static List<Integer> play(int n, int k) {

    Queue<Integer> remainingPlayers = new LinkedList<>();

    List<Integer> outPlayers = new LinkedList<>();

    for (int i = 0; i < n; i++) {
      remainingPlayers.add(i);
    }

    while (remainingPlayers.size() > 0) {
      for (int i = 0; i < (k - 1); i++) {
        remainingPlayers.add(remainingPlayers.remove());
      }
      outPlayers.add(remainingPlayers.remove());
    }
    return outPlayers;
  }

  public static Integer findWinner(int n, int k) {
    return play(n, k).get(n - 1);
  }

  public static Integer findWinnerRec(int n, int k) {
    if (n == 0) {
      return 0;
    }
    return (findWinnerRec(n - 1, k) + k) % n;
  }

  public static void main(String[] args) {
    System.out.println(play(9, 4));
    System.out.println(findWinner(9, 4));
    System.out.println(findWinnerRec(9, 4));
  }
}
