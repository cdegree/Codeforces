package current;

import algorithms.Graph;
import fastio.InputReader;

import java.io.PrintWriter;
import java.io.IOException;
import java.util.*;


public class TaskE {

    int k;
    Graph graph;

    int[] order;
    int[] depth;
    int[] dis;
    int[] mp;
    int[][] fa;

    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        graph = new Graph(n, n * 2);

        order = new int[n + 1];
        depth = new int[n + 1];
        dis = new int[n + 1];
        mp = new int[n + 1];
        fa = new int[n + 1][20];

        for (int i = 0; i < n - 1; ++i) {
            int u = in.nextInt();
            int v = in.nextInt();
            int value = in.nextInt();
            graph.addEdge(u, v, value);
            graph.addEdge(v, u, value);
        }
        k = 0;
        depth[1] = 1;
        dfs(1);
        for (int i = 1; i <= n; ++i) {
//            out.println(String.format("dis[%d]=%d", i, dis[i]));
//            out.println(String.format("depth[%d]=%d", i, depth[i]));
        }
        int q = in.nextInt();
        TreeSet<Integer> set = new TreeSet<>();
        long res = 0;
        while (q-- > 0) {
            String s = in.next();
            char op = s.charAt(0);
            if (op == '?') {
                out.println(res);
            } else {
                int u = in.nextInt();
                int od = order[u];
                set.add(od);
                Integer pre = set.lower(od);
                if (pre == null) {
                    pre = set.last();
                }
                Integer next = set.higher(od);
                if (next == null) {
                    next = set.first();
                }
                int npre = mp[pre];
                int nnext = mp[next];
//                out.println(String.format("op = %s u = %d order =%d pre = %d next = %d", op, u, order[u], pre, next));
//                out.println(String.format("lca(%d, %d) = %d", npre, u, lca(npre, u, out)));
//                out.println(String.format("lca(%d, %d) = %d", u, nnext, lca(u, nnext, out)));
//                out.println(String.format("lca(%d, %d) = %d", npre, nnext, lca(npre, nnext, out)));
                long len = dis[u] - dis[lca(npre, u)] - dis[lca(u, nnext)] + dis[lca(npre, nnext)];
                if (op == '+') {
                    res += len;
                } else {
                    res -= len;
                    set.remove(u);
                }
                out.println("res = "+res);
            }
        }
        out.flush();
    }

    void dfs(int u) {
        order[u] = ++k;
        mp[k] = u;
        for (int i = 1; i < 19; ++i) {
            if (depth[u] >= (1 << i)) {
                fa[u][i] = fa[fa[u][i - 1]][i - 1];
            }
        }
        for (Graph.Edge edge : graph.getAdj()[u]) {
            int v = edge.v;
            if (v != fa[u][0]) {
                fa[v][0] = u;
                depth[v] = depth[u] + 1;
                dis[v] = dis[u] + edge.value;
                dfs(v);
            }
        }
    }

    int lca(int u, int v) {
        if (depth[u] < depth[v]) {
            int t=u;
            u=v;
            v=t;
        }
        for (int i = 18; i >= 0; --i) {
            if (depth[fa[u][i]] >= depth[v]) {
                u = fa[u][i];
            }
        }
        if (u == v) {
            return u;
        }
        for (int i = 18; i >= 0; --i) {
            if (fa[u][i] != fa[v][i]) {
                u = fa[u][i];
                v = fa[v][i];
            }
        }
        return fa[u][0];
    }

    int lca(int u, int v, PrintWriter out) {

        out.println(String.format("before u = %d, v = %d", u, v));
        if (depth[u] < depth[v]) {
            int t=u;
            u=v;
            v=t;
        }
        out.println(String.format("after u = %d, v = %d", u, v));
        for (int i = 18; i >= 0; --i) {
            if (depth[fa[u][i]] >= depth[v]) {
                u = fa[u][i];
            }
        }
        out.println("lca u =" + u);
        if (u == v) {
            return u;
        }
        for (int i = 18; i >= 0; --i) {
            if (fa[u][i] != fa[v][i]) {
                u = fa[u][i];
                v = fa[v][i];
            }
        }
        return fa[u][0];
    }
}
