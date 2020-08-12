package current;

import algorithms.MathUtil;
import fastio.InputReader;

import java.io.PrintWriter;
import java.io.IOException;
import java.util.InputMismatchException;


public class TaskE {
    long total;

    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int T = in.nextInt();
        while (T-- > 0) {
            long m = in.nextLong();
            long d = in.nextLong();
            long w = in.nextLong();
            long min = Math.min(m, d);
            long gcd = MathUtil.getGCD(d - 1, w);
            w /= gcd;
            long cnt = min / w;
            long res = min * cnt - (1 + cnt) * cnt / 2 * w;
            out.println(res);
        }
    }
    // (D*(i - 1) + j )% w    ==  (D*(j - 1) + i )% w
    //  (Di - D + j)%w                (Dj - D + i)%w
    // Di-Dj+j-i =0
    //D(i-j)-(i - j)==0
    //(D-1)*(i-j)==0 % w
    // let u = w / gcd(D-1, w)
    // i - j ==0 % u
    // w * i <= min
    // cnt = min / w;
    // min - w * i

}
