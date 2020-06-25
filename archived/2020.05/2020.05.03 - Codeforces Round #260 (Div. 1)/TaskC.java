package current;

import algorithms.DisjointSet;
import algorithms.Graph;
import fastio.InputReader;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Vector;


public class TaskC {
    int lowestDepth = 0;
    int lowestNode = 0;
    Graph graph = null;
    boolean[] visited = null;

    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        int q = in.nextInt();
        DisjointSet ds = new DisjointSet(n);
        int[] length = new int[n + 1];
        graph = new Graph(n);
        for (int i = 0; i < m; ++i) {
            int a = in.nextInt();
            int b = in.nextInt();
            graph.addEdge(a, b);
        }
        visited = new boolean[n + 1];
        for (int i = 1; i <= n; ++i) {
            if (!visited[i]) {
                Vector<Integer> regionNodes = new Vector<>();
                lowestNode = i;
                lowestDepth = 0;
                search(i, regionNodes, 0);
                for (int v : regionNodes) {
                    visited[v] = false;
                }
                search(lowestNode, 0);
                for (int j = 0; j < regionNodes.size(); ++j) {
                    if (j > 0) {
                        ds.union(regionNodes.get(j), regionNodes.get(j - 1));
                    }
                    length[ds.find(regionNodes.get(j))] = lowestDepth;
                }
            }
        }
        while (q-- > 0) {
            int t = in.nextInt();
            if (t == 1) {
                int x = in.nextInt();
                out.println(length[ds.find(x)]);
            } else {
                int a = in.nextInt();
                int b = in.nextInt();
                int regionA = ds.find(a);
                int regionB = ds.find(b);
                if (regionA != regionB) {
                    int mergedLength = (length[regionA] + 1) / 2 + (length[regionB] + 1) / 2 + 1;
                    mergedLength = Math.max(mergedLength, length[regionA]);
                    mergedLength = Math.max(mergedLength, length[regionB]);
                    ds.union(regionA, regionB);
                    length[regionA] = mergedLength;
                    length[regionB] = mergedLength;
                }
            }
        }
    }

    void search(int cur, int depth) {
        visited[cur] = true;
        if (depth > lowestDepth) {
            lowestDepth = depth;
            lowestNode = cur;
        }
        for (Graph.Edge e : graph.adj[cur]) {
            int v = e.v;
            if (!visited[v]) {
                search(v, depth + 1);
            }
        }
    }

    void search(int cur, Vector<Integer> visitedNodes, int depth) {
        visited[cur] = true;
        if (visitedNodes != null) {
            visitedNodes.add(cur);
        }
        if (depth > lowestDepth) {
            lowestDepth = depth;
            lowestNode = cur;
        }
        for (Graph.Edge e : graph.adj[cur]) {
            int v = e.v;
            if (!visited[v]) {
                search(v, visitedNodes, depth + 1);
            }
        }
    }
}
