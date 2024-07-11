import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int[] sizes;
    static int[] dp; // idx 번째 까지 오름차순 최대 길이

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int size;
        sizes = new int[n];
        dp = new int[n];
        for (int i = 0; i < n; i++) {
            sizes[i] = Integer.parseInt(st.nextToken());
        }
        dp[0] = 1;
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if(sizes[j] >= sizes[i] ) {
                    if(dp[i] == 0) {
                        dp[i] = 1;
                    }
                    continue;
                }
                if(dp[i] < dp[j] + 1) {
                    dp[i] = dp[j] + 1;
                }
            }
        }
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            cnt = Math.max(cnt, dp[i]);
        }
        System.out.println(cnt);
    }


}