
import java.util.*;
import java.io.*;
public class Main {
	static int N, ans = Integer.MAX_VALUE;
	static int[][] costs; 
	static int[] visited;
	static int start;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringTokenizer st;
		costs = new int[N][N];
		visited = new int[N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				costs[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		for (int i = 0; i < N; i++) {
			start = i;
			visited[i] = 1;
			dfs(i,1,0);
			visited[i] = 0;
		}
		
		System.out.println(ans);
	}

	public static void dfs(int cur, int cnt, int sum) { 
		if(cnt == N) {
			if(costs[cur][start] != 0) { // 출발지로 돌아오기 가능
				ans = Math.min(ans, sum+costs[cur][start]);
			}
			return;
		}
		for (int i = 0; i < N; i++) {
			if(costs[cur][i] == 0) continue;
			if(visited[i] == 1) continue;
			visited[i] = 1;
			dfs(i, cnt+1, sum + costs[cur][i]);
			visited[i] = 0;
		}
	}
}
