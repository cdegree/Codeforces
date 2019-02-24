package current;

import fastio.InputReader;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.InputMismatchException;



public class TaskA {
    public void solve(int testNumber, InputReader in, PrintWriter out){
        int w = in.nextInt();
        int h = in.nextInt();
        int u1 = in.nextInt();
        int d1 = in.nextInt();
        int u2 = in.nextInt();
        int d2 = in.nextInt();

        for(int i=h;i>=0;--i){

            w += i;
            if(i==0){
                break;
            }
            if(i==d1){
                w -= u1;
            }
            if(w<0){
                w=0;
            }
            if(i==d2){
                w-=u2;
            }
            if(w<0){
                w=0;
            }
        }
        out.println(w);
    }
}
