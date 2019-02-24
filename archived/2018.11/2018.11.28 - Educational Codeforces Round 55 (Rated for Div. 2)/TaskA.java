package current;

import fastio.InputReader;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.InputMismatchException;



public class TaskA {
    public void solve(int testNumber, InputReader in, PrintWriter out){
        int t = in.nextInt();
        while(t-->0){
            int n = in.nextInt();
            int x = in.nextInt();
            int y = in.nextInt();
            int d = in.nextInt();
            long res=-1;
            if(Math.abs(x-y)%d==0){
                res=Math.abs(x-y)/d;
            }
            else{
                int len = x-1;
                int to1 = (len+d-1)/d;
                if(Math.abs(1-y)%d==0){
                    res=Math.abs(1-y)/d+to1;
                }
                len = n-x;
                int ton =(len+d-1)/d;
                if(Math.abs(n-y)%d==0){
                    if(res==-1) {
                        res = Math.abs(n - y) / d + ton;
                    }
                    else{
                        res = Math.min(res, Math.abs(n - y) / d + ton);
                    }
                }
            }
            out.println(res);
        }
    }
}
