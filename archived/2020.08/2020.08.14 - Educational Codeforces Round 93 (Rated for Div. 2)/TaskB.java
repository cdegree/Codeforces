package current;

import fastio.InputReader;

import java.io.PrintWriter;
import java.io.IOException;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.Vector;


public class TaskB {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int T = in.nextInt();
        while (T-- > 0) {
            char[] s = in.nextLine().toCharArray();
            Vector<Integer> ones = new Vector<>();
            for (int i = 0; i < s.length; ++i) {
                if (s[i] == '1') {
                    int j = i;
                    while (j + 1 < s.length && s[j + 1] == '1') ++j;
                    ones.add(j - i + 1);
                    i = j;
                }
            }
            ones.sort(Comparator.reverseOrder());
            int res = 0;
            for (int i = 0; i < ones.size(); i += 2) {
                res += ones.get(i);
            }
            out.println(res);
        }
    }
}
