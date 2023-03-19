import java.util.*;
import java.io.*;

public class Main {
	static int n;
	static int[] wines;
	static int[][] dp;
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		wines = new int[n];
		//0 : n번째 포도주를 선택하지 않음, 1 : n번째 포도주를 선택했고, 그전까지 연속으로 선택된 포도주는 없음
		//2 : n번째 포도주를 선택했고, 그전에 연속으로 선택된 포도주가 존재함(ex -> 현재 2번째 포도주를 선택하려고 하는데 1번째 포도주도 선택한 상황)
		dp = new int[n][3];
		for (int i = 0; i < n; i++) {
			wines[i] = Integer.parseInt(br.readLine());
			if(i == 0) {
				dp[0][1] = wines[i];
				dp[0][2] = wines[i];
			}else if(i == 1) {
				dp[1][0] = dp[0][1];
				dp[1][1] = wines[i];
				dp[1][2] = dp[0][1] + wines[i];
			}else {
				dp[i][0] = Math.max(dp[i][0], Math.max(dp[i-1][2], Math.max(dp[i-1][0],dp[i-1][1])));
				dp[i][1] = dp[i-1][0]+wines[i];
				dp[i][2] = dp[i-1][1]+wines[i];
			}
		}
		int max = 0;
		for (int i = 0; i < 3; i++) {
			max = Math.max(max, dp[n-1][i]);
		}
		System.out.println(max);
	}
}
