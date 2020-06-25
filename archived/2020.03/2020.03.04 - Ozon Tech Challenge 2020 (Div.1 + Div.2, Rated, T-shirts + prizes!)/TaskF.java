package current;

import algorithms.MathUtil;
import fastio.InputReader;

import java.io.PrintWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.TreeMap;


public class TaskF {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        long[] a = in.nextLongArray(n);
        Random r = new Random(n);
        long res = n;
        for (int it = 0; it < 30; ++it) {
            int idx = r.nextInt(n);
            //out.println("idx = " + idx);
            res = Math.min(res, getAns(a, a[idx]));
            res = Math.min(res, getAns(a, a[idx] + 1));
            res = Math.min(res, getAns(a, a[idx] - 1));
        }
        out.println(res);
    }

    long getAns(long[] a, long x) {
        TreeMap<Long, Integer> primes = MathUtil.getPrimeDivisors(x);
        long ret = a.length;
        //System.out.println("primes of "+x);
        for (long prime : primes.keySet()) {
            //System.out.println(prime+" - ");
            ret = Math.min(ret, getOperations(a, prime));
        }
        return ret;
    }

    long getOperations(long[] a, long d) {
        long ret = 0;
        for (int i = 0; i < a.length; ++i) {
            long low = a[i] - a[i] % d;
            long high = a[i] + d - a[i] % d;
            if (low == 0) {
                low = high;
            }
            ret += Math.min( Math.abs(a[i] - low), high - a[i]);
        }
        //System.out.println("d " + d + " = " + ret);
        return ret;
    }

}
