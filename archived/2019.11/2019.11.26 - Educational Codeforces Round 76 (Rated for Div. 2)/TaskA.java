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
            int x = in.nextInt();
            int a = in.nextInt();
            int b = in.nextInt();
            int result = Math.abs(a-b)+x;
            result = Math.min(result,n-1);
            out.println(result);
        }
    }
}
