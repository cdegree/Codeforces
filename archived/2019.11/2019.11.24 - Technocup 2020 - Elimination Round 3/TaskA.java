package current;

import fastio.InputReader;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.InputMismatchException;



public class TaskA {
    public void solve(int testNumber, InputReader in, PrintWriter out){
        int T = in.nextInt();
        while(T-->0){
            int n = in.nextInt();
            int left = 0;
            int right = 1000000000;
            for(int i=0;i<n;++i){
                int l = in.nextInt();
                int r= in.nextInt();
                left = Math.max(left,l);
                right = Math.min(right,r);
            }
            int diff = left-right;
            if(diff<0){
                out.println(0);
            }
            else{
                out.println(diff);
            }
        }
    }
}
