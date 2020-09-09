package acl;

import java.util.*;

public class Sol {

    static int a = 2;

    static {
        System.out.println("Sol static block called");
    }

    {
        System.out.println("Sol block called");
    }

    public Sol() {
        B b = new B();
    }
    int[] getArrays(){
        return new int[]{1,2,3};
    }

    public static void main(String[] args) {
        // The comment below is magic..
        // \u000d System.out.println("Geek Comment Executed!");
        Sol sol = new Sol();
        String s = "09-03-2002";
        boolean ok = s.matches("\\d+\\D\\d+\\D\\d+");
        System.out.println(ok);
        int[] a;


        TreeMap<Integer, Integer> set = new TreeMap<>();
        set.put(1,1);
        set.put(1,1);
        set.put(1,1);
        List<List<String>> ret = Arrays.asList(new ArrayList[set.size()]);
        if(ret.get(0) == null) {
            ret.set(0, new ArrayList<>());
        }
    }

    void linkedList() {
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

    void treeSet() {
        TreeSet<Integer> set = new TreeSet<>();
        set.first();
        set.last();
        set.lower(12);
        set.floor(12);
        set.higher(12);
        set.ceiling(12);
        set.isEmpty();
    }

    void treeMap() {
        TreeMap<Integer, Integer> map = new TreeMap<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
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

    void dataStructure() {


    }

    static class S {
        static {
            System.out.println("S static block called");
        }

        {
            System.out.println("S block called");
        }
    }

    static class A {
        static {
            System.out.println("A static block called");
        }
        {
            System.out.println("A block called");
        }

        public A() {
            System.out.println("A Constructed");
        }
    }

    class B extends A {
        {
            System.out.println("B block called");
        }

        public B() {
            System.out.println("B Constructed");
        }
    }
}
