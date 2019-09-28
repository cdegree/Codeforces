package current;

import fastio.InputReader;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.LinkedList;


public class TaskB {
    public void solve(int testNumber, InputReader in, PrintWriter out){
        int t = in.nextInt();
        while(t-->0){
            int n = in.nextInt();
            char[] s = in.next().toCharArray();
            int res = n;
            for(int i=0;i<n;++i){
                if(s[i]=='>'){
                    res = Math.min(res, i);
                }
                else {
                    res = Math.min(res, n-i-1);
                }
            }
            out.println(res);
        }
    }
}
