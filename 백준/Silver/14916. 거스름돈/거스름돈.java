import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int[] dp;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        dp = new int[n + 1];
        if(n >= 2) dp[2] = 1;
        if(n >= 4) dp[4] = 2;
        if(n >= 5) dp[5] = 1;
        for (int i = 6; i <= n; i++) {
            if(dp[i-2] == 0 && dp[i-5] == 0) continue;
            if(dp[i-2] == 0)
                dp[i] = dp[i - 5] + 1;
            else if(dp[i-5] == 0)
                dp[i] = dp[i - 2] + 1;
            else
                dp[i] = Math.min(dp[i - 2] + 1, dp[i - 5] + 1);
        }
        int ans = (dp[n] == 0) ? -1 : dp[n];
        System.out.println(ans);
    }

}