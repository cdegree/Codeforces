package current;

import fastio.InputReader;

import java.io.PrintWriter;
import java.io.IOException;
import java.util.InputMismatchException;


public class TaskA {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int t = in.nextInt();
        while (t-- > 0) {
            int a = in.nextInt();
            int b = in.nextInt();
            int res = 0;
            if (a == b) {
            } else if (a > b) {
                if ((a - b) % 2 == 0) {
                    res = 1;
                } else {
                    res = 2;
                }
            } else {
                if ((b - a) % 2 == 1) {
                    res = 1;
                } else {
                    res = 2;
                }
            }
            out.println(res);
        }
    }
}
