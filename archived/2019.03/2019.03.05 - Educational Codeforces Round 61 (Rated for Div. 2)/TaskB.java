package current;

import com.sun.deploy.util.ArrayUtil;
import fastio.InputReader;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.InputMismatchException;



public class TaskB {
    public void solve(int testNumber, InputReader in, PrintWriter out){
        int n = in.nextInt();
        int[] a = new int[n];
        long sum=0;
        for(int i=0;i<n;++i){
            a[i] = in.nextInt();
            sum += a[i];
        }
        int m = in.nextInt();
        int[] q = new int[m];
        for(int i=0;i<m;++i){
            q[i] = in.nextInt();
        }
        Arrays.sort(a);
        for(int i=0;i<m;++i){
            int v = q[i];
            v = n - v;
            out.println(sum - a[v]);
        }

    }
}
