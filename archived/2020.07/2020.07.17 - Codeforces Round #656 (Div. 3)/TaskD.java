package current;

import fastio.InputReader;

import java.io.PrintWriter;
import java.io.IOException;
import java.util.InputMismatchException;


public class TaskD {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int T = in.nextInt();
        while (T-- > 0) {
            int n = in.nextInt();
            char[] s = in.nextLine().toCharArray();
            out.println(getResult(s, 'a', 0, n - 1));
        }
    }

    int getResult(char[] s, char c, int left, int right) {
        if (left == right) {
            return s[left] == c ? 0 : 1;
        }
        int leftNeed = 0;
        int rightNeed = 0;
        int mid = (left + right) / 2;
        for (int i = left; i <= mid; ++i) {
            if (c != s[i]) {
                ++leftNeed;
            }
        }
        for (int i = mid + 1; i <= right; ++i) {
            if (c != s[i]) {
                ++rightNeed;
            }
        }
        char next = (char) (c + 1);
        return Math.min(leftNeed + getResult(s, next, mid + 1, right), rightNeed + getResult(s, next, left, mid));
    }
}
