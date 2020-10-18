package current;

import algorithms.ArrayUtils;
import fastio.InputReader;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.InputMismatchException;



public class TaskA {
    public void solve(int testNumber, InputReader in, PrintWriter out){
        int T = in.nextInt();
        while(T-->0){
            int n = in.nextInt();
            int[] a = in.nextIntArray(n);
            long sum = ArrayUtils.sum(a);
            if(sum==0){
                out.println("NO");
            }
            else{
                out.println("YES");
                ArrayUtils.mergeSort(a);
                if(sum>0){
                    ArrayUtils.reverse(a);
                }
                for(int x:a){
                    out.print(x+" ");
                }
                out.println();
            }
        }
    }
}
