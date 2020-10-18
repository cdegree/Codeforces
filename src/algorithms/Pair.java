package algorithms;

public class Pair<K, V> implements Comparable<Pair> {
    public K x;
    public V y;

    public Pair(K x, V y) {
        this.x = x;
        this.y = y;
    }

    public Pair() {

    }


    @Override
    public int compareTo(Pair o) {
        if (compareNumber(x, o.x) != 0) {
            return compareNumber(x, o.x);
        } else {
            return compareNumber(y, o.y);
        }
    }

    private int compareNumber(Object o1, Object o2) {
        if (o1 instanceof Integer && o2 instanceof Integer) {
            return ((Integer) o1).compareTo((Integer) o2);
        } else if (o1 instanceof Long && o2 instanceof Long) {
            return ((Long) o1).compareTo((Long) o2);
        } else if (o1 instanceof Double && o2 instanceof Double) {
            return ((Double) o1).compareTo((Double) o2);
        } else {
            System.out.println("Unsupported Type");
            return 0;
        }
    }
}
