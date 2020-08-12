package current;

import algorithms.Pair;
import fastio.InputReader;

import java.io.PrintWriter;
import java.io.IOException;
import java.util.*;


public class TaskE1 {
    LinkedList<Edge>[] adj;
    Vector<Edge> edges;
    int[] leafsUnder;

    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int T = in.nextInt();
        while (T-- > 0) {
            int n = in.nextInt();
            long S = in.nextLong();
            adj = new LinkedList[n + 1];
            edges = new Vector<>();
            for (int i = 1; i <= n; ++i) {
                adj[i] = new LinkedList<>();
            }
            for (int i = 1; i < n; ++i) {
                int u = in.nextInt();
                int v = in.nextInt();
                int w = in.nextInt();
                adj[u].add(new Edge(i - 1, v, w));
                adj[v].add(new Edge(i - 1, u, w));
                Edge e = new Edge();
                e.w = w;
                e.cnt = 0;
                edges.add(e);
            }
            leafsUnder = new int[n + 1];
            dfs(1, 1);

            int ans = 0;
            PriorityQueue<Edge> pq = new PriorityQueue<>(((o1, o2) -> Long.compare(o2.gain, o1.gain)));
            for (Edge e : edges) {
                S -= 1L * e.w * e.cnt;
                e.gain = 1L * (e.w + 1) / 2 * e.cnt;
                pq.add(e);
            }
            while (S < 0) {
                Edge top = pq.poll();
                S += top.gain;
                top.w /= 2;
                top.gain = 1L * (top.w + 1) / 2 * top.cnt;
                pq.add(top);
                ++ans;
                //System.out.println("sum = " + sum);
            }
            out.println(ans);
        }
    }

    void dfs(int u, int pre) {
        boolean isLeaf = true;
        for (Edge e : adj[u]) {
            int v = e.v;
            if (v == pre) continue;
            int eIndex = e.index;
            isLeaf = false;
            dfs(v, u);
            edges.get(eIndex).cnt += leafsUnder[v];
            leafsUnder[u] += leafsUnder[v];
        }
        if (isLeaf) {
            leafsUnder[u]++;
        }
    }

    class Pair implements Comparable<Pair> {
        public long x;
        public int y;

        public Pair(long x, int y) {
            this.x = x;
            this.y = y;
        }

        public Pair() {
        }

        @Override
        public int compareTo(Pair o) {
            if (o.x == x) return y - o.y;
            return Long.compare(x, o.x);
        }
    }

    class Edge {
        int index;
        int v;
        int w;
        int cnt;
        long gain;

        public Edge() {
        }

        public Edge(int index, int v, int w) {
            this.index = index;
            this.v = v;
            this.w = w;
        }
    }
}
