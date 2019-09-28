package current;

import fastio.InputReader;

import java.io.PrintWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.InputMismatchException;


public class TaskC {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        final int N = 10000002;
        int[] f = new int[N];
        int[] cnt = new int[N];
        for (int i = 0; i < n; ++i) {
            int a = in.nextInt();
            cnt[a]++;
        }
        boolean[] isPrime = new boolean[N];
        Arrays.fill(isPrime, true);
        for (int i = 2; i < N; ++i) {
            if (isPrime[i]) {
                f[i] += cnt[i];
                for (int j = i + i; j < N; j += i) {
                    isPrime[j] = false;
                    f[i] += cnt[j];
                }
            }
        }
        int[] sf = new int[N];
        for (int i = 1; i < N; ++i) {
            sf[i] = sf[i - 1] + f[i];
        }
        int m = in.nextInt();
        while (m-- > 0) {
            int l = in.nextInt();
            int r = in.nextInt();
            if(r>=N){
                r = N-1;
            }
            if(l>=N){
                l = N-1;
            }
            int res = sf[r] - sf[l - 1];
            out.println(res);
        }
    }
}
