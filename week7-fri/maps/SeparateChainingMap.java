import java.util.LinkedList;

/**
 * Separate chaining hash map
 * 
 * @author linanqiu
 * @file_name SeparateChainingMap.java
 * @param <K>
 *          key
 * @param <V>
 *          value
 */
public class SeparateChainingMap<K extends Comparable<? super K>, V> implements
    Map<K, V> {

  private LinkedList<Pair<K, V>> table[];
  private double maxLoadFactor = 1;
  private int size = 0;

  public static final int SCALE_FACTOR = 2; // by how much should the size be
                                            // increased each time
  public static final int INITIAL_SIZE = 10;

  @SuppressWarnings("unchecked")
  public SeparateChainingMap() {
    table = (LinkedList<Pair<K, V>>[]) new LinkedList[INITIAL_SIZE];
    for (int i = 0; i < table.length; i++) {
      table[i] = new LinkedList<>();
    }
  }

  @Override
  public void put(K key, V value) {
    put(table, key, value);
    size++;
    resize();
  }

  @Override
  public V get(K key) {
    return get(table, key);
  }

  /**
   * Puts a key value pair into a table (not necessarily the class variable
   * table)
   * 
   * @param table
   *          a table, array of linked lists of pairs
   * @param key
   *          key
   * @param value
   *          value
   */
  private void put(LinkedList<Pair<K, V>>[] table, K key, V value) {
    int hash = (key.hashCode() % table.length + table.length) % table.length;
    for (Pair<K, V> pair : table[hash]) {
      if (pair.key.equals(key)) {
        pair.value = value;
        return;
      }
    }
    Pair<K, V> pair = new Pair<>(key, value);
    table[hash].add(pair);
  }

  /**
   * Gets a value from a table (not necessarily the class variable table)
   * 
   * @param table
   *          an array of linked list of pairs
   * @param key
   *          key
   * @return value associated with key
   */
  public V get(LinkedList<Pair<K, V>>[] table, K key) {
    int hash = (key.hashCode() % table.length + table.length) % table.length;
    for (Pair<K, V> pair : table[hash]) {
      if (pair.key.equals(key)) {
        return pair.value;
      }
    }
    return null;
  }

  /**
   * Resizes the class variable table if necessary
   */
  @SuppressWarnings("unchecked")
  private void resize() {
    if (size >= maxLoadFactor * table.length) {
      LinkedList<Pair<K, V>>[] newTable = (LinkedList<Pair<K, V>>[]) new LinkedList[table.length * 2];
      for (int i = 0; i < newTable.length; i++) {
        newTable[i] = new LinkedList<>();
      }

      // rehash
      for (LinkedList<Pair<K, V>> chain : table) {
        for (Pair<K, V> pair : chain) {
          put(newTable, pair.key, pair.value);
        }
      }

      this.table = newTable;
    }
  }
}
