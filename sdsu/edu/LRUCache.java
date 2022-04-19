package sdsu.edu;

import java.util.HashMap;

public class LRUCache implements Cache{
    private class Node {
        Node next;
        Node prev;
        int key;
        String val;

        public Node(){ }
        public Node(int key, String val, Node next, Node prev){
            this.key = key;
            this.val = val;
            this.next = next;
            this.prev = prev;
        }

        public void updateVal(String val){
            this.val = val;
        }
    }


    private final HashMap<Integer, Node> cache; // this will act as our container for our cache
    private final Node tail;                    // dummy tail
    private final Node head;                    // dummy head
    private final int capacity;                 // defines the max capacity of our Cache
    private int size;                           // contains current size of our cache

    public LRUCache(int capacity) {
        // init cap and size
        this.capacity   = capacity;
        this.size       = 0;
        this.cache      = new HashMap<>();

        // init doubly linked-list
        head        = new Node();
        tail        = new Node();
        head.next   = tail;
        tail.prev   = head;
    }

    /**
     * Return the value of the key if the key exists, otherwise return -1.
     * @param key : key we want to get
     * @return value corresponding to the key
     */
    public String get(int key) {
        if(this.cache.containsKey(key)){
            Node curr = this.cache.get(key);    // grab the node
            moveToFront(curr);                  // move the node to the front of the list
            return curr.val;
        }

        return "Error: key is not in cache"; // if we don't have the key in our map
    }

    /**
     * Update the value of the key if the key exists.
     * Otherwise, add the key-value pair to the cache.
     * If the number of keys exceeds the capacity from this operation, evict the least recently used key.
     * @param key: key
     * @param value: value
     */
    public void put(int key, String value) {
        // check if the value we insert is in map
        if(this.cache.containsKey(key)){
            //update the node with the value since it's already in map
            Node curr = this.cache.get(key);
            curr.updateVal(value);
            moveToFront(curr);
        }
        else{
            //evict the LRU to make space for the next guy
            if(size == capacity) deleteLRU();

            // Insert new node at the front of the doubly LL
            Node newNode        = new Node(key,value, this.head.next, this.head);
            this.head.next.prev = newNode;
            this.head.next      = newNode;

            this.cache.put(key, newNode); // add our k:v to the hashtable
            size++;
        }
    }

    /**
     * Helper function with logic to delete least recently used data
     */
    private void deleteLRU(){
        Node toRemove       = tail.prev;
        tail.prev           = toRemove.prev;
        toRemove.prev.next  = tail;
        this.cache.remove(toRemove.key);
        size--;
    }

    /**
     * Helper function with logic to move node to the front of the list
     * @param curr : the node we wish to move to the front
     */
    private void moveToFront(Node curr){
        curr.next.prev  = curr.prev;
        curr.prev.next  = curr.next;
        curr.next       = head.next;
        curr.prev       = head;
        head.next.prev  = curr;
        head.next       = curr;
    }
}
