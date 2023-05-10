import java.util.*;
import java.io.*;

public class Main {
	static int N;
	static boolean[][] visited; 
	static char[][] map;
	static int[] dr = {-1,0,1,0}; // 행 상하좌우 이동
	static int[] dc = {0,-1,0,1}; // 열 상하좌우 이동
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new char[N][N];
		for (int i = 0; i < N; i++) {
			map[i] = (br.readLine()).toCharArray();
		}
		StringBuilder sb = new StringBuilder();
		//적록색약 아닌 사람 먼저 시작
		for (int i = 0; i < 2; i++) {
			visited = new boolean[N][N];
			sb.append(countArea(i));
			if(i==0) sb.append(' ');
		}
		System.out.println(sb.toString());
	}
	static int countArea(int type) {
		int cnt=0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(visited[i][j]) continue;
				visited[i][j] = true;
				bfs(i,j,type);
				cnt++;
			}
		}
		return cnt;
	}
	// 중심점 현재 위치, 현재 모양, 움직인 횟수
	static void bfs(int r, int c, int type) { // type 0 적록색약 아님, type 1 적록색약 맞음
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] {r,c});
		int[] cur;
		while(!q.isEmpty()) {
			cur = q.poll();
			r = cur[0];
			c = cur[1];
			// 상하좌우 이동
			for (int i = 0; i < 4; i++) {
				int nr = r + dr[i];
				int nc = c + dc[i];
				if(!check(nr,nc) || !canGo(nr,nc,type,map[r][c])) continue; // 이동할 수 없으므로 건너뛰기
				if(visited[nr][nc]) continue;
				visited[nr][nc] = true;
				q.offer(new int[] {nr,nc});
			}
		}
	}
	private static boolean canGo(int nr, int nc, int type, char color) {
		// TODO Auto-generated method stub
		if(type == 0) { // 적록색약 아님
			if(map[nr][nc] == color) return true;
		}
		if(type == 1) { // 적록색약 맞음
			if(color == 'R' || color == 'G') {
				if(map[nr][nc] == 'R' || map[nr][nc] == 'G') return true;
			}else if(map[nr][nc] == color) return true;
		}
		return false;
	}
	static boolean check(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < N;
	}
}