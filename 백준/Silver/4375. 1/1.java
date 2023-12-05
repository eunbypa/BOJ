import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input;
        boolean find;
        int cnt = 0;
        BigInteger bi;
        BigInteger bi2;
        while((input = br.readLine()) != null) {
            cnt = 0;
            find = false;
            N = Integer.parseInt(input);
            bi = BigInteger.ZERO;
            bi2 = new BigInteger(String.valueOf(N));
            while(!find) {
                cnt++;
                bi = bi.multiply(BigInteger.TEN).add(BigInteger.ONE);
                if(bi.mod(bi2).equals(BigInteger.ZERO)) {
                    find = true;
                    sb.append(cnt).append("\n");
                }
            }
        }

        System.out.println(sb.toString());
    }

}