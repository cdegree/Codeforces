package current;

import algorithms.Pair;
import fastio.InputReader;

import java.io.PrintWriter;
import java.io.IOException;
import java.util.*;


public class TaskE {

    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int T = in.nextInt();
        while (T-- > 0) {
            int n = in.nextInt();
            LinkedList<Integer>[] adj = new LinkedList[n + 1];
            for (int i = 0; i <= n; ++i) {
                adj[i] = new LinkedList<>();
            }
            int m = in.nextInt();
            Edge[] edges = new Edge[m];
            int[] ind = new int[n + 1];
            for (int i = 0; i < m; ++i) {
                int t = in.nextInt();
                int x = in.nextInt();
                int y = in.nextInt();
                if (t == 1) {
                    adj[x].addLast(y);
                    ++ind[y];
                }
                edges[i] = new Edge(x, y);
            }
            Vector<Integer> nodes = topologicalSort(adj, ind, n);
            if (nodes.size() < n) {
                out.println("NO");
            } else {
                out.println("YES");
                int[] pos = new int[n + 1];
                for (int i = 0; i < n; ++i) {
                    pos[nodes.get(i)] = i;
                }
                for (int i = 0; i < m; ++i) {
                    if (pos[edges[i].x] > pos[edges[i].y]) {
                        out.println(edges[i].y + " " + edges[i].x);
                    } else {
                        out.println(edges[i].x + " " + edges[i].y);
                    }
                }
            }
        }
    }

    Vector<Integer> topologicalSort(LinkedList<Integer>[] adj, int[] inDegree, int n) {
        Deque<Integer> dq = new ArrayDeque<>();
        for (int i = 1; i <= n; ++i) {
            if (inDegree[i] == 0) {
                dq.addLast(i);
            }
        }
        Vector<Integer> ret = new Vector<>();
        while (!dq.isEmpty()) {
            int cur = dq.removeFirst();
            ret.add(cur);
            for (int v : adj[cur]) {
                --inDegree[v];
                if (inDegree[v] == 0) {
                    dq.addLast(v);
                }
            }
        }
        return ret;
    }

    class Edge {
        public int x;
        public int y;

        public Edge(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public Edge() {
        }

    }
}
