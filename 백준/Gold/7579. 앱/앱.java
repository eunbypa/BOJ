import java.util.*;
import java.io.*;
public class Main {
	static int N,M,W;
	static int[][] dp;
	static int[] memory;
	static int[] cost;
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st =  new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		memory = new int[N+1];
		cost = new int[N+1];
		st =  new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			memory[i] = Integer.parseInt(st.nextToken());
		}
		st =  new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			cost[i] = Integer.parseInt(st.nextToken());
			W+=cost[i];
		}
		int min = 100000;
		//해당 비용에서 얻을 수 있는 최대의 메모리를 구하자.(knapsack 응용)
		dp = new int[N+1][W+1];
		for (int i = 1; i <= N; i++) {
			for (int j = 0; j <= W; j++) {
				if(j >= cost[i]){
					dp[i][j] = Math.max(dp[i][j], dp[i-1][j-cost[i]]+memory[i]);
				}
				dp[i][j] =Math.max(dp[i][j], dp[i-1][j]);
			}
		}
		for (int i = 0; i <= W; i++) {
			if(dp[N][i] >= M) {
				min = Math.min(min, i);
				break;
			}
		}
		System.out.println(min);
	}

}