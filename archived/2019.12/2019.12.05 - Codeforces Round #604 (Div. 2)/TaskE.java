package current;

import algorithms.MathUtil;
import fastio.InputReader;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.InputMismatchException;



public class TaskE {
    long mod = 998244353;
    public void solve(int testNumber, InputReader in, PrintWriter out){
        int n = in.nextInt();
        long[] p = new long[n];
        for(int i=0;i<n;++i){
            p[i] = in.nextLong();
            p[i] = p[i]*MathUtil.getInv(100, mod)%mod;
        }
        long a = 0;
        long b = 1;
        long e = 1;
        for(int i=0;i<n;++i){
            a = (a + e)% mod;
            e = e * p[i] % mod;
            b = b * MathUtil.getInv(p[i],mod) % mod;
        }
        long ret = a * b % mod;
        out.println(ret);
    }
}
