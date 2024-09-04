import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {

    static final int MOD_VALUE = 1000000007;

    static int N,M;
    static int[] dp;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        dp = new int[N + 1];
        findPossibleAnswer();
        System.out.println(dp[N]);
    }

    static void findPossibleAnswer() {
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= N; i++) {
            if(i < M)
                dp[i] = dp[i - 1] % MOD_VALUE;
            else {
                dp[i] = (dp[i-1] % MOD_VALUE + dp[i-M] % MOD_VALUE) % MOD_VALUE;
            }
        }
    }

}