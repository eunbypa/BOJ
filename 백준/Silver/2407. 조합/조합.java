import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int n,m;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        BigInteger a = new BigInteger("1");
        BigInteger b = new BigInteger("1");
        for (int i = n-m+1; i <= n; i++) {
            a = a.multiply(BigInteger.valueOf(i));
        }
        for (int i = 2; i <= m; i++) {
            b = b.multiply(BigInteger.valueOf(i));
        }
        System.out.println(a.divide(b));
    }

}