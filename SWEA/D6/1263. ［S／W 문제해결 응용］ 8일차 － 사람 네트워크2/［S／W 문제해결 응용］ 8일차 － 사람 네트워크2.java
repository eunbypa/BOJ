import java.util.*;
import java.io.*;
public class Solution {
	static int N,min;
	static int[][] dp;
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int T;
		T=Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		for(int test_case = 1; test_case <= T; test_case++)
		{
			min = Integer.MAX_VALUE;
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			dp = new int[N][N];
			for (int i = 0; i < N; i++) {
				Arrays.fill(dp[i], 10000);
				for (int j = 0; j < N; j++) {
					dp[i][j] = Integer.parseInt(st.nextToken());
					if(dp[i][j] == 0) dp[i][j] = 10000;
				}
			}
			floidWashall();
			min = findMinCC();
			sb.append("#").append(test_case).append(" ")
            .append(min).append("\n");
		}
		bw.write(sb.toString());
        bw.flush();
        bw.close();
	}
	static void floidWashall() {
		for (int k = 0; k< N;k++) {
			for (int i= 0; i < N; i++) {
				if(k==i) continue;
				for (int j = 0; j < N; j++) {
					if(j == k || j == i) continue;
					dp[i][j] = Math.min(dp[i][j], dp[i][k]+dp[k][j]);
				}
			}
		}
	}
	static int findMinCC() {
		int min = Integer.MAX_VALUE;
		int sum = 0;
		for (int i = 0; i < N; i++) {
			 sum = 0;
			for (int j = 0; j < N; j++) {
				if(i==j) continue;
				sum+=dp[i][j];
			}
			min = Math.min(min,sum);
		}
		return min;
	}
}