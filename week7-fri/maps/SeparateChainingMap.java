public class SeparateChainingMap<K, V> implements Map<K, V> {
  private LinkedList<Pair<K, V>>[] data;

  public SeparateChainingMap() {
    data = new LinkedList<Pair<K, V>>[137];
    for(int i = 0; i < 137; i++) {
      data[i] = new LinkedList<Pair<K, V>>();
    }
  }

  public void put(K key, V value) {
    int hashCode = key.hashCode();
    hashCode = hashCode < 0 ? hashCode * -1 : hashCode;
    int index = hashCode % data.length;

    LinkedList<Pair<K, V>> list = data[index];

    for(Pair<K, V> pair : list) {
      if(key.eqals(pair.key)) {
        pair.value = value;
        return;
      }
    }

    list.add(new Pair<K, V>(key, value));
  }

  public value get(K key) {
    int hashCode = key.hashCode();
    hashCode = hashCode < 0 ? hashCode * -1 : hashCode;
    int index = hashCode % data.length;

    LinkedList<Pair<K, V>> list = data[index];

    for(Pair<K, V> pair : list) {
      if(key.equals(pair.key)) {
        return value;
      }
    }

    return null;
  }
}
