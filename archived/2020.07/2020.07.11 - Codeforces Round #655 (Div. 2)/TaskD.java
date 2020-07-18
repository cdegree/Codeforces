package current;

import fastio.InputReader;

import java.io.PrintWriter;
import java.util.Comparator;
import java.util.TreeSet;
import java.util.Vector;


public class TaskD {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int[] a = in.nextIntArray(n);
        int m = (n + 1) / 2;
        long subArraySum = 0;
        for (int i = 0; i < n; i += 2) {
            subArraySum += a[i];
        }
        long best = subArraySum;
        for (int i = 0; i < 2 * n + 2; i += 2) {
            subArraySum -= a[i % n];
            subArraySum += a[(m * 2 + i) % n];
            best = Math.max(best, subArraySum);
        }
        out.println(best);
    }

}
