import sdsu.edu.Cache;
import sdsu.edu.LRUCache;

public class TestDriver {


    /**
     * Default constructor for TestDriver class
     *
     * We primarily use this for testing out Cache
     */
    public TestDriver(){
        Cache lRUCache = new LRUCache(2);     // init cache of size 2

        // fill up Cache
        lRUCache.put(1, "SDSU");        // cache is {1="SDSU"}
        lRUCache.put(22, "UCSD");        // cache is {1="SDSU", 2="UCSD"}
        System.out.printf("%s\n",lRUCache.get(1));    // return "SDSU"

        // Test if our cache evicted the Least Recently Used
        lRUCache.put(33, "SJSU");      // LRU key was 2, evicts key 2, cache is {1="SDSU", 3="SJSU"}
        System.out.printf("%s\n",lRUCache.get(22));    // return (not found)

        // Test if our cache evicted the Least Recently Used
        lRUCache.put(44, "UCI");         // LRU key was 1, evicts key 1, cache is {4="UCI", 3="SJSU"}
        System.out.printf("%s\n",lRUCache.get(1));    // return -1 (not found)

        System.out.printf("%s\n",lRUCache.get(33));    // return "SJSU"
        System.out.printf("%s\n",lRUCache.get(44));    // return "UCI"


    }

    public static void main(String[] args) {
        new TestDriver();
    }
}
