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
    class  Node{
        int x;
        int y;
    }
    public static void main(String[] args) {
        TreeSet<Node> set = new TreeSet<>(new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return 0;
            }
        });
        List<Integer> a = new ArrayList<>();
        TreeMap<Integer,Integer> map = new TreeMap<>();
        Map.Entry<Integer,Integer> it = map.ceilingEntry(12);
        map.firstKey();
        LinkedList<Integer> aa = new LinkedList<>();
        List<List<Integer>> b = new LinkedList();
        ((LinkedList<List<Integer>>) b).getLast().addAll(new LinkedList<>());
        int n = 10;
        for(int i=0;i<n;++i) {
            String s = Integer.toBinaryString(i);
            StringBuffer sb = new StringBuffer();
            System.out.println(s);
        }


        Collections.sort(a, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return 0;
            }
        });
        LinkedHashMap<Integer,Integer> mp = new LinkedHashMap<>();
    }
    int solve(int n ){
        int pos=0;
        ArrayList<Integer> list = new ArrayList<Integer>();
        while(n>0){
            list.add(n%10);
            n/=10;
        }
        return 1;
    }
}