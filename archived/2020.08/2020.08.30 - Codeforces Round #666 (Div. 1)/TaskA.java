package current;

import fastio.InputReader;

import java.io.PrintWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.stream.IntStream;


public class TaskA {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        long[] a = in.nextLongArray(n);
        ArrayList<Long> b1 = new ArrayList<>(n - 1);
        ArrayList<Long> b2 = new ArrayList<>(n - 1);

        for (int i = 0; i < n - 1; ++i) {
            if (a[i] % n == 0) {
                b1.add(1L*(n-1) * n);
            } else {
                long r = a[i] % n;
                b1.add(r * (n - 1));
            }
        }
        for (int i = 0; i < n - 1; ++i) {
            b2.add(-a[i]);
        }
        if (n == 1) {
            out.println("1 1");
            out.println("0");
            out.println("1 1");
            out.println("0");
            out.println("1 1");
            out.println(-a[0]);
        } else {
            b2.add(0L);
            out.println("1 " + (n - 1));
            b1.stream().forEach(o->out.print(o+" "));
            out.println();
            out.println("1 " + n);
            b2.stream().forEach(o->out.print(o+" "));
            out.println();
            out.println(n + " " + n);
            out.println(-a[n - 1]);
        }
    }
}
