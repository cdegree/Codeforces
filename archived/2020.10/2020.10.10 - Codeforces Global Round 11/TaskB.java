package current;

import fastio.InputReader;

import java.io.PrintWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.PriorityQueue;
import java.util.TreeSet;


public class TaskB {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int T = in.nextInt();
        while (T-- > 0) {
            int n = in.nextInt();
            int k = in.nextInt();
            char[] s = in.nextLine().toCharArray();
            int lastWindex = -1;
            PriorityQueue<Integer> intervals = new PriorityQueue<>();
            int res = 0;
            for (int i = 0; i < s.length; ++i) {
                if (s[i] == 'W') {
                    ++res;
                    if (lastWindex == -1) {
                        lastWindex = i;
                    } else {
                        if (i - lastWindex - 1 > 0) {
                            intervals.add(i - lastWindex - 1);
                        } else {
                            ++res;
                        }
                        lastWindex = i;
                    }
                }
            }
            //System.out.println("res = " + res);
            if (res == 0) {
                res = Math.max(0, k * 2 - 1);
            } else {
                while(!intervals.isEmpty()) {
                    int x = intervals.poll();
                    if (k >= x) {
                        k -= x;
                        res += x * 2 + 1;
                    }
                }
                res += k * 2;
                res = Math.min(res, 2 * n - 1);
            }
            out.println(res);
        }
    }
}
