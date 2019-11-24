package current;

import fastio.InputReader;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.InputMismatchException;



public class TaskC {
    public void solve(int testNumber, InputReader in, PrintWriter out){
        char[] s = in.next().toCharArray();
        for(int i=0;i<s.length;++i){
            if(s[i]=='m'||s[i]=='w'){
                out.println(0);
                return;
            }
        }
        int n = s.length;
        int[] dp = new int[n+2];
        long mod = 1000000007L;
        dp[1]=1;
        dp[2] = 2;
        for(int i=3;i<=n;++i) {
            dp[i] = (int) ((0L + dp[i - 1] + dp[i - 2]) % mod);
        }
        long last = 1;
        for(int i=0;i<n;++i){
            if(s[i]=='u'||s[i]=='n') {
                int j = i;
                while(j+1<n&&s[j+1]==s[i]){
                    ++j;
                }
                last *= dp[j-i+1];
                last %= mod;
                i = j;
            }
        }
        out.println(last);
    }
}
