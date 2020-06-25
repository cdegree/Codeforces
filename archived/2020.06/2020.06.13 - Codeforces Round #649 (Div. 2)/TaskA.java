package current;

import fastio.InputReader;

import java.io.PrintWriter;
import java.io.IOException;
import java.util.InputMismatchException;


public class TaskA {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int tt = in.nextInt();
        while (tt-- > 0) {
            int n = in.nextInt();
            int x = in.nextInt();
            int[] a = in.nextIntArray(n);
            boolean allDivisible = true;
            long sum = 0;
            int left = -1;
            int right = -1;
            for (int i = 0; i < n; ++i) {
                if (a[i] % x != 0) {
                    allDivisible = false;
                    if(left==-1){
                        left = i;
                    }
                    right = i;
                }
                sum += a[i];
            }
            if (allDivisible) {
                out.println(-1);
            } else {
                if (sum % x == 0) {
                    int lx = Math.max(left, n - left - 1);
                    int rx = Math.max(right, n - right - 1);
                    int mx = Math.max(lx, rx);
                    out.println(mx);
                } else {
                    out.println(n);
                }
            }
        }
    }
}
