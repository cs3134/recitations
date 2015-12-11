import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Memoization {
  public static int minimumCoin(int[] combinations, int x) {
    int[] memory = new int[x + 1];
    for (int i = 0; i < memory.length; i++) {
      memory[i] = Integer.MIN_VALUE;
    }
    minimumCoin(combinations, x, memory);
    return memory[x];
  }

  private static void minimumCoin(int[] combinations, int x, int[] memory) {
    if (x == 0) {
      memory[0] = 0;
      return;
    }

    if (x == 1) {
      memory[1] = 1;
      return;
    }

    List<Integer> costs = new LinkedList<Integer>();

    for (int combination : combinations) {
      if (x - combination >= 0) {
        if (memory[x - combination] == Integer.MIN_VALUE) {
          minimumCoin(combinations, x - combination, memory);
        }
        costs.add(memory[x - combination] + 1);
      }
    }

    memory[x] = Collections.min(costs).intValue();
  }

  public static void main(String[] args) {
    int[] combinations = new int[] { 35, 25, 15, 10, 5, 1 };
    System.out.println(minimumCoin(combinations, 150));
  }
}
