package current;

import fastio.InputReader;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;


public class TaskC {
    public void solve(int testNumber, InputReader in, PrintWriter out){
        int T = in.nextInt();
        while(T-->0){
            int n = in.nextInt();
            int k = in.nextInt();
            int r = (k + n - 2) % (n - 1) + 1;
            int c = (k + n - 2) / (n - 1);

            out.println(1L * (c - 1) * n + r);
        }
    }
}
