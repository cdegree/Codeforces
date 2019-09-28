package current;

import fastio.InputReader;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.InputMismatchException;



public class TaskB {
    public void solve(int testNumber, InputReader in, PrintWriter out){
        int n = in.nextInt();
        int m = in.nextInt();

        boolean[][] available = new boolean[2][m+1];
        for(int i=0;i<2;++i)
        Arrays.fill(available[i],false);
        for(int i=0;i<n;++i){
            int a = in.nextInt();
            a %= m;
            if(!available[(i + 1) & 1][0]) {
                for (int j = 0; j < m; ++j) {
                    if (available[i & 1][j]) {
                        available[(i + 1) & 1][(j + a) % m] = true;
                        available[(i + 1) & 1][j] = true;
                    }
                }
            }
            available[(i+1)&1][a]=true;

//            for(int j=0;j<m;++j){
//                out.print(available[(i+1)&1][j]+" ");
//            }
//            out.println();
        }
        out.println(available[n&1][0]?"YES":"NO");
    }
}
