package current;

import fastio.InputReader;

import java.io.PrintWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.InputMismatchException;


public class TaskD {

    int[] occur;
    long ans = 0;

    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int q = in.nextInt();
        occur = new int[1000005];
        int[] a = in.readIntArray(n);
        Query[] queries = new Query[q];
        for (int i = 0; i < q; ++i) {
            queries[i] = new Query(in.nextInt() - 1, in.nextInt() - 1, i);
        }
        int k = (int) Math.round(Math.sqrt(n));
        Arrays.sort(queries, new Comparator<Query>() {
            @Override
            public int compare(Query o1, Query o2) {
                int g1 = o1.l / k;
                int g2 = o2.l / k;
                if (g1 == g2) {
                    return o1.r - o2.r;
                } else {
                    return g1 - g2;
                }
            }
        });
        int curL = 0, curR = 0;
        ans = 0;
        long[] result = new long[q];
        for (int i = 0; i < q; ++i) {
            int sL = queries[i].l;
            int sR = queries[i].r;
            while (curL < sL) {
                remove(curL++, a);
            }
            while (curL > sL) {
                add(--curL, a);
            }
            while (curR <= sR) {
                add(curR++, a);
            }
            while (curR > sR + 1) {
                remove(--curR, a);
            }
            result[queries[i].idx] = ans;
        }
        for (int i = 0; i < q; ++i) {
            out.println(result[i]);
        }
    }

    void add(int idx, int[] a) {
        ans += a[idx] * (2L * occur[a[idx]]++ + 1);
    }

    void remove(int idx, int[] a) {
        ans -= a[idx] * (2L * occur[a[idx]]-- - 1);
    }

    class Query {
        int l, r, ans, idx;

        Query(int l, int r, int idx) {
            this.l = l;
            this.r = r;
            this.idx = idx;
        }
    }
}
