package current;

import fastio.InputReader;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.LinkedList;


public class TaskE {
    public void solve(int testNumber, InputReader in, PrintWriter out){
        int n = in.nextInt();
        int k = in.nextInt();
        int[] a = new int[n];
        LinkedList<Integer>[] d = new LinkedList[n];
        long sum = 0;
        long mod = 998244353L;
        for(int i=0;i<n;++i){
            a[i] = in.nextInt();
            d[i] = new LinkedList<>();
        }
        long res=1;
        for(int i=0;i<n;++i){
            if(i+2<n&&a[i]!=-1&&a[i]==a[i+2]){
                out.println(0);
                return ;
            }
            if(a[i]==-1){
                int[] dv = new int[2];
                int dc = 0;
                if(i-2>=0&&a[i-2]!=-1){
                    dv[0] =  a[i-2];
                    d[i].add(a[i-2]);
                    ++dc;
                }
                if(i+2<n&&a[i+2]!=-1){
                    if(dv[0]!=a[i+2]){
                        ++dc;
                        dv[1] = a[i+2];
                        d[i].add(a[i+2]);
                    }
                }
            }
        }
        for(int i=0;i<n;++i){
            if(a[i]==-1){
                int able = k;
                able -= d[i].size();
                out.println(String.format("%d %d ",i,d[i].size()));
                if(i+2<n){
                    int able2 = k;
                    able2 -= d[i+2].size();
                    long r = able*able2;
                    long same = same(d[i],d[i+2]);
                    res *= r-same;
                }
                else {
                    res *= able;
                }
            }
        }
        out.println(res);
    }
    int same(LinkedList<Integer> a ,LinkedList<Integer> b){
        if(a.size()>0&&b.size()>0){
            int cnt=0;
            Collections.sort(a);
            Collections.sort(b);
            int i=0;
            int j=0;
            while(i<a.size()||j<b.size()){
                if(a.get(i)==b.get(j)){
                    ++i;
                    ++j;
                    ++cnt;
                }
                else if(a.get(i)<b.get(j)){
                    ++i;
                }
                else {
                    ++j;
                }
            }
            return cnt;
        }
        else{
            return 0;
        }
    }
}
