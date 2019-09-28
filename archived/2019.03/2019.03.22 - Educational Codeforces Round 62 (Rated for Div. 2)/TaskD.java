package current;

import fastio.InputReader;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.InputMismatchException;



public class TaskD {
    public void solve(int testNumber, InputReader in, PrintWriter out){
        int n = in.nextInt();
        long res= 0 ;
        for(int i=3;i<=n;++i){
            res += 1L*(i-1)*i;
        }
        out.println(res);
    }
}
