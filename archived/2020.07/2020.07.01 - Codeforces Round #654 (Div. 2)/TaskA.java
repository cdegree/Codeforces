package current;

import fastio.InputReader;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.InputMismatchException;



public class TaskA {
    public void solve(int testNumber, InputReader in, PrintWriter out){
        int t=in.nextInt();
        while(t-->0){
            int n = in.nextInt();
            out.println((n+1)/2);
        }
    }
}
