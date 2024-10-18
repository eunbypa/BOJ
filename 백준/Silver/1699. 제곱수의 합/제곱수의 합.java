import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static final int MAX_VALUE = 100000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        System.out.println(getAnswer(N));
    }

    // dp bottom-up
    static int getAnswer(int N) {
        int[] dp = new int[N + 1];
        init(dp, MAX_VALUE);
        dp[0] = 0;
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j*j <= i; j++) {
                dp[i] = Math.min(dp[i], dp[i - j * j] + 1);
            }
        }

        return dp[N];
    }

    static void init(int[] arr, int value) {
        int l = arr.length;

        for (int i = 0; i < l; i++) {
            arr[i] = value;
        }
    }

}