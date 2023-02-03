import java.util.*;
import java.io.*;

public class Main {
	static int[][] bamboos; // 대나무 양
	static int n, ans;
	static int[][] dp; // 각 칸에서 최대 움직인 칸 수 저장
	static int[] moveR = {-1,0,1,0};
	static int[] moveC = {0,-1,0,1};
	//dp + dfs 문제?
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		StringTokenizer st;
		bamboos = new int[n][n];
		dp = new int[n][n];
		for (int i = 0; i < n; i++) {
			Arrays.fill(dp[i], -1);
		}
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				bamboos[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				ans = Math.max(ans, dfs(i, j, bamboos[i][j]));
			}
		}
		System.out.println(ans);
	}
	public static int dfs(int r, int c, int curBamboo) {
		if(dp[r][c] != -1) return dp[r][c];
		for (int i = 0; i < 4; i++) {
			int nr = r + moveR[i];
			int nc = c + moveC[i];
			if(nr < 0 || nr > n-1 || nc < 0 || nc > n-1) continue;
			if(curBamboo >= bamboos[nr][nc]) continue;
			dp[r][c] = Math.max(dp[r][c], 1+dfs(nr, nc, bamboos[nr][nc]));
		}
		if(dp[r][c] == -1) dp[r][c] = 1;
		return dp[r][c];
	}
}
