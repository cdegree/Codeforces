package algorithms;
import java.io.*;

public class Mask {
    static boolean OK = true;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        int mask = toMask(br.readLine());
        int ip1 = toMask(br.readLine());
        int ip2 = toMask(br.readLine());
        if(!validMask(mask)){
            OK=false;
        }
        if(!OK){
            pw.println(1);
        }
        else{
            if((ip1&mask) == (ip2&mask)){
                pw.println(0);
            }
            else{
                pw.println(2);
            }
        }
        pw.flush();
    }

    static boolean validMask(int mask){
        return ((((-mask)&mask)-1)^mask) == -1;
    }


    static int toMask(String s) {
        String[] tokens = s.split("\\.");
        if(tokens.length !=4){
            //System.out.println("s ="+s+" length = " + tokens.length);
            //System.out.println("tokens = "+ tokens[0] );
            OK=false;
            return 0;
        }
        int[] a = new int[4];
        for (int i = 0; i < 4; ++i) {
            //System.out.println("len ="+tokens[i].length());
            boolean allDigits = true;
            for(int j=0;j<tokens[i].length();++j){
                if( !Character.isDigit( tokens[i].charAt(j))){
                    OK=false;
                    return 0;
                }
            }
            a[i] = Integer.parseInt(tokens[i]);
            if(a[i]>255){
                OK=false;
                return 0;
            }
            if(a[i]==0&&tokens[i].length()>1){
                //System.out.println("len ="+tokens[i].length());
                OK=false;
                return 0;
            }
        }
        int ret = a[0] << 24;
        ret |= a[1] << 16;
        ret |= a[2] << 8;
        ret |= a[3];
        //System.out.println("ret = "+ret);
        return ret;
    }
}
