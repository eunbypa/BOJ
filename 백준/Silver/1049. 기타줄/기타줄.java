import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static final int MAX_VALUE = 1000000;
    static int N, M;

    static int[][] brandPriceArr; // 0 : 패키지, 1 : 낱개

    static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        brandPriceArr = new int[M][2];
        int price;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 2; j++) {
                price = Integer.parseInt(st.nextToken());
                brandPriceArr[i][j] = price;
            }
        }
        dp = new int[N+6];
        for (int i = 1; i < N+6; i++) {
            dp[i] = MAX_VALUE;
        }

        for (int i = 1; i < N+6; i++) {
            for (int j = 0; j < M; j++) {
                if(dp[i] == MAX_VALUE)
                    dp[i] = dp[i - 1] + brandPriceArr[j][1];
                // 1개씩 구매하는 경우
                dp[i] = Math.min(dp[i], dp[i - 1] + brandPriceArr[j][1]);
                if(i < 6) continue;
                dp[i] = Math.min(dp[i], dp[i - 6] + brandPriceArr[j][0]);
            }
        }

        int min = MAX_VALUE;
        for (int i = N; i < N+6; i++) {
            min = Math.min(min, dp[i]);
        }

        System.out.println(min);
    }

}