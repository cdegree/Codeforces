package current;

import fastio.InputReader;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.InputMismatchException;



public class TaskA {
    public void solve(int testNumber, InputReader in, PrintWriter out){
        int T = in.nextInt();
        while(T-->0){
            char[] s = in.next().toCharArray();
            int n = s.length;
            for(int i=0;i<n;++i){
                if(s[i]=='?'){
                    if(i>0){
                        if(i+1<n){
                            if(s[i+1]=='?'){
                                s[i]= getNext(s[i-1]);
                            }
                            else{
                                s[i] = getTheOther(s[i-1],s[i+1]);
                            }
                        }else{
                            s[i]= getNext(s[i-1]);
                        }
                    }
                    else{
                        if(i+1<n){
                            if(s[i+1]=='?'){
                                s[i]= 'a';
                            }
                            else{
                                s[i] = getNext(s[i+1]);
                            }
                        }else{
                            s[i]='a';
                        }
                    }
                }
            }
            boolean OK =true;
            for(int i=0;i<n-1;++i){
                if(s[i]==s[i+1]){
                    OK=false;
                }
            }
            if(OK) {
                out.println(s);
            }
            else{
                out.println(-1);
            }
        }
    }
    char getTheOther(char a,char b){
        for(char ch='a';ch<'d';++ch){
            if(ch==a||ch==b){
                continue;
            }
            else{
                return ch;
            }
        }
        return 'a';
    }
    char getNext(char a){
        return (char)(((a-'a')+1)%3+'a');
    }

}
