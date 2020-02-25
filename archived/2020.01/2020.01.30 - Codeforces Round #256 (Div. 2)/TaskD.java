package current;

import fastio.InputReader;

import java.io.PrintWriter;
import java.io.IOException;
import java.util.InputMismatchException;


public class TaskD {

    long countLessThan(long n, long m, long x) {
        long cnt = 0;

        for (int i = 1; i <= n; ++i) {
            cnt +=  Math.min(m,( x - 1) / i);
        }
        return cnt;
    }

    long countLessThanOrEqualTo(long n, long m, long x) {
        long cnt = 0;
        for (int i = 1; i <= n; ++i) {
            cnt += Math.min(m,x / i);
        }
        return cnt;
    }


    public void solve(int testNumber, InputReader in, PrintWriter out) {
        long n = in.nextLong();
        long m = in.nextLong();
        long k = in.nextLong();

        long left = 1;
        long right = n * m;
        long result = 1;
        while (left <= right) {
            long mid = (left + right) / 2;
            long less = countLessThan(n, m, mid);
            long lessOrEqual = countLessThanOrEqualTo(n, m, mid);

            //out.println(mid + " :" + less + " " + lessOrEqual + " ");
            if (lessOrEqual < k) {
                left = mid + 1;
            } else if (lessOrEqual >= k) {
                if (less + 1 <= k) {
                    result = mid;
                    break;
                } else {
                    right = mid - 1;
                }
            }
        }
        out.print(result);
    }
}
