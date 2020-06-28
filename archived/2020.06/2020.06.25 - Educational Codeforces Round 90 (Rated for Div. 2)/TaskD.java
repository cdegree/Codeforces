package current;

import fastio.InputReader;

import java.io.PrintWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Vector;


public class TaskD {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int tt = in.nextInt();
        while (tt-- > 0) {
            int n = in.nextInt();
            int[] a = in.nextIntArray(n);
            long sumOfEven = 0;
            for (int i = 0; i < n; i += 2) {
                sumOfEven += a[i];
            }
            Vector<Integer> c1 = new Vector<>();
            Vector<Integer> c2 = new Vector<>();
            for (int i = 0; i < n; i += 2) {
                if (i + 1 < n) c1.add(a[i + 1] - a[i]);
                if (i + 2 < n) c2.add(a[i + 1] - a[i + 2]);
            }
            long maxSum = 0;
            maxSum = Math.max(maxSum, maxSubArray(c1));
            maxSum = Math.max(maxSum, maxSubArray(c2));
            out.println(maxSum + sumOfEven);
        }
    }

    long maxSubArray(Vector<Integer> a) {
        long ret = 0;
        long preSum = 0;
        for (int i = 0; i < a.size(); ++i) {
            if (preSum <= 0) {
                preSum = a.get(i);
            } else {
                preSum += a.get(i);
            }
            ret = Math.max(ret, preSum);
        }
        return ret;
    }
}
