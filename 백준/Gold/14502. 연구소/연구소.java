import java.util.*;
import java.io.*;
public class Main {
	static int N, M, max;
	static int[][] map;
	static int[][] copy;
	static boolean[][] visited;
	static List<int[]> zeros = new ArrayList<>(); // 0인 위치 저장
	static int[] dr = {-1,0,1,0}; // 02
	static int[] dc ={0,-1,0,1}; // 13
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		copy = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 0) {
					zeros.add(new int[] {i,j});
				}
			}
		}
		combi(0,0);
		System.out.println(max);
	}
	static void combi(int start, int cnt){
		if(cnt == 3) { // 3개 위치 뽑음
			check();
			return;
		}
		for (int i = start; i < zeros.size(); i++) {
			map[zeros.get(i)[0]][zeros.get(i)[1]] = 1; // 벽 설치
			combi(i+1, cnt+1);
			map[zeros.get(i)[0]][zeros.get(i)[1]] = 0; // 벽 설치
		}
	}
	// 바이러스의 전파 영역 구하기
	static void check() {
		visited= new boolean[N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				copy[i][j] = map[i][j];
			}
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(!visited[i][j] && map[i][j] == 2) {
					visited[i][j] = true;
					bfs(i,j);
				}
			}
		}
		max = Math.max(max, findSafeArea());
	}
	// 바이러스의 전파 시작
	static void bfs(int r, int c) {
		int cnt = 0;
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] {r,c});
		int[] cur;
		while(!q.isEmpty()) {
			cur = q.poll();
			for (int i = 0; i < 4; i++) {
				int nr = cur[0]+dr[i];
				int nc = cur[1]+dc[i];
				//범위 안이고 벽이 아니고 방문하지 않은 상태일때
				if((nr >= 0 && nc >= 0 && nr < N && nc < M) && map[nr][nc] != 1 && !visited[nr][nc]) {
					visited[nr][nc] = true;
					copy[nr][nc] = 2;
					q.offer(new int[] {nr,nc});
				}
			}
		}
	}
	// 안전 영역 계산 
	static int findSafeArea() {
		int cnt = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(copy[i][j] == 0) {
					cnt++;
				}
			}
		}
		return cnt;
	}
}