package current;

import fastio.InputReader;

import java.io.PrintWriter;
import java.io.IOException;
import java.util.InputMismatchException;

public class TaskC {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();

        int a[] = in.readIntArray(0, n);
        int b[] = in.readIntArray(0, m);
        long x = in.nextLong();

        int minR[] = calMin(a, n);
        int minC[] = calMin(b, m);

        int res=0;
        for(int l1=1;l1<=n;++l1){
            for(int l2=1;l2<=m;++l2){
                if( 1L*minR[l1]*minC[l2]<=x ){
                    res = Math.max(res,l1*l2);
                }
            }
        }
        out.println(res);

    }

    public int[] calMin(int[] a, int n) {
        int minOfLen[] = new int[n+1];
        for (int len = 1; len <= n; ++len) {
            int sum = 0;
            for (int i = 0; i < len; ++i) {
                sum += a[i];
            }
            minOfLen[len] = sum;
            for (int i = 0; i < n - len; ++i) {
                if (i + len < n) {
                    sum -= a[i];
                    sum += a[i + len];
                    minOfLen[len] = Math.min(minOfLen[len], sum);
                }
            }
        }
        return minOfLen;
    }
}
