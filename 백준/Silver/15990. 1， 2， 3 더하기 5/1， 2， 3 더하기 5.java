import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static final int SUM_CNT = 3;
    static final int MAX_VALUE = 100000;
    static final int MOD_VALUE = 1000000009;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[][] dp = new int[MAX_VALUE + 1][SUM_CNT+1];
        init(dp);
        int T = Integer.parseInt(br.readLine());
        int n, ans;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < T; i++) {
            n = Integer.parseInt(br.readLine());
            ans = 0;
            for (int j = 1; j <= SUM_CNT; j++) {
                ans = ((ans % MOD_VALUE) + (dp[n][j] % MOD_VALUE)) % MOD_VALUE;
            }
            sb.append(ans).append("\n");
        }

        System.out.println(sb.toString());
    }

    
    static void init(int[][] dp) {
        dp[1][1] = 1;
        dp[2][2] = 1;
        dp[3][1] = dp[2][2];
        dp[3][2] = dp[1][1];
        dp[3][3] = 1;
        for (int i = 4; i <= MAX_VALUE; i++) {
            for (int j = 1; j <= SUM_CNT; j++) {
                for (int k = 1; k <= SUM_CNT; k++) {
                    if(j == k) continue;
                    dp[i][j] = ((dp[i][j] % MOD_VALUE) + (dp[i - j][k] % MOD_VALUE)) % MOD_VALUE;
                }
            }
        }
    }


}