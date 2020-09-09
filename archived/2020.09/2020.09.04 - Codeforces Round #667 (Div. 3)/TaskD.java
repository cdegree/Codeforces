package current;

import fastio.InputReader;

import java.io.PrintWriter;
import java.io.IOException;
import java.util.InputMismatchException;


public class TaskD {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int T = in.nextInt();
        while (T-- > 0) {
            long n = in.nextLong();
            int s = in.nextInt();
            long ops = 0;
            while (sumOfDigits(n) > s) {
                long nx = next(n);
                ops += nx;
                n += nx;
            }
            out.println(ops);
        }
    }

    long next(long x) {
        long e = 10;
        while (x % e == 0) {
            e *= 10;
        }
        return e - x % e;
    }

    int sumOfDigits(long x) {
        int sum = 0;
        while (x > 0) {
            sum += x % 10;
            x /= 10;
        }
        return sum;
    }
}
