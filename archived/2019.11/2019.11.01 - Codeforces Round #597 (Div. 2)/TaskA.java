package current;

import fastio.InputReader;
import java.io.PrintWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.util.InputMismatchException;



public class TaskA {
    public void solve(int testNumber, InputReader in, PrintWriter out){
        int n = in.nextInt();

        while(n-->0){
            int a = in.nextInt();
            int b = in.nextInt();
            int g = gcd(a,b);
            if(g>1){
                out.println("Infinite");
            }
            else{
                out.println("Finite");
            }
            //out.println(g);
        }
    }
    int gcd(int a,int b){
        if(a<b){
            int t= a;
            a = b;
            b = t;
        }
        if(b==0){
            return a;
        }
        return gcd(b,a%b);
    }
}
