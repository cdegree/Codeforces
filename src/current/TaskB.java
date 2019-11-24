package current;

import fastio.InputReader;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.InputMismatchException;



public class TaskB {
    public void solve(int testNumber, InputReader in, PrintWriter out){
        int T = in.nextInt();
        while(T-->0){
            int n = in.nextInt();
            int[] a = in.readIntArray(1,n);
            int[] b = new int[n+1];
            boolean[] occupied = new boolean[n+1];
            int base = 1;
            int pre = 0;
            boolean OK =true;
            for(int i=1;i<=n;++i){
                if(a[i]!=pre){
                    pre=a[i];
                    b[i] = a[i];
                    occupied[b[i]] = true;
                }
                else{
                    while(occupied[base]){
                        ++base;
                    }
                    if(base>n||base>a[i]){
                        OK=false;
                        break;
                    }
                    b[i] = base;
                    occupied[base] = true;
                }
            }
            if(OK){
                for(int i=1;i<=n;++i){
                    out.print(b[i]+" ");
                }
                out.println();
            }
            else{
                out.println(-1);
            }
        }
    }
}
