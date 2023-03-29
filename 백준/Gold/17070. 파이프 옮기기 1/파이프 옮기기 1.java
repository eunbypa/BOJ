import java.util.*;
import java.io.*;
public class Main {
	static int N, ans;
	static int[][] map;
	static int[][][] dp; // 현재 위치까지 옮긴 파이프 경우의 수
	static int[] dr = {0,1,1}; // 02
	static int[] dc ={1,0,1}; // 13
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		map = new int[N+1][N+1];
		dp = new int[N+1][N+1][3];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				Arrays.fill(dp[i][j], -1);
			}
		}
		//파이프 초기 위치
		map[1][1] = 2;
		map[1][2] = 2;
		System.out.println(dfs(1,2, 0));
	}
	static int dfs(int r, int c, int d) {
		//System.out.println(r+" "+c);
		if(dp[r][c][d] != -1) return dp[r][c][d];
		if(d == 0) { //가로인경우
			for (int i = 0; i < 3; i++) {
				if(i == 1) continue;
				int nr = r + dr[i];
				int nc = c+dc[i];
				if(!check(nr,nc)) continue; // 범위 밖
				if(i == 2) { // 대각선일때는 벽을 3부위에서 검사해줘야 함
					if(map[nr-1][nc] == 1|| map[nr][nc-1] == 1||map[nr][nc] == 1) continue; // 벽
				}else {
					if(map[nr][nc] == 1) continue; // 벽
				}
				if(dp[r][c][d] == -1) dp[r][c][d] = 0;
				dp[r][c][d] += dfs(nr,nc,i);
			} 
		}else if(d==1) { // 세로인경우
			for (int i = 0; i < 3; i++) {
				if(i == 0) continue;
				int nr = r + dr[i];
				int nc = c+dc[i];
				if(!check(nr,nc)) continue; // 범위 밖
				if(i == 2) { // 대각선일때는 벽을 3부위에서 검사해줘야 함
					if(map[nr-1][nc] == 1|| map[nr][nc-1] == 1||map[nr][nc] == 1) continue; // 벽
				}else {
					if(map[nr][nc] == 1) continue; // 벽
				}
				if(dp[r][c][d] == -1) dp[r][c][d] = 0;
				dp[r][c][d] += dfs(nr,nc,i);
			} 
		}else { // 대각선인경우
			for (int i = 0; i < 3; i++) {
				int nr = r + dr[i];
				int nc = c+dc[i];
				if(!check(nr,nc)) continue; // 범위 밖
				if(i == 2) { // 대각선일때는 벽을 3부위에서 검사해줘야 함
					if(map[nr-1][nc] == 1|| map[nr][nc-1] == 1||map[nr][nc] == 1) continue; // 벽
				}else {
					if(map[nr][nc] == 1) continue; // 벽
				}
				if(dp[r][c][d] == -1) dp[r][c][d] = 0;
				dp[r][c][d] += dfs(nr,nc,i);
			} 
		}
		if(r==N && c == N) return 1; // 도착
		if(dp[r][c][d] == -1) { // 도착 불가능을 의미
			dp[r][c][d] = 0;
		}
		return dp[r][c][d];
	}
	static boolean check(int x, int y) {
		return x >= 1 && x <= N &&y>=1 && y <= N;
	}
}