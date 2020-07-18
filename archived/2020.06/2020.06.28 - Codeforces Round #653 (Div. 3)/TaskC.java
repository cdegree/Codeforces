package current;

import fastio.InputReader;

import java.io.PrintWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.LinkedList;


public class TaskC {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int tt = in.nextInt();
        while (tt-- > 0) {
            int n = in.nextInt();
            char[] s = in.nextLine().toCharArray();
            int left = 0;
            int right = 0;
            for (char ch : s) {
                if (ch == '(') {
                    ++left;
                } else {
                    if (left > 0) {
                        --left;
                    } else {
                        ++right;
                    }
                }
            }
            out.println((left + right) / 2);
        }
    }
}
