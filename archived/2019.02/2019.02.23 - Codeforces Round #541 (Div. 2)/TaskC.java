package current;

import fastio.InputReader;

import java.io.PrintWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.InputMismatchException;


public class TaskC {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; ++i) {
            a[i] = in.nextInt();
        }
        Arrays.sort(a);

        int[] b = new int[n];
        int left = 0;
        int right = n - 1;
        for (int i = 0; i < n; i += 2) {
            b[left++] = a[i];
            if (i + 1 < n) {
                b[right--] = a[i + 1];
            }
        }
        for(int i=0;i<n;++i){
            out.print(b[i]+" ");
        }
    }
}
