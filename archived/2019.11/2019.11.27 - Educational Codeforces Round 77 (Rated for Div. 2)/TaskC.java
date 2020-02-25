package current;

import algorithms.MathUtil;
import fastio.InputReader;

import java.io.PrintWriter;
import java.io.IOException;
import java.util.InputMismatchException;


public class TaskC {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int T = in.nextInt();
        while (T-- > 0) {
            long r = in.nextLong();
            long b = in.nextLong();
            long k = in.nextLong();
            boolean OK = false;
            if (r == b) {
                out.println("OBEY");
                continue;
            }

            long pdx = r * b;
            long gcd = pdx / MathUtil.getGCD(r, b);
            long cnt1 = gcd / r - 1;
            long cnt2 = gcd / b - 1;
            long mx = 0;
            if (cnt2 > cnt1) {
                long t = cnt1;
                cnt1 = cnt2;
                cnt2 = t;
            }
            //out.println("  "+cnt1+" -- "+cnt2);
            mx = (cnt1 + cnt2) / (cnt2 + 1);
            //out.println(mx + "  " + k);
            if (mx < k) {
                out.println("OBEY");
            } else {
                out.println("REBEL");
            }
        }

    }

}
