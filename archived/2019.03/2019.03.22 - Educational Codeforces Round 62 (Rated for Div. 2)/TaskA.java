package current;

import fastio.InputReader;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.InputMismatchException;



public class TaskA {
    public void solve(int testNumber, InputReader in, PrintWriter out){
        int n  = in.nextInt();
        int[] a = new int[n+1];
        for(int i=1;i<=n;++i){
            a[i] = in.nextInt();
        }
        int sum=0;
        int mx = 0;
        int i = 1;
        while(i<=n){
            if(mx<a[i]){
                mx = a[i];
            }
            if(i==mx){
                ++sum;
            }
            ++i;
        }
        out.println(sum);
    }
}
