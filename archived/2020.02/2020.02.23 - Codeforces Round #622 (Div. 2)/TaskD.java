package current;

import algorithms.MathUtil;
import fastio.InputReader;

import java.io.PrintWriter;
import java.io.IOException;
import java.util.InputMismatchException;


public class TaskD {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int t = in.nextInt();
        while (t-- > 0) {
            long a = in.nextLong();
            long m = in.nextLong();
            long G = MathUtil.getGCD(a, m);
            m /= G;
            long res = MathUtil.getEulerTotientFunction(m);
            out.println(res);
        }
    }
}
