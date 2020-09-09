package current;

import algorithms.ArrayUtils;
import fastio.InputReader;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.InputMismatchException;



public class TaskB {
    public void solve(int testNumber, InputReader in, PrintWriter out){
        int T =in.nextInt();
        while(T-->0){
            int n = in.nextInt();
            long k = in.nextLong();
            int[] a = in.nextIntArray(n);
            k %= 2;
            int max = ArrayUtils.maxElement(a);
            if(k==1){
                for(int i=0;i<n;++i){
                    a[i] = max - a[i];
                }
            }
            else{
                for(int i=0;i<n;++i){
                    a[i] = max - a[i];
                }
                max = ArrayUtils.maxElement(a);
                for(int i=0;i<n;++i){
                    a[i] = max - a[i];
                }
            }
            for(int i=0;i<n;++i){
                out.print(a[i]+" ");
            }
        }
    }
}
