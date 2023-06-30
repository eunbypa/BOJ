import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int n, k;
    static int[] dp;
    static int[] costs;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        dp = new int[k+1];
        Arrays.fill(dp, 10001);
        costs = new int[n];
        int cost;
        for (int i = 0; i < n; i++) {
            cost = Integer.parseInt(br.readLine());
            costs[i] = cost;
            if(cost <= k) dp[cost] = 1;
        }
        for (int i = 1; i <= k; i++) {
            for (int j = 0; j < n; j++) {
                if(costs[j] > i) continue; // 선택하는 동전의 가치가 현재 만드려는 값보다 큰 경우
                dp[i] = Math.min(dp[i], dp[i - costs[j]] + 1);
            }
        }
        int answer = (dp[k] == 10001) ? -1 : dp[k];
        System.out.println(answer);
    }

}