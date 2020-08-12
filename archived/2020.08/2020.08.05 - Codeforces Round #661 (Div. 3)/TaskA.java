package current;

import fastio.InputReader;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.InputMismatchException;



public class TaskA {
    public void solve(int testNumber, InputReader in, PrintWriter out){
        int T = in.nextInt();
        while(T-->0){
            int n = in.nextInt();
            int[] a = in.nextIntArray(n);
            Arrays.sort(a);
            boolean gap = false;
            for(int i=1;i<n;++i){
                if(a[i]-a[i-1]>1){
                    gap=true;
                }
            }
            out.println(gap?"NO":"YES");
        }
    }
}
