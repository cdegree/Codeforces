package current;

import fastio.InputReader;

import java.io.PrintWriter;


public class TaskA {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int tt = in.nextInt();

        while (tt-- > 0) {
            int n = in.nextInt();
            if(n%2==1)n--;
            out.println(n/2);
        }
    }
}
