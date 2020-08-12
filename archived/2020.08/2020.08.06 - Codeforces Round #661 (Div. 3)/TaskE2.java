package current;

import fastio.InputReader;

import java.io.PrintWriter;
import java.io.IOException;
import java.util.*;


public class TaskE2 {
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
                int c = in.nextInt();
                adj[u].add(new Edge(i - 1, v, w));
                adj[v].add(new Edge(i - 1, u, w));
                Edge e = new Edge();
                e.w = w;
                e.cnt = 0;
                e.cost = c;
                edges.add(e);
            }
            leafsUnder = new int[n + 1];
            dfs(1, 1);

            Vector<Long> v1 = getGain(1, S);
            Vector<Long> v2 = getGain(2, S);
            //pf(v1, 1);
            //pf(v2, 2);
            int j = v2.size() - 1;
            int res = 1000000000;
            for (int i = 0; i < v1.size(); ++i) {
                while (j - 1 >= 0 && v1.get(i) + v2.get(j - 1) <= S) --j;
                if (v1.get(i) + v2.get(j) <= S) {
                    res = Math.min(res, i + j * 2);
                }
            }
            out.println(res);
        }
    }

    void pf(Vector<Long> v, int tag) {
        for (int i = 0; i < v.size(); ++i) {
            System.out.println(String.format("v%d(%d) = %d", tag, i, v.get(i)));
        }
        System.out.println();
    }

    // v(i) + v(j) <= S
    //Sum{cnt(i)* weight(i)  <= S }
    Vector<Long> getGain(int cost, long S) {
        Vector<Long> ret = new Vector<>();
        PriorityQueue<Edge> pq = new PriorityQueue<>(((o1, o2) -> Long.compare(o2.gain, o1.gain)));
        long sum = 0;
        for (Edge e : edges) {
            e.gain = 1L * (e.w + 1) / 2 * e.cnt;
            if (e.cost == cost) {
                sum += 1L * e.w * e.cnt;
                //System.out.println("e.gain  " + e.gain);
                pq.add(e);
            }
        }
        ret.add(sum);
        while (!pq.isEmpty() && pq.peek().gain > 0) {
            Edge top = pq.poll();
            //System.out.println("e.gain  " + top.gain);
            sum -= top.gain;
            top.w /= 2;
            top.gain = 1L * (top.w + 1) / 2 * top.cnt;
            pq.add(top);
            ret.add(sum);
            //System.out.println(String.format("i = %d  sum = %d S = %d top.w = %d", ret.size(), sum, S, top.w));
        }
        return ret;
    }

    void dfs(int u, int pre) {
        boolean isLeaf = true;
        for (Edge e : adj[u]) {
            int v = e.v;
            if (v == pre) continue;
            isLeaf = false;
            dfs(v, u);
            edges.get(e.index).cnt += leafsUnder[v];
            leafsUnder[u] += leafsUnder[v];
        }
        if (isLeaf) {
            leafsUnder[u]++;
        }
    }

    class Edge {
        int index;
        int v;
        int w;
        int cnt;
        long gain;
        int cost;

        public Edge() {
        }

        public Edge(int index, int v, int w) {
            this.index = index;
            this.v = v;
            this.w = w;
            //System.out.println(String.format("%d %d %d %d", index, v, w, c));
        }
    }
}
