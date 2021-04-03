import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Collection;
import java.io.IOException;
import java.util.Deque;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.AbstractCollection;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.util.LinkedList;
import java.util.ArrayDeque;
import java.io.InputStream;

/**
 * Built using CHelper plug-in
 * Actual solution is at the top
 *
 * @author xwchen
 */
public class Main {
    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        TaskE solver = new TaskE();
        solver.solve(1, in, out);
        out.close();
    }

    static class TaskE {
        public void solve(int testNumber, InputReader in, PrintWriter out) {
            int n = in.nextInt();
            int k = in.nextInt();
            int[] p = in.nextIntArray1(n);
            int[] next = new int[n + 1];
            int[] map = new int[n + 1];
            int[] rmap = new int[n + 1];
            Arrays.fill(map, -1);
            Arrays.fill(rmap, -1);
            Arrays.fill(next, -1);
            LinkedList<Integer>[] adj = new LinkedList[n + 1];
            LinkedList<Integer>[] strongAdj = new LinkedList[n + 1];
            LinkedList<Integer>[] weakAdj = new LinkedList[n + 1];
            for (int i = 1; i <= n; ++i) {
                adj[i] = new LinkedList<>();
                strongAdj[i] = new LinkedList<>();
                weakAdj[i] = new LinkedList<>();
            }
            DisjointSet disjointSet = new DisjointSet(n);
            boolean valid = true;
            int[] inDegree = new int[n + 1];
            for (int i = 0; i < k; ++i) {
                int x = in.nextInt();
                int y = in.nextInt();
                strongAdj[x].addLast(y);
                inDegree[y]++;
                disjointSet.union(x, y);
            }
            ArrayList<Integer> topologicalResult = Graph.topologicalSort(strongAdj, inDegree, n);
            if (topologicalResult.size() < n) {
                out.println(0);
                return;
            }
            for (int i = 1; i <= n; ++i) {
                if (p[i] > 0) {
                    weakAdj[p[i]].addLast(i);
                    inDegree[i]++;
                }
                if (!strongAdj[i].isEmpty()) {
                    for (int v : strongAdj[i]) {
                        weakAdj[i].add(v);
                        inDegree[v]++;
                    }
                }
            }
//        for (int i = 1; i <= n; ++i) {
//            System.out.println(String.format("weakAdj[%d] = %s", i, weakAdj[i]));
//        }
            topologicalResult = Graph.topologicalSort(weakAdj, inDegree, n);
            if (topologicalResult.size() < n) {
                out.println(0);
                return;
            }

            int compSize = 0;
            for (int i = 1; i <= n; ++i) {
                int sx = disjointSet.find(i);
                if (map[sx] == -1) {
                    map[sx] = ++compSize;
                    rmap[compSize] = sx;
                }
            }
//        for (int i = 1; i <= n; ++i) {
//            Utils.pf("set[%d] = %d map= %d", i, disjointSet.find(i), map[disjointSet.find(i)]);
//        }
            Arrays.fill(inDegree, 0);
            for (int i = 1; i <= n; ++i) {
                int sx = disjointSet.find(i);
                if (p[i] != 0) {
                    int psx = disjointSet.find(p[i]);
                    if (map[psx] != map[sx]) {
                        adj[map[psx]].addLast(map[sx]);
                        inDegree[map[sx]]++;
                    }
                }
            }
//        for (int i = 1; i <= compSize; ++i) {
//            System.out.println(String.format("adj[%d] = %s", i, adj[i]));
//        }
            topologicalResult = Graph.topologicalSort(adj, inDegree, compSize);
            if (topologicalResult.size() < compSize) {
                out.println(0);
                return;
            }
            int[] result = new int[n];
            int id = 0;
            boolean[] visited = new boolean[n + 1];
            for (int q : topologicalResult) {
                int x = rmap[q];
                if (disjointSet.getSize(x) > 1) {
                    visited[x] = true;
                    result[id++] = x;
                    while (strongAdj[x].size() > 0) {
                        if (strongAdj[x].size() > 1) {
                            valid = false;
                            break;
                        }
                        x = strongAdj[x].getFirst();
                        visited[x] = true;
                        result[id++] = x;
                    }
                } else {
                    visited[x] = true;
                    result[id++] = x;
                }
            }
            if (!valid) {
                out.println(0);
            } else {
                for (int x : result) {
                    out.print(x + " ");
                }
                out.println();
            }
        }

    }

    static class DisjointSet {
        private int[] set;
        private int[] size;

        public DisjointSet(int n) {
            set = new int[n + 1];
            size = new int[n + 1];
            for (int i = 1; i <= n; ++i) {
                set[i] = i;
                size[i] = 1;
            }
        }

        public int find(int x) {
            return set[x] == x ? x : (set[x] = find(set[x]));
        }

        public void union(int x, int y) {
            int sx = find(x);
            int sy = find(y);
            if (sx != sy) {
                set[sy] = sx;
                size[sx] += size[sy];
            }
        }

        public int getSize(int x) {
            return size[find(x)];
        }

    }

    static class Graph {
        public LinkedList<Edge>[] adj;
        int n;

        public Graph() {

        }

        public Graph(int n) {
            this.n = n;
            adj = new LinkedList[n + 1];
            for (int i = 0; i <= n; ++i) {
//            adj[i] = new TreeSet<>((o1, o2) -> {
//                if (o1.value != o2.value) {
//                    return o1.value - o2.value;
//                } else {
//                    return o1.type - o2.type;
//                }
//            });
                adj[i] = new LinkedList<>();
            }
        }

        public static ArrayList<Integer> topologicalSort(LinkedList<Integer>[] adj, int[] inDegree, int n) {
            Deque<Integer> dq = new ArrayDeque<>();
            for (int i = 1; i <= n; ++i) {
                if (inDegree[i] == 0) {
                    dq.addLast(i);
                }
            }
            ArrayList<Integer> ret = new ArrayList<>();
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

        public class Edge {
            public int u;
            public int v;
            public int value;
            public int type;

            public Edge(int u, int v, int value, int type) {
                this.u = u;
                this.v = v;
                this.value = value;
                this.type = type;
            }

            public Edge(int u, int v, int value) {
                this.u = u;
                this.v = v;
                this.value = value;
            }

            public Edge(int v, int value) {
                this.v = v;
                this.value = value;
            }

        }

    }

    static class InputReader {
        private BufferedReader reader;
        private StringTokenizer tokenizer = new StringTokenizer("");

        public InputReader(InputStream inputStream) {
            this.reader = new BufferedReader(
                    new InputStreamReader(inputStream));
        }

        public String next() {
            while (!tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return tokenizer.nextToken();
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }

        public int[] nextIntArray1(int n) {
            int[] a = new int[n + 1];
            for (int i = 1; i <= n; i++)
                a[i] = nextInt();
            return a;
        }

    }
}

