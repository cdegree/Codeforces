package algorithms;

public class Pair implements Comparable<Pair> {
    public int x;
    public int y;

    public Pair(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Pair() {
    }

    @Override
    public int compareTo(Pair o) {
        if (o.x == x) return y - o.y;
        return x - o.x;
    }
}
