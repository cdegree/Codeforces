package current;

import fastio.InputReader;

import java.io.PrintWriter;
import java.io.IOException;
import java.util.InputMismatchException;


public class TaskC {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int T = in.nextInt();
        while (T-- > 0) {
            long a = in.nextLong();
            long b = in.nextLong();
            long n = in.nextLong();
            long m = in.nextLong();
            if (a + b < n + m) {
                out.println("No");
            } else {
                boolean OK;
                if (a != b) {
                    long diff = Math.abs(a - b);
                    if (diff > n) {
                        long remain = Math.min(a, b);
                        OK = remain >= m;
                    } else {
                        n -= diff;
                        // a == b
                        if (n >= m) {
                            OK = (a + b) >= (n + m);
                        } else {
                            //n < m
                            long r = Math.min(a, b);
                            r -= n;
                            m -= n;
                            if (r < 0) {
                                OK = false;
                            } else {
                                OK = r >= m;
                            }
                        }
                    }
                } else {
                    if (n >= m) {
                        OK = (a + b) >= (n + m);
                    } else {
                        a -= n;
                        m -= n;
                        if (a < 0) {
                            OK = false;
                        } else {
                            OK = a >= m;
                        }
                    }
                }
                out.println(OK ? "Yes" : "No");
            }
        }
    }
}
