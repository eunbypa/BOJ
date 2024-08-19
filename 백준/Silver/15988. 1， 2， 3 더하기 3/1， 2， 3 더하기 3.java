import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static final int MAX_VALUE = 1000000;
    static final int MOD_VALUE = 1000000009;

    static int[] dp;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        int n;
        dp = new int[MAX_VALUE + 1];
        set();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < T; i++) {
            n = Integer.parseInt(br.readLine());
            sb.append(dp[n]).append("\n");
        }
        System.out.println(sb.toString());
    }

    public static void set() {
        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 4;
        for (int i = 4; i <= MAX_VALUE; i++) {
            dp[i] = ((((dp[i-1] % MOD_VALUE) + (dp[i-2] % MOD_VALUE)) % MOD_VALUE)
                    + (dp[i-3] % MOD_VALUE)) % MOD_VALUE;
        }
    }

}