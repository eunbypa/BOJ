import java.util.*;
import java.io.*;

public class Main {
	static int H, W, removeCnt, total, time;
	static int[][] map;
	static boolean[][] visited;
	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,-1,0,1,};
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		H = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		map = new int[H][W];
		for (int i = 0; i < H; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < W; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 1) total++;
			}
		}
		int[][] copy = new int[H][W];
		while(total > 0) {
			time++;
			bfs();
			removeCnt = 0;
			for (int i = 0; i < H; i++) {
				copy[i] = Arrays.copyOf(map[i], W);
			}
			removeCheeze(copy);
			total -= removeCnt;
		}
		System.out.println(time);
		System.out.println(removeCnt);
	}
	public static void removeCheeze(int[][] copy) {
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				if(copy[i][j] == 2 || copy[i][j] == 0) continue;
				int cnt = 0;
				for (int j2 = 0; j2 < 4; j2++) {
					if(!check(i+dr[j2],j+dc[j2])) continue;
					if(copy[i+dr[j2]][j+dc[j2]] == 2) cnt++;
				}
				if(cnt >= 1) {
					copy[i][j] = 3;
					removeCnt++;
					map[i][j] = 2;
				}
			}
		}
	}
	public static boolean check(int r, int c) {
		return r >= 0 && c >= 0 && r < H && c < W;
	}
	public static void bfs() {
		Queue<int[]> q = new LinkedList<>();
		visited = new boolean[H][W];
		visited[0][0] = true;
		map[0][0] = 2;
		q.offer(new int[] {0,0});
		while(!q.isEmpty()) {
			int[] loc = q.poll();
			for (int i = 0; i < 4; i++) {
				int nr = loc[0]+dr[i];
				int nc = loc[1]+dc[i];
				if(!check(nr,nc) || visited[nr][nc]) continue;
				if(map[nr][nc] == 1) continue;
				visited[nr][nc] = true;
				map[nr][nc] = 2;
				q.offer(new int[] {nr,nc});
			}
		}
	}
}