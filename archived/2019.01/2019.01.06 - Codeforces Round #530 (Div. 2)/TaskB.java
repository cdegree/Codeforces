package current;

import fastio.InputReader;

import java.io.PrintWriter;
import java.io.IOException;
import java.util.InputMismatchException;


public class TaskB {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        long l = 1;
        long r = 1;
        while (l * r < n) {
            if (l < r) ++l;
            else ++r;
        }
        out.println(l + r);

    }
}
