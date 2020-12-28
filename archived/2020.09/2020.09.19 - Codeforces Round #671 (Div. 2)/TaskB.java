package current;

import algorithms.Utils;
import fastio.InputReader;

import java.io.PrintWriter;
import java.io.IOException;
import java.util.InputMismatchException;


public class TaskB {
    public void solve(int testNumber, InputReader in, PrintWriter out) {

        int T = in.nextInt();
        while (T-- > 0) {
            long x = in.nextLong();

            int res = 0;
            for (int i = 1; ; ++i) {
                long len = ((1L << i) - 1);
                if (x - cellsToStair(len) >= 0) {
                    x -= cellsToStair(len);
                    ++res;
                } else {
                    break;
                }
            }
            out.println(res);
        }
    }


    long cellsToStair(long x) {
        return (x + 1) * x / 2;
    }
}
