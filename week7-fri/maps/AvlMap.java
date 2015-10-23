/**
 * A Map backed by a AvlTree
 * 
 * @author linanqiu
 * @file_name AvlMap.java
 * @param <K>
 *          key
 * @param <V>
 *          value
 */
public class AvlMap<K extends Comparable<? super K>, V> implements Map<K, V> {

  private AvlTree<Pair<K, V>> tree;

  /**
   * Constructs a new AvlMap with an empty tree
   */
  public AvlMap() {
    tree = new AvlTree<>();
  }

  @Override
  public void put(K key, V value) {
    Pair<K, V> pair = new Pair<>(key, value);
    // i modified AvlTree's insert to overwrite duplicates
    tree.insert(pair);
  }

  @Override
  public V get(K key) {
    Pair<K, V> pair = new Pair<>(key, null);
    // I wrote a get method for AvlTree
    Pair<K, V> getPair = tree.get(pair);
    return getPair != null ? getPair.value : null;
  }
}
