import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * solution is written by hand
 *
 * @author xwchen
 */
public class Main {
    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        Task solver = new Task();
        solver.solve(1, in, out);
        out.close();
    }

    /*

     */
    static class Task {
        static class Edge {
            int u;
            int v;
            boolean reversed;
            boolean visited;
        }

        int cnt = 0;
        ArrayList<Integer>[] adj;
        ArrayList<Integer>[] edgeIds;
        Edge[] edges;
        int[] dfn;
        int[] low;
        boolean[] inStack;
        int sc;
        Stack<Integer> stk = new Stack<>();

        private static final int INF = 1 << 30;
        private static final long MOD = 1000000007;

        int[] dep;
        int[] euler;
        int[] dis;
        boolean[] isRed;

        int[][] dp;
        int[] redInBlock;
        int len;

        public void solve(int testNumber, InputReader in, PrintWriter out) {
            int n = in.nextInt();
            int m = in.nextInt();
            initAdj(n);
            initArray(n);
            for (int i = 0; i < n - 1; i++) {
                int u = in.nextInt() - 1;
                int v = in.nextInt() - 1;
                adj[u].add(v);
                adj[v].add(u);
            }
            dep[0] = 1;
            dfs1(0);
            initSt(n);

            int[] t = new int[m];
            int[] v = new int[m];
            isRed[0] = true;
            for (int i = 0; i < m; i++) {
                t[i] = in.nextInt();
                v[i] = in.nextInt() - 1;
            }
            int q = 1;
            while (q * q < m) {
                q++;
            }
            redInBlock = new int[q + 1];
            len = 0;
            Arrays.fill(dis, INF);
            dis[0] = 0;
            for (int i = 0; i < m; i++) {
                if (i % q == 0) {
                    updateDis(n);
                    len = 0;
                }
                if (t[i] == 1) {
                    redInBlock[len++] = v[i];
                    isRed[v[i]] = true;
                } else {
                    int minDis = dis[v[i]];
                    for (int j = 0; j < len; j++) {
                        minDis = Math.min(minDis, dist(redInBlock[j], v[i]));
                    }
                    out.println(minDis);
                }
            }
        }

        int[] lg;

        void initSt(int n) {
            int n2 = (n << 1) - 1;
            int m = 1;
            while ((1 << m) < n2) {
                ++m;
            }
            lg = new int[n2 + 5];
            lg[1] = 0;
            for (int i = 2; i <= n2; i++) {
                lg[i] = lg[i >> 1] + 1;
            }
            dp = new int[n2][m];
            for (int i = 0; i < n2; i++) {
                dp[i][0] = euler[i];
            }
            for (int j = 1; (1 << j) < n2; j++) {
                for (int i = 0; i + (1 << (j - 1)) < n2; i++) {
                    int prefix = dfn[dp[i][j - 1]];
                    int suffix = dfn[dp[i + (1 << (j - 1))][j - 1]];
                    dp[i][j] = prefix < suffix ? dp[i][j - 1] : dp[i + (1 << (j - 1))][j - 1];
                }
            }
        }


        int lca(int u, int v) {
            int left = Math.min(dfn[u], dfn[v]);
            int right = Math.max(dfn[u], dfn[v]);
            int len = right - left + 1;
            int q = lg[len];
            return dfn[dp[left][q]] < dfn[dp[right - (1 << q) + 1][q]] ? dp[left][q] : dp[right - (1 << q) + 1][q];
        }

        private void initArray(int n) {
            dep = new int[n];
            dfn = new int[n];
            euler = new int[n << 1];
            dis = new int[n];
            isRed = new boolean[n];
        }

        void updateDis(int n) {
            Queue<Integer> q = new LinkedList<>();
            for (int i = 0; i < len; i++) {
                dis[redInBlock[i]] = 0;
                q.add(redInBlock[i]);
            }
            q.add(0);
            while (!q.isEmpty()) {
                int u = q.poll();
                for (Integer v : adj[u]) {
                    if (dis[v] > dis[u] + 1) {
                        dis[v] = dis[u] + 1;
                        q.add(v);
                    }
                }
            }
        }

        void dfs1(int u) {
            dfn[u] = cnt;
            euler[cnt++] = u;
            for (Integer v : adj[u]) {
                if (dep[v] == 0) {
                    dep[v] = dep[u] + 1;
                    dfs1(v);
                    euler[cnt++] = u;
                }
            }
        }

        int dist(int u, int v) {
            return dep[u] + dep[v] - 2 * dep[lca(u, v)];
        }


        long modSum(long a, long b) {
            long sum = a + b;
            if (sum >= MOD) {
                sum -= MOD;
            }
            return sum;
        }


        private void initAdj(int n) {
            adj = new ArrayList[n + 1];
            for (int i = 0; i <= n; i++) {
                adj[i] = new ArrayList<>();
            }
        }

        void tarjan(int u, int fa) {
            low[u] = dfn[u] = ++cnt;
            stk.add(u);
            inStack[u] = true;
            for (int i = 0; i < adj[u].size(); i++) {
                int v = adj[u].get(i);
                if (v == fa) {
                    continue;
                }
                int eId = edgeIds[u].get(i);
                if (dfn[v] == 0) {
                    tagEdge(eId);
                    tarjan(v, u);
                    low[u] = Math.min(low[u], low[v]);
                } else if (inStack[v]) {
                    tagEdge(eId);
                    low[u] = Math.min(low[u], dfn[v]);
                }
            }
//            System.out.println("cur = " + u);
//            System.out.println("low[u] = " + low[u]);
//            System.out.println("dfn[u] = " + dfn[u]);
            if (low[u] == dfn[u]) {
//                System.out.println("sc = " + u);
                sc++;
//                while (stk.peek() != u) {
////                    System.out.println("item = " + stk.peek());
//                    inStack[stk.pop()] = false;
//                }
//                inStack[stk.pop()] = false;
            }
        }

        private void tagEdge(int eId) {
            if (eId < 0) {
                int id = -eId - 1;
                if (!edges[id].visited) {
                    edges[id].reversed = true;
                    edges[id].visited = true;
                }
            } else {
                int id = eId - 1;
                if (!edges[id].visited) {
                    edges[id].reversed = false;
                    edges[id].visited = true;
                }
            }
        }

        long pow(long x, long n) {
            if (n == 1) {
                return x;
            }

            long half = pow(x, n / 2);
            if (n % 2 == 1) {
                return half * half % MOD * x % MOD;
            } else {
                return half * half % MOD;
            }
        }


        int[] dx = {0, 0, 1, -1};
        int[] dy = {1, -1, 0, 0};
    }

    static public int[] getP(char[] s) {
        int n = s.length;
        int[] p = new int[n];
        int j = 0;
        for (int i = 1; i < n; ++i) {
            while (j > 0 && s[i] != s[j]) {
                j = p[j - 1];
            }
            if (s[i] == s[j]) {
                ++j;
            }
            p[i] = j;
        }
        return p;
    }

    int d;

    static class SegmentTree {
        int[] nodeLeft;
        int[] nodeRight;
        long[] sum;
        int[] max;
        int[] dValue;

        public SegmentTree(int n) {
            int m = n * 4;
            nodeLeft = new int[m];
            nodeRight = new int[m];
            sum = new long[m];
            max = new int[m];
        }

        public void setDValue(int[] dValue) {
            this.dValue = dValue;
        }

        public void build(int[] value, int rt, int left, int right) {
            nodeLeft[rt] = left;
            nodeRight[rt] = right;
            if (left == right) {
                sum[rt] = value[left];
                max[rt] = value[left];
                return;
            }
            int mid = (left + right) >> 1;
            build(value, rt * 2, left, mid);
            build(value, rt * 2 + 1, mid + 1, right);
            pullUp(rt);
        }

        public long query(int rt, int queryLeft, int queryRight) {
            if (queryLeft <= nodeLeft[rt] && nodeRight[rt] <= queryRight) {
                return sum[rt];
            }
            int mid = (nodeLeft[rt] + nodeRight[rt]) >> 1;
            long r = 0;
            if (queryLeft <= mid) {
                r += query(rt * 2, queryLeft, queryRight);
            }
            if (queryRight > mid) {
                r += query(rt * 2 + 1, queryLeft, queryRight);
            }
            return r;
        }

        public void update(int rt, int updateLeft, int updateRight) {
            if (max[rt] <= 2) {
                return;
            }
            if (nodeLeft[rt] == nodeRight[rt]) {
                max[rt] = dValue[max[rt]];
                sum[rt] = max[rt];
                return;
            }
            int mid = (nodeLeft[rt] + nodeRight[rt]) >> 1;
            if (updateLeft <= mid) {
                update(rt * 2, updateLeft, updateRight);
            }
            if (updateRight > mid) {
                update(rt * 2 + 1, updateLeft, updateRight);
            }
            pullUp(rt);
        }

        private void pullUp(int rt) {
            sum[rt] = sum[rt * 2] + sum[rt * 2 + 1];
            max[rt] = Math.max(max[rt * 2], max[rt * 2 + 1]);
        }

        private void pushDown(int rt) {
            if (max[rt] != 0) {
                maintain(rt * 2, max[rt]);
                maintain(rt * 2 + 1, max[rt]);
                max[rt] = 0;
            }
        }

        private void maintain(int rt, int val) {
            sum[rt] += val * (nodeRight[rt] - nodeLeft[rt] + 1);
            this.max[rt] += val;
        }
    }

    static class Graph {
        public LinkedList<Edge>[] adj;
        int n;

        public Graph() {

        }

        public Graph(int n) {
            this.n = n;
            adj = new LinkedList[n];
            for (int i = 0; i < n; ++i) {
                adj[i] = new LinkedList<>();
            }
        }

        public void addEdge(int u, int v, int value, int type) {
            adj[u].add(new Edge(u, v, value, type));
            //System.out.println("add edge "+u+" "+v);
        }

        public void addEdge(int u, int v) {
            adj[u].add(new Edge(v));
        }

        public int getMinDistanceTo(int src, long[] train) {
            PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingLong(x -> x.distance));
            Node p = new Node(src, 0);
            long[] distance = new long[n];
            boolean[] visited = new boolean[n];
            Arrays.fill(visited, false);
            Arrays.fill(distance, 1L << 60);
            distance[src] = 0;
            pq.add(p);
            int n = train.length;
            for (int i = 0; i < n; i++) {
                pq.add(new Node(i, train[i]));
            }
            int ans = 0;
            while (!pq.isEmpty()) {
                Node cur = pq.poll();
                if (distance[cur.u] > cur.distance) {
                    distance[cur.u] = cur.distance;
                    ++ans;
                }
                if (visited[cur.u]) {
                    continue;
                }
                visited[cur.u] = true;
                for (Edge e : adj[cur.u]) {
                    int v = e.v;
                    if (distance[v] > distance[cur.u] + e.value) {
                        distance[v] = distance[cur.u] + e.value;
                        pq.add(new Node(v, distance[v]));
                    }
                }
            }
            return ans;
        }

        public class Edge {
            public int u;
            public int v;
            public int value;
            public int type;
            public boolean used = false;

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

            public Edge(int v) {
                this.v = v;
            }

        }

        class Node {
            int u;
            long distance;

            public Node(int u, long distance) {
                this.u = u;
                this.distance = distance;
            }

        }

    }

    static class InputReader {

        private BufferedReader reader;
        private StringTokenizer tokenizer = new StringTokenizer("");

        public InputReader(InputStream inputStream) {
            this.reader = new BufferedReader(new InputStreamReader(inputStream));
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

        public char nextChar() {
            try {
                int c = reader.read();
                return (char) c;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        public String nextLine() {
            String ret = "";
            try {
                ret = reader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return ret;
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }

        public long nextLong() {
            return Long.parseLong(next());
        }

        public double nextDouble() {
            return Double.parseDouble(next());
        }

        public int[] nextIntArray(int n) {
            int[] a = new int[n];
            for (int i = 0; i < n; i++)
                a[i] = nextInt();
            return a;
        }

        public int[] nextIntArray1(int n) {
            int[] a = new int[n + 1];
            for (int i = 1; i <= n; i++)
                a[i] = nextInt();
            return a;
        }


        public long[] nextLongArray(int n) {
            long[] a = new long[n];
            for (int i = 0; i < n; i++)
                a[i] = nextLong();
            return a;
        }

        public long[] nextLongArray1(int n) {
            long[] a = new long[n + 1];
            for (int i = 1; i <= n; i++)
                a[i] = nextLong();
            return a;
        }
    }
}

