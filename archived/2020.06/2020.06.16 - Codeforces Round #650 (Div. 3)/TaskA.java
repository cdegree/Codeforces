package current;

import fastio.InputReader;

import java.io.PrintWriter;
import java.io.IOException;
import java.util.InputMismatchException;


public class TaskA {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int tt = in.nextInt();
        while (tt-- > 0) {
            char[] c = in.nextLine().toCharArray();
            StringBuilder sb = new StringBuilder();
            sb.append(c[0]);
            sb.append(c[1]);
            for (int i = 3; i < c.length; i += 2) {
                sb.append(c[i]);
            }
            out.println(sb);
        }
    }
}
