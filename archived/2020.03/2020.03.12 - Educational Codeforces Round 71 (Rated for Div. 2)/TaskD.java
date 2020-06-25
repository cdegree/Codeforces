package current;

import fastio.InputReader;

import java.io.PrintWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.Vector;


public class TaskD {
    long mod = 998244353L;

    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        Vector<Pair> pairs = new Vector<>();
        int[] a = new int[n + 1];
        int[] b = new int[n + 1];
        long[] f = new long[n + 1];
        long c3 = 1;
        f[0] = 1;
        for (int i = 0; i < n; ++i) {
            f[i + 1] = f[i] * (i + 1) % mod;
            int x = in.nextInt();
            int y = in.nextInt();
            a[x]++;
            b[y]++;
            pairs.add(new Pair(x, y));
        }

        Collections.sort(pairs);
        long res = f[n];
        long c1 = 1;
        long c2 = 1;
        for (int i = 1; i <= n; ++i) {
            c1 = c1 * f[a[i]] % mod;
            c2 = c2 * f[b[i]] % mod;
            //System.out.println(pairs.get(i-1).x+" "+pairs.get(i-1).y);
        }
        int last = 1;
        boolean OK = true;
        for (int i = 1; i < n; ++i) {
            if (pairs.get(i - 1).y > pairs.get(i).y) {
                OK = false;
            }
            if (pairs.get(i - 1).x == pairs.get(i).x && pairs.get(i - 1).y == pairs.get(i).y) {
                last++;
            } else {
                c3 = c3 * f[last] % mod;
                last = 1;
            }
        }
        c3 = c3 * f[last] % mod;
        if (!OK) c3 = 0;
        //System.out.println(c1 + " " + c2 + " " + c3);
        res = ((res - c1 - c2 + c3) % mod + mod) % mod;
        out.println(res);

    }

    public class Pair implements Comparable<Pair> {
        int x;
        int y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Pair o) {
            if (o.x == x) return y - o.y;
            return x - o.x;
        }
    }
}
