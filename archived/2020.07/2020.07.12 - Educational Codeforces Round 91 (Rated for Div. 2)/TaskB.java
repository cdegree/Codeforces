package current;

import fastio.InputReader;

import java.io.PrintWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Vector;


public class TaskB {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int T = in.nextInt();
        while (T-- > 0) {
            char[] s = in.nextLine().toCharArray();
            int[] cnt = new int[255];
            for (char ch : s) {
                cnt[ch]++;
            }
            Vector<Integer> index = new Vector<>();
            for (int i = 0; i < 255; ++i) {
                index.add(i);
            }
            index.sort(((o1, o2) -> cnt[o2] - cnt[o1]));
            char best = 0;
            if (index.get(0) == 'R') {
                best = 'P';
            } else if (index.get(0) == 'P') {
                best = 'S';
            } else {
                best = 'R';
            }
            for (int i = 0; i < s.length; ++i) {
                out.print(best);
            }
            out.println();
        }
    }
}
