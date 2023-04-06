import java.util.*;
import java.io.*;

public class Main {
	static int N, M, W;
	static int[] weights;
	static int[][] dp;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		weights = new int[N+1];
		 st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			weights[i] = Integer.parseInt(st.nextToken());
			W += weights[i];
		}
		dp = new int[N+1][W+1];
		find(0,0);
		M = Integer.parseInt(br.readLine());
		int n;
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++) {
			n = Integer.parseInt(st.nextToken());
			if(n <= W && dp[N][n] == 1) sb.append("Y");
			else sb.append("N");
			if(i != M-1) sb.append(" ");
		}
		System.out.println(sb.toString());
	}

	static void find(int idx, int sum) {
		if(idx > N || dp[idx][sum] == 1) {
			return;
		}
		dp[idx][sum] = 1;
		//더하거나
		find(idx+1, sum+weights[idx]);
		//빼거나
		find(idx+1, Math.abs(sum-weights[idx]));
		//안하거나
		find(idx+1, sum);
	}
}