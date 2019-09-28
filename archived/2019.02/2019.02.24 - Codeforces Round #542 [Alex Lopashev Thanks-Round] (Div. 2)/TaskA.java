package current;

import fastio.InputReader;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.InputMismatchException;



public class TaskA {
    public void solve(int testNumber, InputReader in, PrintWriter out){
        int n = in.nextInt();
        int m = (n+1)/2;
        int[] a = new int[n];
        int p =0;
        int neg = 0;
        for(int i=0;i<n;++i){
            a[i] = in.nextInt();
            if(a[i]>0){
                ++p;
            }
            else if(a[i]<0){
                neg++;
            }
        }
        if(p>=m){
            out.println(1);
        }
        else if(neg>=m){
            out.println(-1);
        }
        else {
            out.println(0);
        }

    }
}
