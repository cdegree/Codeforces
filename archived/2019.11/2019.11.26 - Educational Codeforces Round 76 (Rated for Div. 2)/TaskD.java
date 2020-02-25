package current;

import algorithms.RMQ;
import fastio.InputReader;

import java.io.PrintWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.Vector;


public class TaskD {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int T = in.nextInt();
        while (T-- > 0) {
            int n = in.nextInt();
            int[] a = in.readIntArray(n);
            int m = in.nextInt();
            Hero[] heroes = new Hero[m];
            for (int i = 0; i < m; ++i) {
                int p = in.nextInt();
                int s = in.nextInt();
                heroes[i] = new Hero(p, s);
            }
            Arrays.sort(heroes, (o1, o2) -> {
                return o2.s - o1.s;
            });
            int[] best = new int[n + 2];
            int largestDuration = heroes[0].s;
            best[largestDuration] = heroes[0].p;
            int idx = 0;
            for (int i = n; i >= 1; --i) {
                best[i] = Math.max(best[i], best[i + 1]);
                while (idx < m && heroes[idx].s == i) {
                    best[i] = Math.max(best[i], heroes[idx].p);
                    ++idx;
                }
            }
            for (int i = 1; i <= n; ++i) {
                //out.println("best " + i + " = " + best[i]);
            }
            idx = 0;
            int result = 0;
            boolean OK = true;
            while (idx < n) {
                int monstersToKill = 0;
                int mx = 0;
                while (idx+monstersToKill<n) {
                    mx = Math.max(mx, a[idx + monstersToKill]);
                    if (mx > best[monstersToKill + 1]) {
                        break;
                    } else {
                        monstersToKill++;
                    }
                }
                if (monstersToKill == 0) {
                    OK = false;
                    break;
                } else {
                    result++;
                    idx += monstersToKill;
                }
            }
            out.println(OK ? result : -1);
        }
    }

    public class Hero {
        int p, s;

        public Hero(int p, int s) {
            this.p = p;
            this.s = s;
        }
    }
}
