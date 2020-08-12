package current;

import fastio.InputReader;

import java.io.PrintWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.LinkedList;


public class TaskD {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int T = in.nextInt();
        while (T-- > 0) {
            int n = in.nextInt();
            char[] a = in.nextLine().toCharArray();
            int[] belong = new int[n];
            int cnt = 0;
            LinkedList<Integer> endWith0 = new LinkedList<>();
            LinkedList<Integer> endWith1 = new LinkedList<>();

            for (int i = 0; i < n; ++i) {
                if (a[i] == '0') {
                    if (endWith1.size() > 0) {
                        int pick = endWith1.removeLast();
                        belong[i] = belong[pick];
                        endWith0.addLast(pick);
                    } else {
                        endWith0.addLast(i);
                        belong[i] = ++cnt;
                    }
                } else {
                    if (endWith0.size() > 0) {
                        int pick = endWith0.removeLast();
                        belong[i] = belong[pick];
                        endWith1.addLast(pick);
                    } else {
                        endWith1.addLast(i);
                        belong[i] = ++cnt;
                    }
                }
            }
            out.println(cnt);
            for (int i = 0; i < n; ++i) {
                out.print(belong[i]+" ");
            }
            out.println();
        }
    }
}
