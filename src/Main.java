import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.AbstractCollection;
import java.util.StringTokenizer;
import java.io.IOException;
import java.io.BufferedReader;
import java.util.LinkedList;
import java.io.InputStreamReader;
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
        final int N = 200005;
        LinkedList<Nerbor>[] adj = new LinkedList[N];
        boolean[] visited = null;
        int[] steps = new int[N];
        boolean foundCircle;

        public void solve(int testNumber, InputReader in, PrintWriter out) {
            int T = in.nextInt();
            while (T-- > 0) {
                int n = in.nextInt();
                visited = new boolean[n + 1];
                for (int i = 0; i <= n; ++i) {
                    adj[i] = new LinkedList<>();
                }
                Arrays.fill(steps, 0);
                foundCircle = false;
                int m = in.nextInt();
                Edge[] edges = new Edge[m];
                int[] ind = new int[n + 1];
                for (int i = 0; i < m; ++i) {
                    int t = in.nextInt();
                    int x = in.nextInt();
                    int y = in.nextInt();
                    edges[i] = new Edge(x, y);

                    if (t == 0) {
                        adj[x].addLast(new Nerbor(y, t, edges[i]));
                        adj[y].addLast(new Nerbor(x, t, edges[i]));
                    } else {
                        edges[i].set = true;
                        adj[x].addLast(new Nerbor(y, t, edges[i]));
                        ind[y]++;
                    }
                }
                for (int i = 1; i <= n; ++i) {
                    if (!visited[i]) {
                        findCircle(i, i, i);
                    }
                }
                if (foundCircle) {
                    out.println("NO");
                } else {
                    out.println("YES");
                    Arrays.fill(visited, false);
                    for (int i = 1; i <= n; ++i) {
                        if (ind[i] == 0 && !visited[i]) {
                            expand(i);
                        }
                    }
                    for (int i = 0; i < m; ++i) {
                        if (!edges[i].set) while (true) ;
                        if (edges[i].reverse) {
                            out.println(edges[i].v + " " + edges[i].u);
                        } else {
                            out.println(edges[i].u + " " + edges[i].v);
                        }
                    }
                }
            }
        }

        void expand(int u) {
            visited[u] = true;
            LinkedList<Integer> queue = new LinkedList<>();
            queue.addLast(u);
            while (!queue.isEmpty()) {
                int cur = queue.removeFirst();
                for (Nerbor nerbor : adj[cur]) {
                    if (nerbor.type == 0 && !nerbor.edge.set) {
                        setDirection(nerbor.edge, cur);
                    }
                    if (!visited[nerbor.v]) {
                        queue.addLast(nerbor.v);
                        visited[nerbor.v] = true;
                    }
                }
            }
        }

        void setDirection(Edge edge, int u) {
            edge.set = true;
            if (edge.v == u) {
                edge.reverse = true;
            }
        }

        void findCircle(int u, int pre, int step) {
            visited[u] = true;
            steps[u] = step;
            for (Nerbor nerbor : adj[u]) {
                if (nerbor.type == 1) {
                    if (visited[nerbor.v]) {
                        if (steps[nerbor.v] == step) {
                            foundCircle = true;
                        }
                    } else {
                        findCircle(nerbor.v, u, step);
                    }
                }
            }
        }

        class Nerbor {
            int v;
            int type;
            Edge edge;

            public Nerbor(int v, int type, Edge edge) {
                this.v = v;
                this.type = type;
                this.edge = edge;
            }

        }

        class Edge {
            int u;
            int v;
            boolean reverse = false;
            boolean set = false;

            public Edge(int u, int v) {
                this.u = u;
                this.v = v;
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

    }
}

