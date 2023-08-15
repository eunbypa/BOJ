import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int n, k;
    static int[][] dp;
    static int mod = 1000000000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        dp = new int[n+1][k+1]; // k개를 더해서 합이 n인 경우
        for (int i = 0; i <= n; i++) {
            dp[i][1] = 1;
        }
        for (int i = 0; i <= n; i++) {
            for (int j = 2; j <= k; j++) {
                for (int l = 0; l <= n; l++) {
                    if(i+l > n) continue;
                    dp[i+l][j] += dp[i][j-1];
                    dp[i+l][j] %= mod;
                }
            }
        }
        System.out.println(dp[n][k]);
    }

}