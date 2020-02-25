package current;

import fastio.InputReader;

import java.io.PrintWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Vector;


public class TaskD {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int[] a = in.readIntArray(n);
        Vector<Integer> dividers = new Vector<>();
        for (int i = 2; i <= n; ++i) {
            if (n % i == 0 && n / i >= 3) {
                dividers.add(i);
            }
        }
        int result = 0;
        for (int i = 0; i < n; ++i) {
            result += a[i];
        }
        for (int divider : dividers) {
            for (int st = 0; st < divider; ++st) {
                int sum = 0;
                for (int i = 0; i < n; i += divider) {
                    sum += a[st + i];
                }
                if (result < sum) {
                    result = sum;
                }
            }
        }
        out.println(result);
    }
}
