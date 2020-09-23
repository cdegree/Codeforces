import java.util.Comparator;
import java.util.LinkedList;
import java.util.Map;
import java.util.TreeMap;


/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * <p>
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
public class Solve {
    public static void main(String[] args) {
        LRUCache lruCache = new LRUCache(3);
        lruCache.set(1, 1);
        lruCache.pf();
        lruCache.set(2, 2);
        lruCache.pf();
        lruCache.set(3, 3);
        lruCache.pf();
        lruCache.set(4, 4);
        lruCache.pf();
        lruCache.set(4, 8);
        lruCache.pf();
        lruCache.set(2, 4);
        lruCache.pf();

        System.out.println(String.format("%d", lruCache.get(1)));
        lruCache.pf();
        System.out.println(String.format("%d", lruCache.get(2)));
        lruCache.pf();
        System.out.println(String.format("%d", lruCache.get(3)));
        lruCache.pf();
        System.out.println(String.format("%d", lruCache.get(4)));
        lruCache.pf();
        System.out.println(String.format("%d", lruCache.get(1)));
        lruCache.pf();
        System.out.println(String.format("%d", lruCache.get(3)));
        lruCache.pf();
    }

    static class LRUCache {

        int capacity;
        Map<Integer, Element> cache;
        LinkList rank;

        public LRUCache(int capacity) {
            this.capacity = capacity;
            cache = new TreeMap<>(Comparator.comparingInt(o -> o));
            rank = new LinkList();
        }
        public int get(int key) {
            if (cache.containsKey(key)) {
                Element element = cache.get(key);
                rank.remove(element);
                rank.addFirst(element);
                return element.value;
            } else {
                return -1;
            }
        }

        void pf() {
            Element element = rank.head;
            for (int i = 0; i < cache.size(); ++i) {
                System.out.print(" list " + element.key + " " + element.value + "\n");
                element = element.next;
            }
            System.out.println("  size = " + this.cache.size());
        }

        public void set(int key, int value) {
            if (cache.size() == this.capacity && !cache.containsKey(key)) {
                Element leastRecentUsedItem = rank.getTail();
                //System.out.println("leastRecentUsedItem = " + leastRecentUsedItem.key);
                rank.remove(leastRecentUsedItem);
                cache.remove(leastRecentUsedItem.key);
                leastRecentUsedItem = null;
                Element element = new Element(key, value);
                rank.addFirst(element);
                cache.put(key, element);
            } else {
                if (cache.containsKey(key)) {
                    Element element = cache.get(key);
                    element.value = value;
                    rank.remove(element);
                    rank.addFirst(element);
                    cache.put(key, element);
                } else {
                    Element element = new Element(key, value);
                    rank.addFirst(element);
                    cache.put(key, element);
                }
            }
        }

        class Element {
            int key;
            int value;
            Element next = null;
            Element pre = null;

            public Element(int key, int value) {
                this.key = key;
                this.value = value;
            }
        }

        class LinkList {
            Element head = null;
            Element tail = null;

            public void addFirst(Element element) {
                if (head == null) {
                    head = element;
                    tail = element;
                } else {
                    element.next = head;
                    head.pre = element;
                    head = element;
                }
            }

            public void remove(Element element) {
                if (element == head) {
                    head = head.next;
                } else if (element == tail) {
                    tail = tail.pre;
                } else {
                    element.pre.next = element.next;
                    element.next.pre = element.pre;
                }
            }

            public Element getTail() {
                return tail;
            }
        }
    }

    class Node {
        int x;
        int y;
    }
}