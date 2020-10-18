package current;

import fastio.InputReader;

import java.io.PrintWriter;
import java.io.IOException;
import java.util.InputMismatchException;


public class TaskC {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int T = in.nextInt();
        while (T-- > 0) {
            char[] s = in.nextLine().toCharArray();
            int n = s.length;
            boolean started = false;
            int cntB = 0;
            int cnt = 0;
            for (int i = n - 1; i >= 0; --i) {
                if (started) {
                    if (s[i] == 'B') {
                        ++cntB;
                    } else {
                        if (cntB > 0) {
                            --cntB;
                            cnt += 2;
                        }
                    }
                } else {
                    if (s[i] == 'B') {
                        started = true;
                        cntB = 1;
                    }
                }
            }
            cnt += cntB / 2 * 2;
            int res = n - cnt;
            out.println(res);
        }
    }
}
