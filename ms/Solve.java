import java.util.*;


/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 *
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
public class Solve {
    static class LRUCache {

        int capacity;
        Map<Integer,Integer> cache;
        LinkedList<Integer> rank;

        public LRUCache(int capacity) {
            this.capacity = capacity;
            cache = new HashMap<>();
            rank = new LinkedList<>();
        }

        public int get(int key) {
            if(cache.containsKey(key)){
                rank.remove( (Object)key );
                rank.addFirst(key);
                return cache.get(key);
            }
            else{
                return -1;
            }
        }
        void pf(){
            for(int i=0;i<rank.size();++i){
                System.out.print(rank.get(i)+" ");
            }
            System.out.println("  capacity = "+this.capacity);
        }

        public void set(int key, int value) {
            if(cache.size() == this.capacity){
                if(cache.containsKey(key)){
                    rank.remove( (Object)key );
                    rank.addFirst(key);
                    cache.put(key, value);
                }
                else{
                    int leastRecentUsedItem = rank.pollLast();
                    rank.addFirst(key);
                    System.out.println("leastRecentUsedItem = "+ leastRecentUsedItem);
                    cache.remove(leastRecentUsedItem);
                    cache.put(key, value);
                }
            }
            else{
                if(cache.containsKey(key)){
                    rank.remove( (Object)key );
                    rank.addFirst(key);
                    cache.put(key, value);
                }
                else{
                    rank.addFirst(key);
                    cache.put(key, value);
                }
            }
        }
    }
    public static void main(String[] args) {
        LRUCache lru = new LRUCache(2);
        lru.set(2, 1);
        lru.pf();
        lru.set(1, 1);
        lru.pf();
        lru.get(2);
        lru.pf();
        lru.set(4, 1);
        lru.pf();
        lru.get(1);
        lru.get(2);
        lru.pf();
        lru.pf();
        ArrayList<Integer> a = new ArrayList<>();

        Collections.sort(a, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return 0;
            }
        });
        LinkedHashMap<Integer,Integer> map = new LinkedHashMap<>();
    }
}