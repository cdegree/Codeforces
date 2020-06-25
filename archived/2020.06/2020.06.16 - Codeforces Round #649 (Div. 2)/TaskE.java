package current;

import fastio.InputReader;

import java.io.PrintWriter;
import java.util.*;


public class TaskE {
    int[] ans;
    Random rng;
    TreeMap<Query, Integer> query = new TreeMap<>(((o1, o2) -> {
        if (o1.x == o2.x) return o1.y - o2.y;
        return o1.x - o2.x;
    }));

    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        ans = new int[n];
        rng = new Random();
        LinkedList<Integer> cands = new LinkedList<>();
        for (int i = 0; i < n; ++i) cands.add(i + 1);

        int a = cands.removeFirst();
        int b = cands.removeFirst();
        int c = cands.removeFirst();
        int x;
        int y;
        while (true) {
            int A = query(a, b, in, out);
            int B = query(b, c, in, out);
            if (A > B) {
                if (cands.isEmpty()) {
                    x = b;
                    y = c;
                    break;
                }
                a = cands.removeFirst();
            } else if (A < B) {
                if (cands.isEmpty()) {
                    x = a;
                    y = b;
                    break;
                }
                c = cands.removeFirst();
            } else {
                if (cands.isEmpty()) {
                    x = a;
                    y = c;
                    break;
                }
                b = c;
                c = cands.removeFirst();
            }
        }
        int index = getZeroIndex(x, y, n, in, out);
        ans[index - 1] = 0;
        for (int i = 1; i <= n; ++i) {
            if (i != index) {
                ans[i - 1] = query(index, i, in, out);
            }
        }
        out.print("!");
        for (int i = 0; i < n; ++i) {
            out.print(" " + ans[i]);
        }
        out.println();
        out.flush();
    }

    int getZeroIndex(int x, int y, int n, InputReader in, PrintWriter out) {
        int b;
        do {
            b = rng.nextInt(n) + 1;
        } while (b == x || b == y);
        while (true) {
            int A = query(x, b, in, out);
            int B = query(b, y, in, out);
            if (A != B) {
                return A < B ? x : y;
            } else {
                do {
                    b = rng.nextInt(n) + 1;
                } while (b == x || b == y);
            }
        }
    }

    int query(int i, int j, InputReader in, PrintWriter out) {
        if (i > j) {
            int t = i;
            i = j;
            j = t;
        }
        Query q = new Query(i, j);
        if (query.containsKey(q)) {
            return query.get(q);
        }
        out.println(String.format("? %d %d", i, j));
        out.flush();
        int ret = in.nextInt();
        query.put(q, ret);
        return ret;
    }

    class Query {
        int x;
        int y;

        public Query(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
