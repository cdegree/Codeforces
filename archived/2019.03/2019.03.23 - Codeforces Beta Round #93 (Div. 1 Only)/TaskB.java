package current;

import algorithms.KMP;
import fastio.InputReader;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.InputMismatchException;



public class TaskB {
    public void solve(int testNumber, InputReader in, PrintWriter out){
        char[] s = in.next().toCharArray();
        int[] p = KMP.getP(s);
        String no = "Just a legend";
        int n = s.length;
        if(p[n-1]==0){
            out.println(no);
        }
        else {
            int k = p[n-1];
            for(int i=1;i<n-1;++i){
                if(p[i-1]==k){
                    out.println(new String(s).substring(0,k));
                    return ;
                }
            }
            if(p[k-1]==0){
                out.println(no);
            }
            else {
                k = p[k-1];
                out.println(new String(s).substring(0,k));
            }
        }
    }
}
