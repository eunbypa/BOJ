import java.util.*;
import java.io.*;
public class Main {
	static int N;
	static int[][] cost;
	static int[][] dp;
	static int INF = Integer.MAX_VALUE/100;
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		cost = new int[N][N];
		dp = new int[1<<N][N];
		StringTokenizer st;
		for (int i = 0; i < N; i++) {
			 st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				cost[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		int ans = TSP(1, 0); // 0번 도시부터 시작
		System.out.println(ans);
	}
	// 외판원 순회
	static int TSP(int visited, int city) {
		if(visited == ((1<<N)-1)) { // N-1개의 도시를 다 탐방한 상태면
			if(cost[city][0] == 0) return INF;
			return cost[city][0];
		}
		if(dp[visited][city] != 0) return dp[visited][city];
		dp[visited][city] = INF;
		for (int i = 0; i < N; i++) {
			if(cost[city][i] == 0) continue; // 갈수  없음
			if((visited&(1<<i)) != 0) continue; // 이미 방문한 도시
			int res = TSP(visited|(1<<i), i)+cost[city][i];
			dp[visited][city] = Math.min(dp[visited][city], res);
		}
		return dp[visited][city];
	}
}