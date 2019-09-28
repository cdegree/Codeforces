package current;

import fastio.InputReader;

import java.io.PrintWriter;
import java.io.IOException;
import java.util.InputMismatchException;


public class TaskA {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int[] sum = new int[2];
        int[] a = in.readIntArray(n);
        for (int i = 0; i < n; ++i) {
            sum[a[i]]++;
        }
        int res = -1;
        for (int i = 0; i < n; ++i) {
            sum[a[i]]--;
            if (sum[a[i]] == 0) {
                res = i + 1;
                break;
            }
        }
        out.println(res);
    }
}
