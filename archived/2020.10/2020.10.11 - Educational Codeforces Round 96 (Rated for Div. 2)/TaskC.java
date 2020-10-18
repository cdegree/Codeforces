package current;

import fastio.InputReader;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.InputMismatchException;



public class TaskC {
    public void solve(int testNumber, InputReader in, PrintWriter out){
        int T = in.nextInt();
        while (T-- > 0) {
            int n =in.nextInt();
            out.println(2);
            out.println((n-1)+" "+n);
            for(int i=n-1;i>1;--i){
                out.println((i-1)+" "+(i+1));
            }
        }
    }
}
