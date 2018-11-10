package algorithms;
/*


 */
public class RMQ {

    int[] a;
    int n;
    int k;
    int dp[][];

    public RMQ(int a[], int n) {
        this.a = a;
        this.n = n;
        this.k = (int) Math.ceil(Math.log(n) + 1);
        int dp[][] = new int[n][k + 1];
    }

    public void rmq() {
        for(int i=0;i<n;++i){
            dp[i] = new int[k+1];
            dp[i][0] = a[i];
        }
        for(int j=1;(1<<j)<=n;++j){
            for(int i=0;i+(1<<j)<n;++i) {
                if( a[ dp[i][j-1] ] <= a[ dp[i+(1<<(j-1))][j-1]  ] ){
                    dp[i][j] = dp[i][j-1];
                }
                else{
                    dp[i][j] = dp[i+(1<<(j-1))][j-1];
                }
            }
        }
    }

    int query(int l,int r){
        int j=0;
        while((1<<(j+1))<(r-l+1)){
            ++j;
        }
        if(a[ dp[l][j] ] <= a[ dp[r-(1<<j)+1][j] ] ){
            return dp[l][j];
        }
        else{
            return dp[r-(1<<j)+1][j];
        }
    }
}
