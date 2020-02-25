package current;

import fastio.InputReader;

import java.io.PrintWriter;
import java.io.IOException;
import java.util.InputMismatchException;


public class TaskC {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        long n = in.nextLong();
        long p = in.nextLong();
        long w = in.nextLong();
        long d = in.nextLong();
        boolean OK = false;
        for (int i = 0; i < w; ++i) {
            int y = i;
            if ((p - y * d) % w == 0) {
                long x = (p - y * d) / w;
                long z = n - x - y;
                if (x >= 0 && z >= 0) {
                    out.println(x + " " + y + " " + z);
                    OK = true;
                    break;
                }
            }
        }
        if (!OK) {
            out.println(-1);
        }
    }
}
