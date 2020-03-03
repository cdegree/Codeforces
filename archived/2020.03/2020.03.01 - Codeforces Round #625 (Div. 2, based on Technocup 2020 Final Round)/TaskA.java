package current;

import fastio.InputReader;

import java.io.PrintWriter;
import java.io.IOException;
import java.util.InputMismatchException;


public class TaskA {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int[] a = in.nextIntArray(n);
        int[] b = in.nextIntArray(n);
        int cntA=0;
        int cntB=0;
        int cntCommon=0;
        for(int i=0;i<n;++i){
            if(a[i]==1&&b[i]==1)++cntCommon;
            if(a[i]==1&&b[i]==0)++cntA;
            if(a[i]==0&&b[i]==1)++cntB;
        }
        if(cntA==0){
            out.println(-1);
        }
        else{
            int res=1;
            while(res*cntA<=cntB)++res;
            out.println(res);
        }
    }
}
