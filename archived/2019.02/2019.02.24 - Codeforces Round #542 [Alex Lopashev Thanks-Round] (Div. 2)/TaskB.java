package current;

import fastio.InputReader;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.LinkedList;


public class TaskB {
    public void solve(int testNumber, InputReader in, PrintWriter out){
        int n = in.nextInt();
        LinkedList<Integer>[] h = new LinkedList[n*2+2];
        for(int i=1;i<=2*n;++i){
            int a = in.nextInt();
            if(h[a]==null){
                h[a] = new LinkedList<>();
            }
            h[a].add(i);
        }
        long res = 0;
        int p = 1;
        h[0] = new LinkedList<>();
        h[0].add(1);
        h[0].add(1);
        for (int i = 1; i <= n; ++i) {
            res += getDis(h[i-1].getFirst(),h[i-1].getLast(),h[i].getFirst(),h[i].getLast());
        }
        out.println(res);
    }
    int getDis(int a1,int a2,int b1,int b2){
        return Math.min(Math.abs(a1-b1)+Math.abs(a2-b2),Math.abs(a1-b2)+Math.abs(a2-b1)  );
    }
}
