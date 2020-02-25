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
            int[] a = in.readIntArray(n);

            int[] pos = new int[n+1];

            for(int i=0;i<n;++i){
                pos[ a[i] ] = i;
            }
            char[] s = new char[n];
            int left = pos[1];
            int right= pos[1];
            s[0] = '1';
            for(int i=2;i<=n;++i){
                left = Math.min(left, pos[i]);
                right = Math.max(right, pos[i]);
                if(right - left + 1 == i){
                    s[i-1] = '1';
                }
                else{
                    s[i-1] = '0';
                }
            }
            out.println(s);
        }
    }
}
