import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class MergeSort {

  /**
   * Internal method that merges two sorted halves of a subarray (from Weiss
   * Data Structures and Algorithm Analysis in Java)
   * 
   * @param a
   *          an array of Comparable items.
   * @param tmpArray
   *          an array to place the merged result.
   * @param leftPos
   *          the left-most index of the subarray.
   * @param rightPos
   *          the index of the start of the second half.
   * @param rightEnd
   *          the right-most index of the subarray.
   */
  private static void merge(Integer[] a, Integer[] tmpArray, int leftPos, int rightPos, int rightEnd) {
    int leftEnd = rightPos - 1;
    int tmpPos = leftPos;
    int numElements = rightEnd - leftPos + 1;

    // Main loop
    while (leftPos <= leftEnd && rightPos <= rightEnd) {
      if (a[leftPos] <= a[rightPos]) {
        tmpArray[tmpPos++] = a[leftPos++];
      } else {
        tmpArray[tmpPos++] = a[rightPos++];
      }
    }

    while (leftPos <= leftEnd) { // Copy rest of first half
      tmpArray[tmpPos++] = a[leftPos++];
    }

    while (rightPos <= rightEnd) { // Copy rest of right half
      tmpArray[tmpPos++] = a[rightPos++];
    }

    // Copy tmpArray back
    for (int i = 0; i < numElements; i++, rightEnd--) {
      a[rightEnd] = tmpArray[rightEnd];
    }
  }

  /**
   * Merge Sort algorithm. This is the Merge Sort algorithm from from Weiss,
   * Data Structures and Algorithm Analysis in Java, as presented in class.
   * 
   * @param a
   *          an array of Comparable items.
   */
  public static void mergeSort(Integer[] a) {
    Integer[] tmpArray = new Integer[a.length];
    mergeSort(a, tmpArray, 0, a.length - 1);
  }

  /**
   * Internal method that makes recursive calls. This is part of the MergeSort
   * algorithm from from Weiss, Data Structures and Algorithm Analysis in Java,
   * as presented in class.
   * 
   * @param a
   *          an array of Comparable items.
   * @param tmpArray
   *          an array to place the merged result.
   * @param left
   *          the left-most index of the subarray.
   * @param right
   *          the right-most index of the subarray.
   */
  private static void mergeSort(Integer[] a, Integer[] tmpArray, int left, int right) {
    if (left < right) {
      int center = (left + right) / 2;
      mergeSort(a, tmpArray, left, center);
      mergeSort(a, tmpArray, center + 1, right);
      merge(a, tmpArray, left, center + 1, right);
    }
  }

  /**
   * Problem 5: Iterative Bottom-up Merge Sort
   */
  public static void mergeSortB(Integer[] inputArray) {

    Integer[] tempArray = new Integer[inputArray.length];

    for (int width = 1; width < inputArray.length; width *= 2) {
      for (int leftPos = 0; leftPos < inputArray.length; leftPos += 2 * width) {
        int rightPos = Math.min(leftPos + width, inputArray.length - 1);
        int rightEnd = Math.min(leftPos + 2 * width - 1, inputArray.length - 1);

        System.out.println("l: " + leftPos + " r: " + rightPos + " re: " + rightEnd);
        merge(inputArray, tempArray, leftPos, rightPos, rightEnd);
      }
    }

    return;
  }

  /**
   * Problem 6: Merge Sort for Lists, Without Side Effects
   */
  public static List<Integer> sortList(List<Integer> inputList) {
    if (inputList.size() == 1) {
      return inputList;
    } else {
      int splitPoint = inputList.size() / 2;

      List<Integer> leftList = inputList.subList(0, splitPoint);
      List<Integer> rightList = inputList.subList(splitPoint, inputList.size());

      return mergeLists(sortList(leftList), sortList(rightList));
    }
  }

  /**
   * New merge method that merges two lists and returns a new list. Use this
   * method to implement sortList.
   */
  public static List<Integer> mergeLists(List<Integer> left, List<Integer> right) {
    List<Integer> merged = new LinkedList<Integer>();

    Iterator<Integer> leftIte = left.iterator();
    Iterator<Integer> rightIte = right.iterator();

    Integer leftInt = leftIte.hasNext() ? leftIte.next() : null;
    Integer rightInt = rightIte.hasNext() ? rightIte.next() : null;

    while (leftInt != null && rightInt != null) {
      if (leftInt < rightInt) {
        merged.add(leftInt);
        leftInt = leftIte.hasNext() ? leftIte.next() : null;
      } else {
        merged.add(rightInt);
        rightInt = rightIte.hasNext() ? rightIte.next() : null;
      }
    }

    if (leftInt == null) {
      merged.add(rightInt);
      while (rightIte.hasNext()) {
        merged.add(rightIte.next());
      }
    }

    if (rightInt == null) {
      merged.add(leftInt);
      while (leftIte.hasNext()) {
        merged.add(leftIte.next());
      }
    }

    return merged;
  }

  public static void main(String[] args) {
    // // Weiss sort
    // Integer[] a = { 1, 4, 9, 131, 0, 2, 7, 19, 18 };
    // MergeSort.mergeSortB(a);
    // System.out.println(Arrays.toString(a)); // Should be [0, 1, 2, 4, 7, 9,
    // 18,
    // // 19, 131, 245]

    Integer[] left = { 1 };
    Integer[] right = { -1 };
    System.out.println(MergeSort.mergeLists(Arrays.asList(left), Arrays.asList(right)));
  }

}