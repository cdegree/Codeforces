package current;

import fastio.InputReader;

import java.io.PrintWriter;
import java.io.IOException;
import java.util.InputMismatchException;


public class TaskB {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int t = in.nextInt();
        while (t-- > 0) {
            int a = in.nextInt();
            int b = in.nextInt();
            int p = in.nextInt();
            char[] s = in.nextLine().toCharArray();
            int ans = s.length;
            int n = s.length;
            for (int i = n - 2; i >= 0; --i) {
                int j = i;
                while (j - 1 >= 0 && s[j - 1] == s[i]) {
                    --j;
                }
                int cost = (s[i] == 'A') ? a : b;
                if (p >= cost) {
                    ans = j + 1;
                    p -= cost;
                    i = j;
                } else {
                    break;
                }
            }
            out.println(ans);
        }
    }
}
