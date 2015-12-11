import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class CoinChange {

  public static int minCoinsSlow(int[] combinations, int cents) {
    if (cents == 0) {
      return 0;
    }

    if (cents == 1) {
      return 1;
    }

    List<Integer> candidates = new LinkedList<Integer>();

    for (int combination : combinations) {
      if (cents - combination >= 0) {
        int candidate = minCoinsSlow(combinations, cents - combination) + 1;
        candidates.add(candidate);
      }
    }

    return Collections.min(candidates).intValue();
  }

  public static int minCoins(int[] combinations, int cents) {
    int[] memory = new int[cents + 1];
    for (int i = 0; i < memory.length; i++) {
      memory[i] = Integer.MIN_VALUE;
    }
    minCoins(combinations, cents, memory);
    return memory[cents];
  }

  private static int minCoins(int[] combinations, int cents, int[] memory) {
    if (cents == 0) {
      memory[0] = 0;
      return 0;
    }
    if (cents == 1) {
      memory[1] = 1;
      return 1;
    }

    List<Integer> candidates = new LinkedList<>();

    for (int combination : combinations) {
      int index = cents - combination;
      if (index >= 0) {
        if (memory[index] == Integer.MIN_VALUE) {
          memory[index] = minCoins(combinations, cents - combination, memory) + 1;
        }
        candidates.add(memory[index]);
      }
    }

    memory[cents] = Collections.min(candidates).intValue();
    return memory[cents];
  }

  public static int minCoinsIterative(int[] combinations, int cents) {
    int[] memory = new int[cents + 1];
    memory[0] = 0;
    memory[1] = 1;

    for (int i = 1; i < cents + 1; i++) {
      int[] candidates = new int[combinations.length];
      for (int j = 0; j < combinations.length; j++) {
        if (i - combinations[j] >= 0) {
          candidates[j] = memory[i - combinations[j]] + 1;
        } else {
          candidates[j] = Integer.MAX_VALUE;
        }
      }
      int min = Integer.MAX_VALUE;
      for (int candidate : candidates) {
        if (candidate < min) {
          min = candidate;
        }
      }
      memory[i] = min;
      System.out.println(Arrays.toString(memory));
    }

    return memory[cents];
  }

  public static void main(String[] args) {
    int[] combinations = new int[] { 36, 25, 10, 5, 1 };
    System.out.println(minCoinsIterative(combinations, 10));
  }
}
