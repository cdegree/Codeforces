package current;

import algorithms.Graph;
import fastio.InputReader;

import java.io.PrintWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Vector;


public class TaskD {
    final int N = 100005;
    int[] depth = new int[N];
    int[] vis = new int[N];
    Graph g;
    int T = 1;
    int[] par = new int[N];
    int k;

    Vector<Integer>[] color = new Vector[2];
    Vector<Integer> res = new Vector<>();

    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        k = in.nextInt();
        color[0] = new Vector<Integer>();
        color[1] = new Vector<Integer>();
        g = new Graph(n);
        for (int i = 0; i < m; ++i) {
            int u = in.nextInt();
            int v = in.nextInt();
            g.addEdge(u, v);
        }
        int Q = (k + 1) / 2;
        Arrays.fill(vis, 1 << 30);
        dfs(0, 1, 1);
        if (!res.isEmpty() && res.size() <= k) {
            out.println(2);
            out.println(res.size());
            for (int x : res) {
                out.print(x + " ");
            }
        } else {
            out.println(1);
            Vector<Integer> iSet = color[0].size() > color[1].size() ? color[0] : color[1];
            //out.println(String.format("%d : %d", color[0].size(), color[1].size()));
            for (int i = 0; i < Q && i < iSet.size(); ++i) {
                out.print(iSet.get(i) + " ");
            }
        }
    }

    void dfs(int pre, int cur, int depth) {
        if (!res.isEmpty() && res.size() <= k) {
            return;
        }
        vis[cur] = depth;
        color[depth % 2].add(cur);
        par[cur] = pre;
        for (Graph.Edge e : g.adj[cur]) {
            int v = e.v;
            if (v == pre) continue;
            if (vis[v] == 1 << 30) {
                dfs(cur, v, depth + 1);
            } else if (vis[cur] - vis[v] <= k && vis[cur] > vis[v]) {
                int root = cur;
                //System.out.println("v = " + v);
                if (!res.isEmpty() && res.size() <= k) {
                    return;
                }
                res.add(v);
                while (root != v) {
                    res.add(root);
                    //System.out.println(String.format("parent %d = %d", root, par[root]));
                    root = par[root];
                }
                if (res.size() <= k) {
                    //System.out.println("OKK");
                    return;
                } else {
                    res.clear();
                }
            }
        }
    }
}
