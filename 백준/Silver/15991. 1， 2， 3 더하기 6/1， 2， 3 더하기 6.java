import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static final int MAX_VALUE = 100000;
    static final int MOD_VALUE = 1000000009;

    static int[] dp;
    static int[] dp2;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        int n;
        dp = new int[MAX_VALUE + 1];
        dp2 = new int[MAX_VALUE + 1];

        set();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < T; i++) {
            n = Integer.parseInt(br.readLine());
            sb.append(dp[n]).append("\n");
        }
        System.out.println(sb.toString());
    }

    // dp 점화식에 따라 배열값 세팅
    public static void set() {
        dp2[1] = 1;
        dp2[2] = 2;
        dp2[3] = 4;

        for (int i = 4; i <= MAX_VALUE; i++) {
            dp2[i] = ((dp2[i - 1] % MOD_VALUE + dp2[i - 2] % MOD_VALUE) % MOD_VALUE + dp2[i - 3] % MOD_VALUE) % MOD_VALUE;
        }

        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 2;


        for (int i = 4; i < MAX_VALUE; i+=2) {
            dp[i] = (dp2[(i - 2) / 2] % MOD_VALUE + dp2[i / 2] % MOD_VALUE) % MOD_VALUE;
            dp[i + 1] = (dp2[(i - 2) / 2] % MOD_VALUE + dp2[i / 2] % MOD_VALUE) % MOD_VALUE;
        }
        dp[MAX_VALUE] = (dp2[(MAX_VALUE - 2) / 2] % MOD_VALUE + dp2[MAX_VALUE / 2] % MOD_VALUE) % MOD_VALUE;

    }


}