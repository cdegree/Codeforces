package current;

import fastio.InputReader;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.InputMismatchException;



public class TaskA {
    public void solve(int testNumber, InputReader in, PrintWriter out){
        int n = in.nextInt();
        int[] c = in.readIntArray(n);
        if(c[0]==c[n-1]){
            int i=0;
            int j=n-1;
            while(c[0]==c[i])++i;
            while(c[n-1]==c[j])--j;
            int mx = Math.max(n-1-i,j);
            out.println(mx);
        }
        else{
            out.println(n-1);
        }
    }
}
