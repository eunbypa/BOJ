
import java.util.*;
import java.io.*;
class Pair{
	int x; // x
	int y; // y
	int wall; // 뚫은 벽의 개수
	Pair(int x, int y, int wall){
		this.x = x;
		this.y = y;
		this.wall = wall;
	}
}
public class Main {
	static int N,M,ans = 10000000;
	static char[][] map;
	static int[][][] dp;
	static int[] mX = {-1, 0, 1, 0};
	static int[] mY = {0, -1, 0, 1};
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken()); 
		map = new char[N][M];
		dp = new int[N][M][2];
		for (int i = 0; i < N; i++) {
			map[i] = br.readLine().toCharArray();
			//Arrays.fill(dp[i], 10000000);
		}
		bfs();
		if(ans == 10000000) System.out.println(-1);
		else System.out.println(ans);
	}
	public static void bfs() {
		Queue<Pair> q = new LinkedList<>();
		dp[0][0][0] = 1;
		q.add(new Pair(0, 0, 0));
		while(!q.isEmpty()) {
			int cx = q.peek().x;
			int cy = q.peek().y;
			int wall = q.peek().wall;
			//System.out.println("x : " +cx + " y : "+ cy + " " + wall + " " +dp[cx][cy][wall]);
			q.poll();
			if(cx == N-1 && cy == M-1) {
				ans = Math.min(ans, dp[cx][cy][wall]);
				continue;
			}
			for (int i = 0; i < 4; i++) {
				int nx = cx + mX[i];
				int ny = cy + mY[i];
				if(nx < 0 || nx > N-1 || ny < 0 || ny > M-1) continue; // 범위 밖
				if(map[nx][ny] == '1' && wall == 1) continue; // 이미 벽을 한번 깬 상태
				if(map[nx][ny] == '1' && wall == 0) { // 벽을 한번 깨봄
					dp[nx][ny][wall+1] = dp[cx][cy][wall] + 1;
					q.add(new Pair(nx, ny, wall+1));
				}
				if(map[nx][ny] == '1') continue;
				//벽 아님
				if(dp[nx][ny][wall] == 0 || dp[nx][ny][wall] > dp[cx][cy][wall] + 1) {
					dp[nx][ny][wall] = dp[cx][cy][wall] + 1;
					q.add(new Pair(nx, ny, wall));
				}
			}
		}
	}
}
