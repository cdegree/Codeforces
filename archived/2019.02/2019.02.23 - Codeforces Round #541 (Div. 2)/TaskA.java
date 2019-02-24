package current;

import fastio.InputReader;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.InputMismatchException;



public class TaskA {
    public void solve(int testNumber, InputReader in, PrintWriter out){
        int w1 = in.nextInt();
        int h1 = in.nextInt();
        int w2 = in.nextInt();
        int h2 = in.nextInt();

        long res = (1L*h1+ h2 + 2)*2;
        res += Math.max(w1,w2)*2;
        out.println(res);

    }
}
