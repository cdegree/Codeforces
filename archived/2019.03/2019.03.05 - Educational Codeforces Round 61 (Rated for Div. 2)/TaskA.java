package current;

import fastio.InputReader;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.InputMismatchException;



public class TaskA {
    public void solve(int testNumber, InputReader in, PrintWriter out){
        int[] cnt = new int[4];
        for(int i=0;i<4;++i){
            cnt[i] = in.nextInt();
        }
        boolean ok=false;
        if(cnt[0]==cnt[3]){
            if(cnt[2]>0){
                if(cnt[0]>0){
                    ok=true;
                }
                else {

                }
            }
            else {
                ok=true;
            }
        }
        else {

        }
        out.print(ok?1:0);
    }
}
