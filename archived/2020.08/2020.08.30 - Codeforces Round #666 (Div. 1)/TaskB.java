package current;

import algorithms.ArrayUtils;
import fastio.InputReader;

import java.io.PrintWriter;
import java.io.IOException;
import java.util.InputMismatchException;


public class TaskB {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int T = in.nextInt();
        while (T-- > 0) {
            int n = in.nextInt();
            int[] a = in.nextIntArray(n);
            int max = ArrayUtils.maxElement(a);
            long sum = ArrayUtils.sum(a);
            long remain = sum - max;
            if (remain < max) {
                out.println("T");
            } else {
                if (sum % 2 == 1) {
                    out.println("T");
                } else {
                    out.println("HL");
                }
            }
        }
    }
}
