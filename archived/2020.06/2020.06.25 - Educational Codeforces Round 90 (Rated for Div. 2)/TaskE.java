package current;

import fastio.InputReader;

import java.io.PrintWriter;
import java.io.IOException;
import java.util.InputMismatchException;


public class TaskE {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int tt = in.nextInt();
        while (tt-- > 0) {
            int n = in.nextInt();
            int k = in.nextInt();
            StringBuilder ans = new StringBuilder("-1");
            for (int lastDigit = 0; lastDigit <= 9; ++lastDigit) {
                int cnt9Max = n / 9;
                if (lastDigit + k < 10) {
                    cnt9Max = 0;
                }
                for (int cnt9 = 0; cnt9 <= cnt9Max; ++cnt9) {
                    StringBuilder cur = new StringBuilder("");
                    cur.append(lastDigit);
                    for (int i = 0; i < cnt9; ++i) cur.append(9);
                    int sum = 0;
                    for (int i = 0; i <= k; ++i) {
                        int j = lastDigit + i;
                        if (j <= 9) {
                            sum += j + cnt9 * 9;
                        } else {
                            sum += j % 10 + j / 10;
                        }
                    }
                    int need = n - sum;
                    if (need < 0 || need % (k + 1) != 0) continue;
                    need /= (k + 1);
                    int maxNum = 9;
                    if (lastDigit + k >= 10) {
                        maxNum = 8;
                    }
                    while (need > 0) {
                        int d = Math.min(maxNum, need);
                        maxNum = 9;
                        need -= d;
                        cur.append(d);
                    }
                    cur.reverse();
                    updateAns(ans, cur);
                }
            }
            out.println(ans);
        }
    }

    boolean updateAns(StringBuilder ans, StringBuilder cur) {
        if (ans.charAt(0) == '-') {
            ans.delete(0, ans.length());
            ans.append(cur);
            return true;
        } else {
            if (ans.length() > cur.length()) {
                ans.delete(0, ans.length());
                ans.append(cur);
                return true;
            } else if (ans.length() == cur.length()) {
                for (int i = 0; i < ans.length(); ++i) {
                    if (ans.charAt(i) > cur.charAt(i)) {
                        ans.delete(0, ans.length());
                        ans.append(cur);
                        return true;
                    } else if (ans.charAt(i) < cur.charAt(i)) {
                        return false;
                    }
                }
                return false;
            } else {
                return false;
            }
        }
    }
}
