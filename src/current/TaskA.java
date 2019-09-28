package current;

import fastio.InputReader;

import java.io.PrintWriter;
import java.io.IOException;
import java.util.InputMismatchException;


public class TaskA {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int t = in.nextInt();
        int[] s = new int[n];
        int[] d = new int[n];
        int leastTime = 100000;
        int id = 1;
        for (int i = 0; i < n; ++i) {
            s[i] = in.nextInt();
            d[i] = in.nextInt();
            int timeNeed = 10000000;
            if (s[i] >= t) {
                timeNeed = s[i] - t;
            } else {
                for (int j = 0; j <= 100000; ++j) {
                    if (j * d[i] + s[i] > 1000000) {
                        break;
                    }
                    if (j * d[i] + s[i] < t) {
                        continue;
                    }
                    timeNeed = Math.min(timeNeed, Math.abs(s[i] + j * d[i] - t));
                }
            }
//            out.println(i+" "+timeNeed);
            if (timeNeed < leastTime) {
                id = i + 1;
                leastTime = timeNeed;
            }
        }
        out.println(id);


    }
}
