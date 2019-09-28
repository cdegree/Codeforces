package current;

import fastio.InputReader;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.InputMismatchException;



public class TaskA {
    public void solve(int testNumber, InputReader in, PrintWriter out){
        int n = in.nextInt();
        int[] a = new int[n];
        int lastLen = 0;
        int curLen = 0;
        int lastV = 0;
        int curV = 0;
        int best = 0;
        for(int i=0;i<n;++i){
            a[i] = in.nextInt();
        }
        for(int i=0;i<n;++i){
            int v = a[i];
            if(v==curV){
                curLen++;
            }
            else {
                if(lastV==0){

                }
                else{
                    best = Math.max(best, Math.min(curLen,lastLen)*2);
                }
                lastLen = curLen;
                curLen = 1;
                lastV = curV;
                curV = v;
            }
        }
        best = Math.max(best, Math.min(curLen,lastLen)*2);
        out.println(best);
    }
}
