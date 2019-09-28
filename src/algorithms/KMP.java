package algorithms;

public class KMP {
    static public int[] getP(char[] s){
        int n = s.length;
        int[] p = new int[n+1];
        for(int i=1;i<n;++i){
            int j = p[i-1];
            while(j>0&&s[i]!=s[j]){
                j = p[j-1];
            }
            if(s[i]==s[j]){
                ++j;
            }
            p[i] = j;
        }
        return p;
    }
}
