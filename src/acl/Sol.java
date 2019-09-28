package acl;

import java.util.*;

public class Sol {

    public static void main(String[] args){

    }

    void linkedList(){
        LinkedList<Integer> linkedList = new LinkedList<>();
        linkedList.add(1);
        linkedList.addLast(2);
        linkedList.addFirst(3);
        linkedList.addAll(new ArrayList<>());
        linkedList.removeFirst();
        linkedList.removeLast();
        linkedList.getFirst();
        linkedList.getLast();
        Queue<Integer> q = new LinkedList<>();

    }

    void treeSet(){
        TreeSet<Integer> set = new TreeSet<>();
        set.first();
        set.last();
        set.lower(12);
        set.floor(12);
        set.higher(12);
        set.ceiling(12);
        set.isEmpty();
    }

    void treeMap(){
        TreeMap<Integer, Integer> map = new TreeMap<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1-o2;
            }
        });
        map.keySet();
        map.values();
        map.firstKey();
        map.lastKey();
        map.firstEntry();
        map.lastEntry();
        map.containsKey(12);
        map.lowerKey(12); // less than
        map.floorKey(12); // less than or equals to

        map.higherKey(12); // larger than
        map.ceilingKey(12); // larger than or equals to

    }

    void dataStructure(){


    }
}
