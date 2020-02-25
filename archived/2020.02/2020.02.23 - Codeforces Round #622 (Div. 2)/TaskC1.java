package current;

import fastio.InputReader;

import java.io.PrintWriter;
import java.io.IOException;
import java.util.InputMismatchException;


public class TaskC1 {
    int[] m;
    int[] a;

    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        m = in.nextIntArray(n);
        a = new int[n];
        long r = sol(0, n, 0);
        System.out.println(r);
        long sum = 0;
        for (int i = 0; i < n; ++i) {
            if (a[i] == 0) {
                a[i] = m[i];
            }
            sum += a[i];
        }
        out.println(sum);
        for (int i = 0; i < n; ++i) {
            out.print(a[i] + " ");
        }
    }

    long sol(int l, int r, int up) {
        int mn = 2000000000;
        for (int i = l; i < r; ++i) {
            if (mn > m[i]) {
                mn = m[i];
            }
        }
        long res = (mn - up) * (r - l);
        long mx = 0;
        int st = l;
        int ed = r;
        for (int i = l; i < r; ++i) {
            if (m[i] > mn) {
                int j = i + 1;
                while (j < r && m[j] > mn) {
                    ++j;
                }
                long t = sol(i, j, mn);
                if (mx < t) {
                    mx = t;
                    st = i;
                    ed = j;
                }
            }
        }

        //System.out.println(String.format("Largest of %d %d = %d %d",l,r,st,ed));
        return res + mx;
    }
}
