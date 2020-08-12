package current;

import fastio.InputReader;

import java.io.PrintWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Vector;


public class TaskE {
    Vector<Integer>[] adj;
    int[] level;
    Vector<Pair> pairs;
    int[] depth;
    int[] par;
    int n;
    Vector<Integer> path;
    boolean[] visited;

    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int T = in.nextInt();
        while (T-- > 0) {
            n = in.nextInt();
            int m = in.nextInt();
            depth = new int[n + 1];
            adj = new Vector[n + 1];
            visited = new boolean[n + 1];
            level = new int[n + 1];
            Arrays.fill(level, -1);
            par = new int[n + 1];
            for (int i = 1; i <= n; ++i) {
                adj[i] = new Vector<>();
            }
            for (int i = 0; i < m; ++i) {
                int u = in.nextInt();
                int v = in.nextInt();
                adj[u].add(v);
                adj[v].add(u);
            }
            pairs = new Vector<>();
            path = new Vector<>();
            depth[1] = 1;
            par[1] = -1;
            dfs(1, 1);
            if (path.size() >= (n + 1) / 2) {
                out.println("PATH");
                out.println(path.size());
                for (int x : path) {
                    out.print(x + " ");
                }
                out.println();
            } else {
                out.println("PAIRING");
                out.println(pairs.size());
                for (Pair pair : pairs) {
                    out.println(pair.x + " " + pair.y);
                }
            }
        }
    }

    void dfs(int u, int pre) {
        if (depth[u] >= (n + 1) / 2) {
            if (path.size() == 0) {
                path.add(u);
                int v = par[u];
                while (v != -1) {
                    path.add(v);
                    v = par[v];
                }
            }
            return;
        }
        visited[u] = true;
        if (level[depth[u]] == -1) {
            level[depth[u]] = u;
        } else {
            pairs.add(new Pair(u, level[depth[u]]));
            level[depth[u]] = -1;
        }
        for (int v : adj[u]) {
            if (v == pre || visited[v]) continue;
            par[v] = u;
            depth[v] = depth[u] + 1;
            dfs(v, u);
        }
    }

    class Pair implements Comparable<Pair> {
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
}
