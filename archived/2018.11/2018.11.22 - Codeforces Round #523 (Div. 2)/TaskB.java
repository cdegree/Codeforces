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
        int[] a = new int[n];
        int h = 0;
        int w = 0;
        long sum=0;
        for(int i=0;i<n;++i){
            a[i] = in.nextInt();
            sum += a[i];
        }
        Arrays.sort(a);
        long remove = 0;
        for(int i=0;i<n;++i){
            if(a[i]==h+1){
                ++h;
                remove += a[i] -1;
            }
            else if(a[i]>h+1){
                if(i==n-1){
                    remove += h;
                }
                else{
                    remove += a[i] -1;
                    ++h;
                }
            }
            else{
                remove += a[i] -1;
            }
        }
        out.println(remove);
    }
}
