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
            int x = in.nextInt();
            int y = in.nextInt();
            int width = Math.max(x, a - x - 1);
            int height = Math.max(y, b - y - 1);
            long res = Math.max(width * b, height * a);
            out.println(res);
        }
    }
}
