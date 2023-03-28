import java.util.*;
import java.io.*;

public class Main {
	static int[][] dp;
	static int[][] cost;
	public static void main(String[] args) throws Exception, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		cost=new int[N+1][3];
		dp=new int[N+1][3];
		StringTokenizer st;
		for (int n = 1; n < N+1; n++) {
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < 3; i++) {
				cost[n][i] = Integer.parseInt(st.nextToken());
				if(n==1) dp[n][i] = cost[n][i];
			}
		}
		for (int n = 2; n < N+1; n++) {
			dp[n][0] = Math.min(dp[n-1][1]+cost[n][0], dp[n-1][2]+cost[n][0]);
			dp[n][1] = Math.min(dp[n-1][0]+cost[n][1], dp[n-1][2]+cost[n][1]);
			dp[n][2] = Math.min(dp[n-1][0]+cost[n][2], dp[n-1][1]+cost[n][2]);
		}
		int min = Integer.MAX_VALUE;
		for (int i = 0; i < 3; i++) {
			min = Math.min(min, dp[N][i]);
		}
		System.out.println(min);
	}
}