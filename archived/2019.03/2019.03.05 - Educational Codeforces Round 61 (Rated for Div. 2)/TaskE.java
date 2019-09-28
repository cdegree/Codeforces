package current;

import fastio.InputReader;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.InputMismatchException;



public class TaskE {
    public void solve(int testNumber, InputReader in, PrintWriter out){
        long tw = in.nextLong();
        int n = 8;
        long[] w = new long[8];
        for(int i=0;i<n;++i){
            w[i] = in.nextLong();
        }
        long bs = 0;
        for(int mask=0;mask<(1<<n);++mask){
            long s=0;
            for(int i=0;i<n;++i){
                if( ((mask>>i)&1)==1){
                    s += w[i];
                }
            }
            if(s<=tw){
                bs = s;
            }
        }
        out.println(bs);

    }
}
