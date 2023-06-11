import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static final int max_value = 10000000; // 초기화에 필요한 최댓값
    static StringBuilder sb = new StringBuilder();
    static int n, m;
    static int[][] dp; // 각 도시 사이 이동에 필요한 최소 비용 저장
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine()); // 도시(정점)의 개수
        m = Integer.parseInt(br.readLine()); // 버스(간선)의 개수
        dp = new int[n+1][n+1];
        for (int i = 1; i <= n; i++) {
            Arrays.fill(dp[i], 10000000);
        }
        int a, b, c;
        StringTokenizer st;
        for (int t = 0; t < m; t++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken()); // 시작 도시
            b = Integer.parseInt(st.nextToken()); // 도착 도시
            c = Integer.parseInt(st.nextToken()); // 필요 비용
            dp[a][b] = Math.min(dp[a][b], c);
        }
        floyd_warshall();
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if(dp[i][j] == max_value) sb.append(0);
                else sb.append(dp[i][j]);
                if(j == n) break; // 마지막은 공백 x
                sb.append(" "); // 사이 공백 넣기
            }
            sb.append("\n"); // 줄바꿈
        }
        System.out.println(sb.toString());
    }
    // 플로이드 워셜 알고리즘
    static void floyd_warshall(){
        for (int i = 1; i <= n; i++) { // 중간점
            for (int j = 1; j <= n; j++) { // 시작점
                if(i==j) continue;
                for (int k = 1; k <= n; k++) { // 도착점
                    if(k == i || k == j) continue;
                    dp[j][k] = Math.min(dp[j][k], dp[j][i] + dp[i][k]);
                }
            }
        }
    }


}