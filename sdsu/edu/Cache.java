package sdsu.edu;

public interface Cache{

    /**
     * Update the value of the key if the key exists.
     * Otherwise, add the key-value pair to the cache.
     * If the number of keys exceeds the capacity from this operation, evict the least recently used key.
     * @param k: key
     * @param v: value
     */
    void put(int k, String v);


    /**
     * Return the value of the key if the key exists, otherwise return error message.
     * @param k : key we want to get
     * @return value corresponding to the key
     */
    String get(int k);

}
