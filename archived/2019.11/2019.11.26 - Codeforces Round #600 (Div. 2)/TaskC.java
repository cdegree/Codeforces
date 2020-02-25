package current;

import fastio.InputReader;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.InputMismatchException;



public class TaskC {
    public void solve(int testNumber, InputReader in, PrintWriter out){
        int n = in.nextInt();
        int m  = in.nextInt();
        int[] a = in.readIntArray(1,n);
        Arrays.sort(a);
        long[] preSum = new long[n+1];
        for(int i=1;i<=n;++i){
            preSum[i] = preSum[i-1] + a[i];
        }
        long[] result = new long[n+1];
        for(int i=1;i<=n;++i){
            if(i<=m){
                result[i] = preSum[i];
            }
            else{
                result[i] = preSum[i] + result[i-m];
            }
        }
        for(int i=1;i<=n;++i){
            out.print(result[i]+" ");
        }
    }
}
