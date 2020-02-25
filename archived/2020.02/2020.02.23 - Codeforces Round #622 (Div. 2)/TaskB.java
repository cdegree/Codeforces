package current;

import fastio.InputReader;

import java.io.PrintWriter;
import java.io.IOException;
import java.util.InputMismatchException;


public class TaskB {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int t = in.nextInt();
        while (t-- > 0) {
            long n = in.nextInt();
            long x = in.nextInt();
            long y = in.nextInt();
            long avg = n + 1;
            long total = n * avg;
            long sum = x + y;
            long best = 1;
            long worst = n;
            long left = 1;
            long right = n;
            while (left <= right) {
                long rank = (left + right) / 2;
                long nBefore = rank - 1;
                long used = (1 + nBefore) * nBefore;
                long remained = total - used - sum;
                if (remained / (n - rank) > sum) {
                    best = rank;
                    right = rank - 1;
                } else {
                    left = rank + 1;
                }
            }

            left = 1;
            right = n;
            while (left <= right) {
                long rank = (left + right) / 2;
                long nBefore = rank - 1;
                long used = (1 + nBefore) * nBefore;
                long remained = total - used - sum;
                if (remained / (n - rank) > sum) {
                    right = rank - 1;
                } else {
                    left = rank + 1;
                }
            }
        }
    }
}
