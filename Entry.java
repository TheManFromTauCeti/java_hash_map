/**
 * Represents an Entry object
 *
 * @param <K> A generic that will be used by StringKey
 * @param <V> A generic that will be used by Item
 */
public class Entry<K, V> {
    private final K key;
    private final V value;

    /**
     * Instantiates an entry object
     *
     * @param key   holds a generic object
     * @param value holds a generic object
     */
    public Entry(K key, V value) {
        this.key = key;
        this.value = value;
    }

    /**
     * returns the value
     *
     * @return the value
     */
    public V getValue() {
        return value;
    }

    /**
     * Returns the key
     *
     * @return the key
     */
    public K getKey() {
        return key;
    }

    /**
     * Honestly, it's a toString. I don't feel like I need to explain this
     *
     * @return a String representation of an Entry object
     */
    @Override
    public String toString() {
        return String.format("[" + key + " : " + value + "]");
    }
}
