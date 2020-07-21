package current;

import fastio.InputReader;

import java.io.PrintWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.InputMismatchException;


public class TaskA {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int T = in.nextInt();
        while (T-- > 0) {
            int[] a = in.nextIntArray(3);
            Arrays.sort(a);
            if (a[1] == a[2]) {
                out.println("YES");
                out.println(a[1] + " " + a[0] + " " + a[0]);
            } else {
                out.println("NO");
            }
        }
    }
}
