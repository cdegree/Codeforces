package current;

import fastio.InputReader;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.InputMismatchException;



public class TaskA {
    public void solve(int testNumber, InputReader in, PrintWriter out){
        int n = in.nextInt();
        for(int i=0;i<n;++i){
            long c = in.nextLong();
            long sum = in.nextLong();
            long base = sum / c;
            long cnt2 = sum % c;
            long cnt1 = c - cnt2;
            long result = base*base*cnt1 + cnt2*(base+1)*(base+1);
            out.println(result);
        }
    }
}
