package current;

import fastio.InputReader;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.InputMismatchException;



public class TaskA {
    public void solve(int testNumber, InputReader in, PrintWriter out){
        int T = in.nextInt();
        while(T-->0){
            int a = in.nextInt();
            int b = in.nextInt();
            int cnt=0;
            int[] vol = {5,2,1};
            int diff = Math.abs(a-b);
            cnt += diff/5;
            diff %=5;
            cnt += diff/2;
            diff %= 2;
            cnt += diff;
            out.println(cnt);
        }
    }
}
