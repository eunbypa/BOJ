import java.util.*;
import java.io.*;

public class Main {
	static int N,K;
	static int[][] dp;
	static int[] weight;
	static int[] cost;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N= Integer.parseInt(st.nextToken());
		K= Integer.parseInt(st.nextToken());
		dp = new int[N+1][K+1];
		weight = new int[N+1];
		cost = new int[N+1];
		for (int i = 1; i < N+1; i++) {
			st = new StringTokenizer(br.readLine());
			weight[i] = Integer.parseInt(st.nextToken());
			cost[i] = Integer.parseInt(st.nextToken());
		}
		for (int i = 1; i < N+1; i++) {
			for (int j = 1; j < K+1; j++) {
				if(weight[i] > j) { // 현재 담을 수 있는 무게보다 더 크므로 담을 수 없다
					dp[i][j] = dp[i-1][j];
					continue;
				}
				dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-weight[i]]+cost[i]);
			}
		}
		
		System.out.println(dp[N][K]);
	}
}