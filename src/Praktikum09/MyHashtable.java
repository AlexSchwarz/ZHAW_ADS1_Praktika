package Praktikum09;

import java.lang.reflect.Array;
import java.util.*;

public class MyHashtable<K,V> implements java.util.Map<K,V> {
    private K[] keys;
    private V[] values;
    private int maxSize;
    private int size = 0;

    private int hash(Object k) {
        int h = Math.abs(k.hashCode());
        return h % keys.length;
    }

    public MyHashtable(int maxSize) {
        this.maxSize = maxSize;
        clear();
    }

    //  Removes all mappings from this map (optional operation).
    public void clear() {
        keys = (K[]) new Object[maxSize];
        values = (V[]) new Object[maxSize];
        size = 0;
    }

    //  Associates the specified value with the specified key in this map (optional operation).
    public V put(K key, V value) {
        int currentPosition = hash(key);

        // folie 29 detect collision and what to do when collision happens / lineare sondierung
        while(keys[currentPosition] != null && !keys[currentPosition].equals(key)) {
            currentPosition = hash(currentPosition + 1);
        }

        //size control
        if (size + 1 >= maxSize) {
            maxSize *= 2;
            values = Arrays.copyOf(values, maxSize);
            keys = Arrays.copyOf(keys, maxSize);

        }

        // wenn der key der gleiche ist wie die der index der current position,
        // dann setze an dieser position den value ins values array und key ins key array
        // einsetzen kann man hier immer, weil die collision detection schon stattgefunden hat.
        if(!key.equals(keys[currentPosition])) {
            values[currentPosition] = value;
            keys[currentPosition] = key;
            size++;
        }

        return value;
    }

    //  Returns the value to which this map maps the specified key.
    public V get(Object key) {
        int currentPosition = hash(key);

        while(keys[currentPosition] != null && !keys[currentPosition].equals(key)) {
            currentPosition = hash(currentPosition + 1);
        }

        if(keys[currentPosition] == null) {
            return null;
        } else {
            return values[currentPosition];
        }
    }

    //  Returns true if this map contains no key-value mappings.
    public boolean isEmpty() {
        return size() == 0;
    }

    //  Removes the mapping for this key from this map if present (optional operation).
    public V remove(Object key) {
        int currentPosition = hash(key);

        // collision detection
        if(key.equals(keys[currentPosition])) {
            K[] newKeys = Arrays.copyOf(keys, maxSize);
            V[] newValues = Arrays.copyOf(values, maxSize);
            clear();

            for(int i = 0; i < newKeys.length && i < newValues.length; i++) {
                if(!key.equals(newKeys[i]) && newKeys[i] != null) {
                    put(newKeys[i], newValues[i]);
                }
            }
            return newValues[currentPosition];
        }
        return null;
    }

    //  Returns the number of key-value mappings in this map.
    public int size() {
        return size;
    }

    // =======================================================================
    //  Returns a set view of the keys contained in this map.
    public Set keySet() {
        throw new UnsupportedOperationException();
    }

    //  Copies all of the mappings from the specified map to this map (optional operation).
    public void putAll(Map t) {
        throw new UnsupportedOperationException();
    }

    //  Returns a collection view of the values contained in this map.
    public Collection values() {
        throw new UnsupportedOperationException();
    }
    //  Returns true if this map contains a mapping for the specified key.
    public boolean containsKey(Object key) {
        throw new UnsupportedOperationException();
    }
    //  Returns true if this map maps one or more keys to the specified value.
    public boolean containsValue(Object value)  {
        throw new UnsupportedOperationException();
    }
    //  Returns a set view of the mappings contained in this map.
    public Set entrySet() {
        throw new UnsupportedOperationException();
    }
    //  Compares the specified object with this map for equality.
    public boolean equals(Object o) {
        throw new UnsupportedOperationException();
    }
    //  Returns the hash code value for this map.
    public int hashCode() {
        throw new UnsupportedOperationException();
    }
}
