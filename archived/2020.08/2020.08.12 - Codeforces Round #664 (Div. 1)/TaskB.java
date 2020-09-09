package current;

import fastio.InputReader;

import java.io.PrintWriter;
import java.io.IOException;
import java.util.*;


public class TaskB {
    TreeSet<Edge>[] adj;
    boolean[] vis;
    int[] lengthToRoot;
    boolean[][] outd;
    int n;

    public void solve(int testNumber, InputReader in, PrintWriter out) {
        n = in.nextInt();
        int m = in.nextInt();
        int k = in.nextInt();
        adj = new TreeSet[n + 1];
        vis = new boolean[n + 1];
        lengthToRoot = new int[n + 1];
        for (int i = 1; i <= n; ++i) {
            adj[i] = new TreeSet<>(Comparator.comparingInt(o -> o.w));
        }
        for (int i = 0; i < m; ++i) {
            int u = in.nextInt();
            int v = in.nextInt();
            int w = in.nextInt();
            adj[u].add(new Edge(v, w));
        }
        int[] next = new int[n + 1];
        int[] pre = new int[n + 1];
        for (int i = 1; i <= n; ++i) {
            if (adj[i].size() == 1) {
                next[i] = adj[i].first().v;
                pre[adj[i].first().v] = i;
            }
        }
        outd = new boolean[10][10];
        for (int i = 1; i < 10; ++i) {
            Arrays.fill(outd[i], true);
        }
        dfs(1, 1, 1);
        long res = 1;
        for (int i = 1; i <= k; ++i) {
            int cnt = 0;
            for (int j = 1; j <= i; ++j) {
                if (outd[i][j]) {
                    ++cnt;
                }
            }
            res *= cnt;
        }
        out.println(res);
    }

    void dfs(int u, int par, int depth) {
        vis[u] = true;
        int cnt = 0;
        for (Edge e : adj[u]) {
            ++cnt;
            if (!vis[e.v]) {
                if (lengthToRoot[e.v] + depth != n) {
                    dfs(e.v, u, depth + 1);
                    if (lengthToRoot[e.v] > 0) {
                        lengthToRoot[u] = lengthToRoot[e.v] + 1;
                    }
                    if (lengthToRoot[e.v] + depth != n) {
                        outd[adj[u].size()][cnt] = false;
                    }
                }
            } else if (e.v == 1) {
                if (depth == n) {
                    lengthToRoot[u] = 1;
                } else {
                    lengthToRoot[u] = 0;
                }
            }
        }
        vis[u] = false;
    }

    class Edge {
        int v;
        int w;

        public Edge(int v, int w) {
            this.v = v;
            this.w = w;
        }
    }
}
