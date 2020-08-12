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
            int k = in.nextInt();
            int l1 = in.nextInt();
            int r1 = in.nextInt();
            int l2 = in.nextInt();
            int r2 = in.nextInt();
            long left = 0;
            long right = 1000000000000000000L;
            long ans = -1;
            while (left <= right) {
                long mid = (left + right) / 2;
                if (judge(mid, n, k, l1, r1, l2, r2)) {
                    ans = mid;
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
            if (ans != -1) {
                out.println(ans);
            } else {
                while (true) ;
            }
        }
    }

    boolean judge(long steps, int n, int k, int l1, int r1, int l2, int r2) {
        long sum = 0;
        //System.out.println(steps + " ");
        int L = Math.min(l1, l2);
        int R = Math.max(r1, r2);
        long diff = l1 - L + l2 - L;
        diff += R - r1 + R - r2;
        if (r1 < l2) {
            for (int i = 0; i < n; ++i) {
                if (steps >= diff) {
                    steps -= diff;
                    sum += R - L;
                } else {
                    long op1 = steps / 2;
                    long op2 = 0;
                    steps -= l2 - r1;
                    if (steps > 0) {
                        op2 = steps;
                        steps = 0;
                    }
                    long add = op2;
                    if (i > 0) {
                        add = Math.max(op1, add);
                        steps = 0;
                    }
                    sum += add;
                    break;
                }
                if (steps <= 0) {
                    break;
                }
            }
            if (steps > 0) {
                sum += steps / 2;
                steps = 0;
            }
        } else if (r2 < l1) {
            for (int i = 0; i < n; ++i) {
                if (steps >= diff) {
                    steps -= diff;
                    sum += R - L;
                } else {
                    long op1 = steps / 2;
                    long op2 = 0;
                    steps -= l1 - r2;
                    if (steps > 0) {
                        op2 = steps;
                        steps = 0;
                    }
                    long add = op2;
                    if (i > 0) {
                        add = Math.max(op1, add);
                        steps = 0;
                    }
                    sum += add;
                    break;
                }
                if (steps <= 0) {
                    break;
                }
            }
            if (steps > 0) {
                sum += steps / 2;
                steps = 0;
            }
        } else {
            sum += (long) (Math.min(r1, r2) - Math.max(l1, l2)) * n;
            sum += Math.min(steps, (long) n * (R - L - (Math.min(r1, r2) - Math.max(l1, l2))));
            steps -= (long) n * (R - L - (Math.min(r1, r2) - Math.max(l1, l2)));
            if (steps > 0) {
                sum += steps / 2;
            }
        }
        //System.out.println(steps + " " + sum);
        return sum >= k;
    }
}
