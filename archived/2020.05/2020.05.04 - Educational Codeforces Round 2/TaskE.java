package current;

import algorithms.Graph;
import fastio.InputReader;

import java.io.PrintWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.TreeMap;


public class TaskE {
    Graph graph;
    TreeMap<Integer, Integer>[] occurs;
    int[] c;
    long[] mostOccurs;
    long[] res;

    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        c = in.nextIntArray1(n);
        occurs = new TreeMap[n + 1];
        mostOccurs = new long[n + 1];
        res = new long[n + 1];
        graph = new Graph(n);
        for (int i = 1; i < n; ++i) {
            int u = in.nextInt();
            int v = in.nextInt();
            graph.addEdge(u, v);
        }
        search(1, 1);
        for (int i = 1; i <= n; ++i) {
            out.print(res[i] + " ");
        }
    }

    void search(int cur, int pre) {
        occurs[cur] = new TreeMap<>();
        occurs[cur].put(c[cur], 1);
        mostOccurs[cur] = 1;
        res[cur] = c[cur];
        for (Graph.Edge e : graph.adj[cur]) {
            int v = e.v;
            if (v != pre) {
                search(v, cur);
                if (occurs[cur].size() < occurs[v].size()) {
                    TreeMap<Integer, Integer> temp = occurs[cur];
                    occurs[cur] = occurs[v];
                    occurs[v] = temp;
                    res[cur] = res[v];
                    mostOccurs[cur] = mostOccurs[v];
                }
                for (int color : occurs[v].keySet()) {
                    int count = occurs[v].get(color);
                    occurs[cur].put(color, occurs[cur].getOrDefault(color, 0) + count);
                    if (occurs[cur].get(color) > mostOccurs[cur]) {
                        mostOccurs[cur] = occurs[cur].get(color);
                        res[cur] = 0;
                    }
                    if (occurs[cur].get(color) == mostOccurs[cur]) {
                        res[cur] += color;
                    }
                }
            }
        }
    }
}
