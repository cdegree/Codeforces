package algorithms;

public class DisjointSet {
    private int[] set;
    private int[] size;

    public DisjointSet(int n) {
        set = new int[n + 1];
        size = new int[n + 1];
        for (int i = 1; i <= n; ++i) {
            set[i] = i;
            size[i] = 1;
        }
    }

    public static void main(String[] args) {
        DisjointSet ds = new DisjointSet(5);
        ds.union(1, 2);pf(ds);
        ds.union(2,3);pf(ds);
        ds.union(4,5);pf(ds);
        ds.union(1,4);pf(ds);
    }
    private static void pf(DisjointSet ds){
        System.out.println("********************");
        System.out.println(ds.getSize(1));
        System.out.println(ds.getSize(2));
        System.out.println(ds.getSize(3));
        System.out.println(ds.getSize(4));
        System.out.println(ds.getSize(5));
        System.out.println("********************");
    }

    public int find(int x) {
        return set[x] == x ? x : (set[x] = find(set[x]));
    }

    public void union(int x, int y) {
        int sx = find(x);
        int sy = find(y);
        if (sx != sy) {
            set[sy] = sx;
            size[sx] += size[sy];
        }
    }

    public int getSize(int x) {
        return size[find(x)];
    }
}
