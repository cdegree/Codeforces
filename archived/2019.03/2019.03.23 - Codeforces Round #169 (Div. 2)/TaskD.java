package current;

import fastio.InputReader;

import java.io.PrintWriter;
import java.io.IOException;
import java.util.InputMismatchException;


public class TaskD {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        long l = in.nextLong();
        long r = in.nextLong();
        if (l == r) {
            out.println(0);
            return;
        }
        int pos = 62;
        while (!test_bit(r, pos) || test_bit(l, pos)) {
            pos--;
        }
        out.println((1L << (pos + 1)) - 1);
    }

    boolean test_bit(long mask, int pos) {
        return ((mask >> pos) & 1) != 0;
    }
}
