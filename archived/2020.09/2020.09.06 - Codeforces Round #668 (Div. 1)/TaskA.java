package current;

import algorithms.Utils;
import fastio.InputReader;

import java.io.PrintWriter;
import java.io.IOException;
import java.util.InputMismatchException;


public class TaskA {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int T = in.nextInt();
        while (T-- > 0) {
            int n = in.nextInt();
            int k = in.nextInt();
            int m = k / 2;
            char[] s = in.nextLine().toCharArray();
            int[] cnt = new int[2];
            for (int i = 0; i < k; ++i) {
                if (s[i] == '?') {

                } else {
                    cnt[s[i] - '0']++;
                }
            }
            boolean OK = true;
            for (int i = k; i <= n; ++i) {
                if (cnt[0] > m || cnt[1] > m) {
                    OK = false;
                    break;
                }
                if (i == n) {
                    break;
                }
                int st = i - k;
                if (s[st] == '0') {
                    if (s[i] == '1') {
                        OK = false;
                    } else if (s[i] == '?') {
                        s[i] = '0';
                    }
                } else if (s[st] == '1') {
                    if (s[i] == '0') {
                        OK = false;
                    } else if (s[i] == '?') {
                        s[i] = '1';
                    }
                }
                if (s[st] == '?' && s[i] != '?') {
                    cnt[s[i] - '0']++;
                }
            }
            out.println(Utils.YESNO(OK));
        }
    }
}
