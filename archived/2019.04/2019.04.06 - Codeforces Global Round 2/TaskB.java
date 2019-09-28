package current;

import fastio.InputReader;

import java.io.PrintWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.InputMismatchException;


public class TaskB {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int h = in.nextInt();
        int[] a = in.readIntArray(n);
        int left = 0;
        int right = n - 1;
        int ans = 0;
        while (left <= right) {
            int mid = (left + right) / 2;
            int[] b = new int[mid + 1];
            for (int i = 0; i <= mid; ++i) {
                b[i] = a[i];
            }
            Arrays.sort(b,0,mid+1);
            if (ok(b, h, mid)) {
                ans = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        out.println(ans + 1);

    }

    boolean ok(int[] a, int h, int k) {
        long H = 0;
        for (int i = k; i >=0 ; i--) {
            int mh = a[i];
            --i;
            if (i >=0 && mh < a[i]) {
                mh = a[i];
            }
            H += mh;
        }
        //System.out.println(String.format("%d %d %d", k, h, H));
        return H <= h;
    }
}
