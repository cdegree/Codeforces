package current;

import fastio.InputReader;

import java.io.PrintWriter;
import java.io.IOException;
import java.util.InputMismatchException;


public class TaskA {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int T = in.nextInt();
        while (T-- > 0) {
            int n = in.nextInt();
            boolean found = false;
            int x = -1;
            int y = -1;
            int z = -1;
            for (int i = 0; i * 3 <= n; ++i) {
                for (int j = 0; j * 5 + i * 3 <= n; ++j) {
                    int rest = n - j * 5 - i * 3;
                    if (rest >= 0 && rest % 7 == 0) {
                        found = true;
                        x = i;
                        y = j;
                        z = rest / 7;
                    }
                }
            }
            if (found) {
                out.println(x + " " + y + " " + z);
            } else {
                out.println(-1);
            }
        }
    }
}
