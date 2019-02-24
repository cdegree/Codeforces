package current;

import fastio.InputReader;

import java.io.PrintWriter;
import java.util.Vector;


public class TaskD {
    boolean OK = true;

    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();

        Vector<Integer>[] adj = new Vector[n];
        for (int i = 0; i < n; ++i) {
            adj[i] = new Vector<>();
        }
        long[] s = new long[n];
        long[] a = new long[n];
        int[] fa = new int[n];
        for (int i = 1; i < n; ++i) {
            fa[i] = in.nextInt() - 1;
            adj[fa[i]].add(i);
        }
        for (int i = 0; i < n; ++i) {
            s[i] = in.nextInt();
        }
        a[0] = s[0];
        dfs(0, 0, 0, adj, s, a, 1);
        if (OK) {
            long sum = 0;
            for (int i = 0; i < n; ++i) {
                sum += a[i];
                //out.println(String.format("a[%d] = %d", i, a[i]));
            }
            out.println(sum);
        } else {
            out.println(-1);
        }
    }

    void dfs(int gfa, int fa, int cur, Vector<Integer>[] adj, long[] s, long[] a, int depth) {
        int scnt = 0;
        long mn = 10000000000L;
        for (int i = 0; i < adj[cur].size(); ++i) {
            int son = adj[cur].get(i);
            if (son != fa) {
                ++scnt;
                mn = Math.min(mn, s[son]);
                dfs(fa, cur, son, adj, s, a, depth + 1);
            }
        }
        if (depth % 2 == 0) {
            if (scnt == 0) {
                a[cur] = 0;
            } else {
                if (mn < s[fa]) {
                    OK = false;
                } else {
                    a[cur] = mn - s[fa];
                    for (int i = 0; i < adj[cur].size(); ++i) {
                        int son = adj[cur].get(i);
                        if (son != fa) {
                            a[son] = s[son] - s[fa] - a[cur];
                        }
                    }
                }
            }
        }
    }
}
