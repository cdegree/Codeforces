package current;

import fastio.InputReader;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;


public class TaskA {
    public void solve(int testNumber, InputReader in, PrintWriter out){
        int T = in.nextInt();
        while(T-->0){
            int a = in.nextInt();
            List<Integer> list = new ArrayList<>();
            int e = 1;
            while(a>0){
                if(a%10 != 0){
                    list.add(a % 10 * e);
                }
                a /= 10;
                e *= 10;
            }
            out.println(list.size());
            for(int x: list){
                out.print(x+" ");
            }
            out.println();
        }
    }
}
