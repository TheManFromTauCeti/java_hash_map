import java.util.ArrayList;
import java.util.Iterator;

/**
 * Represents a HashMap object. Implements Map Interface
 *
 * @param <K> A Generic Key
 * @param <V> A Generic Value
 */
public class HashMap<K, V> implements Map<K, V> {
    private static final int DEFAULT_CAPACITY = 11;
    private static final double DEFAULT_LOAD_FACTOR = 0.75;
    private final double loadFactor;
    private Entry<K, V>[] table;
    private int size;
    private int placeholderCount;

    /**
     * Initializes table and loadFactor to default size
     */
    public HashMap() {
        this(DEFAULT_CAPACITY, DEFAULT_LOAD_FACTOR);
    }

    /**
     * Initializes table to size passed and assigns loadFactor to default value
     *
     * @param initialCapacity the initial table size
     */
    public HashMap(int initialCapacity) {
        this(initialCapacity, DEFAULT_LOAD_FACTOR);
    }

    /**
     * Initializes table and loadFactor to values passed
     *
     * @param initialCapacity the initial table size
     * @param loadFactor      the initial loadFactor
     */
    public HashMap(int initialCapacity, double loadFactor) {
        if (loadFactor <= 0 || loadFactor > 1) throw new IllegalArgumentException();
        if (initialCapacity <= 0) throw new IllegalArgumentException();
        this.loadFactor = loadFactor;
        table = (Entry<K, V>[]) new Entry[initialCapacity];
    }

    /**
     * Returns the table array
     *
     * @return the table array
     */
    public Entry<K, V>[] getTable() {
        return table;
    }

    /**
     * Returns the current size (ignoring placeholders)
     *
     * @return the current size
     */
    public int size() {
        return size;
    }

    /**
     * Returns true if active entries in the table array is zero, otherwise false
     *
     * @return true if active entries in the table array is zero, otherwise false
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Clears an array and all placeholders
     */
    @Override
    public void clear() {
        table = (Entry<K, V>[]) new Entry[table.length];
        size = 0;
        placeholderCount = 0;

    }

    /**
     * Returns the next available bucket based on the key passed
     *
     * @param key the key for the bucket
     * @return the next available bucket
     */
    private int getMatchingOrNextAvailableBucket(K key) {
        int index = key.hashCode() % table.length;
        Entry<K, V> bucket = table[index];

        while (bucket != null && !key.equals(bucket.getKey())) {
            if (index == table.length) {
                index = 0;
            } else {
                index++;
            }
            bucket = table[index];
        }

        return index;
    }

    /**
     * Returns the value located at the bucket found
     *
     * @param key the key for the bucket
     * @return the value located at the bucket
     */
    @Override
    public V get(K key) {
        if (key == null) throw new NullPointerException();

        V value;

        int index = getMatchingOrNextAvailableBucket(key);

        Entry<K, V> entry = table[index];

        if (entry == null) {
            value = null;
        } else {
            value = entry.getValue();
        }

        return value;
    }

    /**
     * Adds or updates the bucket found by the key given
     *
     * @param key   the key for the bucket
     * @param value the new value for the bucket
     * @return the old value found in the bucket
     */
    @Override
    public V put(K key, V value) {
        if (key == null || value == null) throw new NullPointerException();

        V returnValue;

        //threshold = table.length * loadFactor
        if (size + placeholderCount + 1 >= (int) (table.length * loadFactor)) {
            reHash();
        }

        int index = getMatchingOrNextAvailableBucket(key);

        Entry<K, V> oldEntry = table[index];

        table[index] = new Entry<>(key, value);

        if (oldEntry == null) {
            size++;
            returnValue = null;
        } else {
            returnValue = oldEntry.getValue();
        }

        return returnValue;
    }

    /**
     * Removes the values at the bucket found if a value exists
     *
     * @param key the key for the bucket
     * @return the old value if it exists
     */
    @Override
    public V remove(K key) {
        if (key == null) throw new NullPointerException();

        V value;

        int index = getMatchingOrNextAvailableBucket(key);

        Entry<K, V> entry = table[index];

        if (entry != null) {
            table[index] = new Entry<>(entry.getKey(), null);
            placeholderCount++;
            size--;

            value = entry.getValue();
        } else {
            value = null;
        }

        return value;
    }

    /**
     * Determines if the potentially new size for the table array is a prime number
     *
     * @param number the potentially new size for the table array
     * @return true if number is prime, otherwise false
     */
    private boolean isPrime(int number) {
        int squareRootOfNumber = (int) Math.sqrt(number);

        for (int i = 3; i <= squareRootOfNumber; i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * Calculates a new size for the table array
     *
     * @return the new size for the table array
     */
    private int resize() {
        int newArraySize = table.length * 2 + 1;

        while (!isPrime(newArraySize)) {
            newArraySize = newArraySize + 2;
        }

        return newArraySize;
    }

    /**
     * Creates a new table array once the threshold has been reached
     */
    private void reHash() {
        Iterator<K> keys = keys();
        Iterator<V> values = values();

        int size = resize();

        clear();

        table = (Entry<K, V>[]) new Entry[size];

        while (keys.hasNext()) {
            put(keys.next(), values.next());
        }
    }

    /**
     * Returns an Iterator of the keys for the table array
     *
     * @return an Iterator of keys
     */
    @Override
    public Iterator<K> keys() {
        ArrayList<K> keyList = new ArrayList<>();

        for (Entry<K, V> entry : table) {
            if (entry != null) {
                if (entry.getValue() != null) {
                    keyList.add(entry.getKey());
                }
            }
        }

        return keyList.iterator();
    }

    /**
     * Returns an Iterator of the values for the table array
     *
     * @return an Iterator of values
     */
    @Override
    public Iterator<V> values() {
        ArrayList<V> valueList = new ArrayList<>();

        for (Entry<K, V> entry : table) {
            if (entry != null) {
                if (entry.getValue() != null) {
                    valueList.add(entry.getValue());
                }
            }
        }

        return valueList.iterator();
    }
}