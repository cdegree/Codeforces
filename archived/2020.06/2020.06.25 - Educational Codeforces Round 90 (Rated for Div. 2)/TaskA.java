package current;

import fastio.InputReader;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.InputMismatchException;



public class TaskA {
    public void solve(int testNumber, InputReader in, PrintWriter out){
        int tt = in.nextInt();
        while(tt-->0){
            int a = in.nextInt();
            int b = in.nextInt();
            int c = in.nextInt();
            int left = -1;
            int right = -1;
            if((long)a*b>c)right = b;
            if(a<c)left = 1;
            out.println(left+" "+right);
        }
    }
}
