import java.util.*;
import java.io.*;
public class Main {
	static int N;
	static int[][] map;
	static boolean[][] visited;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, -1, 0, 1};
	static int ans, min, max;
	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringTokenizer st;
		map = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(min == 0) min = map[i][j];
				else min = Math.min(min, map[i][j]);
				if(max == 0) max = map[i][j];
				else max = Math.max(max, map[i][j]);
			}
		}
		int cnt;
		for (int k = min-1; k < max; k++) {
			visited  = new boolean[N][N];
			cnt = 0; // 그룹
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if(map[i][j] > k && !visited[i][j]) {
						cnt++;
						bfs(i, j, k);
					}
				}
			}
			ans = Math.max(ans, cnt);
		}
		System.out.println(ans);
	}

	static void bfs(int rr, int cc, int g) {
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] {rr,cc});
		visited[rr][cc] = true;
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			int r = cur[0];
			int c = cur[1];
			for (int i = 0; i < 4; i++) {
				int nr = r + dr[i];
				int nc = c + dc[i];
				if(!check(nr,nc)) continue;
				if(map[nr][nc] > g && !visited[nr][nc]) {
					visited[nr][nc] = true;
					q.offer(new int[] {nr, nc});
				}
			}
		}
	}
	static boolean check(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < N;
	}
}