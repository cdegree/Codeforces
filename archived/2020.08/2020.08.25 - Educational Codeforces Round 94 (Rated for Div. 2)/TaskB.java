package current;

import fastio.InputReader;

import java.io.PrintWriter;
import java.io.IOException;
import java.util.InputMismatchException;


public class TaskB {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int T = in.nextInt();
        while (T-- > 0) {
            long[] c = in.nextLongArray(2);
            long[] count = in.nextLongArray(2);
            long[] w = in.nextLongArray(2);
            long best = 0;
            if (w[0] > w[1]) {
                long t = w[0];
                w[0] = w[1];
                w[1] = t;
                t = count[0];
                count[0] = count[1];
                count[1] = t;
            }
            for (long i = 0; i <= count[0] && i * w[0] <= c[0]; ++i) {

                long leftCarry = c[0] - i * w[0];
                long res = i;
                long canCarryB = leftCarry / w[1];
                res += Math.min(canCarryB, count[1]);
                long leftB = count[1] - Math.min(canCarryB, count[1]);
                long leftA = count[0] - i;
                if (leftA * w[0] <= c[1]) {
                    res += leftA;
                    leftCarry = c[1] - leftA * w[0];
                    canCarryB = leftCarry / w[1];
                    res += Math.min(canCarryB, leftB);
                } else {
                    res += c[1] / w[0];
                }
                best = Math.max(best, res);
            }
            out.println(best);
        }
    }
}
