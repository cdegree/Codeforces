package algorithms;

public class DisjointSet {
    int f[];

    DisjointSet(int n) {
        f = new int[n + 1];
        for (int i = 1; i <= n; ++i) {
            f[i] = i;
        }
    }

    public int find(int x) {
        return f[x] == x ? x : (f[x] = find(f[x]));
    }

    public void union(int x, int y) {
        f[find(y)] = find(x);
    }
}
