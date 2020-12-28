package current;

import algorithms.ArrayUtils;
import fastio.InputReader;

import java.io.PrintWriter;
import java.io.IOException;
import java.util.InputMismatchException;


public class TaskD1 {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int[] a = in.nextIntArray(n);
        ArrayUtils.mergeSort(a);
        int[] b = new int[n];
        int j = n - 1;
        int i;
        for (i = n - 1; i >= 0; i -= 2) {
            b[i] = a[j--];
        }
        i += 2;
        int st = 0;
        int res = 0;
        while (i + 1 < n) {
            if (a[st] < b[i]) {
                b[i + 1] = a[st];
                ++res;
                ++st;
            }
            i += 2;
        }
        for (int k = 0; k < n; ++k) {
            if (b[k] == 0) {
                b[k] = a[st++];
            }
        }
        out.println(res);
        for(int k:b){
            out.print(k+" ");
        }
        out.println();
    }
}
