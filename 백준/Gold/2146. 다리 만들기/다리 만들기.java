import java.util.*;
import java.io.*;
public class Main {
	static int N, min = 10001;
	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,-1,0,1};
	static int[][] map;
	static int[][] copy;
	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		copy = new int[N][N];
		StringTokenizer st;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				copy[i][j] = map[i][j];
			}
		}
		int num = 1; // 섬 번호
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(map[i][j] == 1) {
					num++;
					bfs(i,j,num);
				}
			}
		}
		//print(map);
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(copy[i][j] == 2) {
					bfs2(i,j);
				}
			}
		}
		System.out.println(min);
	}
	/*static void print(int[][] arr) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print(arr[i][j]+" ");
			}
			System.out.println();
		}
	}*/
	static void bfs(int r, int c, int num) {
		Queue<int[]> q = new LinkedList<>();
		boolean[][] visited = new boolean[N][N];
		q.offer(new int[] {r,c});
		visited[r][c]= true;
		map[r][c] = num;
		int[] cur;
		int cnt;
		while(!q.isEmpty()) {
			cur = q.poll();
			cnt = 0;
			for (int i = 0; i < 4; i++) {
				int nr = cur[0]+dr[i];
				int nc = cur[1]+dc[i];
				if(!check(nr,nc) || visited[nr][nc]) continue;
				if(map[nr][nc] == 0) { // 제일 바깥 영역 테두리를 구하기 위해 4방향에서 인접한 바다 칸 개수를 구함
					cnt++;
					continue;
				}
				map[nr][nc] = num; // 해당 섬 영역을 섬 번호로 바꿈
				visited[nr][nc] = true;
				q.offer(new int[] {nr,nc});
			}
			if(cnt == 0) continue;
			// 바깥 부분
			copy[cur[0]][cur[1]] = 2;
		}
	}
	static void bfs2(int r, int c) {
		Queue<int[]> q = new LinkedList<>();
		boolean[][] visited = new boolean[N][N];
		q.offer(new int[] {r,c, 0});
		visited[r][c]= true;
		int[] cur;
		while(!q.isEmpty()) {
			cur = q.poll();
			if(min == cur[2]) continue;
			for (int i = 0; i < 4; i++) {
				int nr = cur[0]+dr[i];
				int nc = cur[1]+dc[i];
				if(!check(nr,nc) || visited[nr][nc]) continue;
				if(min <= cur[2]) continue;
				if(map[nr][nc] == 0) {
					visited[nr][nc] = true;
					q.offer(new int[] {nr,nc,cur[2]+1});
				}else if(map[nr][nc] == map[r][c]) continue;
				else {
					min = Math.min(min, cur[2]);
				}
			}
		}
	}
	static boolean check(int r, int c) {
		return r >= 0 && c >= 0 && r < N && c < N;
	}
}