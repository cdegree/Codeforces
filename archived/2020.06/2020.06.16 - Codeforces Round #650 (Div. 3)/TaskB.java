package current;

import fastio.InputReader;

import java.io.PrintWriter;
import java.io.IOException;
import java.util.InputMismatchException;


public class TaskB {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int tt = in.nextInt();
        while (tt-- > 0) {
            int n = in.nextInt();
            int[] a = in.nextIntArray(n);
            int cntOdd = 0;
            int cntEven = 0;
            int cnt = 0;
            for (int i = 0; i < n; ++i) {
                if (a[i] % 2 == 0) {
                    cntEven++;
                } else {
                    cntOdd++;
                }
                if (a[i] % 2 == i % 2) {

                } else {
                    cnt++;
                }
            }
            if (n % 2 == 0) {
                if (cntEven == cntOdd) {
                    out.println(cnt / 2);
                } else {
                    out.println(-1);
                }
            } else {
                if (cntOdd + 1 == cntEven) {
                    out.println(cnt / 2);
                } else {
                    out.println(-1);
                }
            }
        }
    }
}
