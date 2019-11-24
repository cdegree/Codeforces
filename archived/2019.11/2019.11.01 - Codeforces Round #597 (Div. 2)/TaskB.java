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
            int a = in.nextInt();
            int b = in.nextInt();
            int c = in.nextInt();
            char[] s = in.next().toCharArray();
            int[] cnt = new int[3];
            for(int i=0;i<s.length;++i){
                if(s[i]=='R'){
                    cnt[0]++;
                }
                else if(s[i]=='P'){
                    cnt[1]++;
                }
                else{
                    cnt[2]++;
                }
            }
            int m = (n+1)/2;
            int w = 0;
            int rr = Math.min(cnt[0],b);
            int pp = Math.min(cnt[1],c);
            int ss= Math.min(cnt[2],a);
            w = rr+pp+ss;
            //out.println(w);
            if(w>=m){
                out.println("YES");
                char[] res = new char[n];
                for(int i=0;i<n;++i) {
                    if (s[i] == 'R') {
                        if (b > 0) {
                            --b;
                            res[i] = 'P';
                        }
                    } else if (s[i] == 'P') {
                        if(c>0){
                            --c;
                            res[i]='S';
                        }
                    } else {
                        if(a>0){
                            a--;
                            res[i]='R';
                        }
                    }
                }
                for(int i=0;i<n;++i){
                    if(res[i]==0){
                        if(a>0){
                            --a;
                            res[i]='R';
                        }
                        else if(b>0){
                            --b;
                            res[i]='P';
                        }
                        else{
                            --c;
                            res[i]='S';
                        }
                    }
                }
                out.println(res);
            }
            else{
                out.println("NO");
            }
        }
    }
}
