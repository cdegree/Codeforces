package current;

import fastio.InputReader;

import java.io.PrintWriter;
import java.io.IOException;
import java.util.InputMismatchException;


public class TaskC {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        char[] a = in.next().toCharArray();
        char[] b = in.next().toCharArray();
        int cnt = 0;
        for (int i = 0; i < b.length; ++i) {
            cnt += b[i] == '1' ? 1 : 0;
        }
        int res = 0;
        for (int i = 0; i < b.length - 1; ++i) {
            cnt += a[i] == '1' ? 1 : 0;
        }
        for (int i = b.length - 1; i < a.length; ++i) {
            cnt += a[i] == '1' ? 1 : 0;
            res += cnt % 2 == 0 ? 1 : 0;
            cnt -= a[i - b.length + 1] == '1' ? 1 : 0;
        }
        out.println(res);
    }
}
