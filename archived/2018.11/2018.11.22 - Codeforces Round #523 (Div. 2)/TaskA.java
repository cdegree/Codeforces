package current;

import fastio.InputReader;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.InputMismatchException;



public class TaskA {
    public void solve(int testNumber, InputReader in, PrintWriter out){
        int n = in.nextInt();
        int S = in.nextInt();

        int res = (S+n-1)/n;
        out.println(res);
    }
}
