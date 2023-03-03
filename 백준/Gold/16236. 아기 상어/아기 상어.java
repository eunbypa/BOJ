import java.util.*;
import java.io.*;

public class Main {
	static int N, ate, time;
	static int[][] map;
	//static List<int[]>[] fishes = new ArrayList[7]; // 물고기 각 크기당 위치들
	static int[] babyShark = {-1,-1}; // 아기 상어 위치
	static int[] nextFish;
	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,-1,0,1};
	static int size = 2; // 아기 상어 크기
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringTokenizer st;
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 9) {
					babyShark[0] = i;
					babyShark[1] = j;
				}
			}
		}
		ate = 0; // 먹은 물고기 수
		while(bfs()) {
			map[babyShark[0]][babyShark[1]] = 0;
			map[nextFish[0]][nextFish[1]] = 9;
			for (int i = 0; i < 2; i++) { //상어의 현재 위치 갱신
				babyShark[i] = nextFish[i];
			}
			ate++;
			if(ate == size) {
				ate = 0;
				size++;
			}
			time += nextFish[2]; // 걸린 시간 갱신
		}
		System.out.println(time);
	}
	// 다음에 먹을 물고기를 찾지 못하면 false반환
	public static boolean bfs() {
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] {babyShark[0], babyShark[1], 0});
		boolean[][] visited = new boolean[N][N];
		visited[babyShark[0]][babyShark[1]] = true;
		nextFish = new int[] {-1,-1,-1};
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			for (int i = 0; i < 4; i++) {
				int nr = cur[0]+dr[i];
				int nc = cur[1]+dc[i];
				if(!check(nr,nc)) continue;
				if(visited[nr][nc]) continue;
				if(size < map[nr][nc]) continue; // 크기가 큰 물고기는 못지나감
				if(map[nr][nc] != 0 && size > map[nr][nc]) { // 사이즈보다 크기가 작으므로 먹을 수 있는 물고기다.
					if(nextFish[0] == -1) { // 아직 찾은 물고기가 없음
						nextFish[0] = nr;
						nextFish[1] = nc;
						nextFish[2] = cur[2]+1;
					}else {
						if(nextFish[2] > cur[2]+1) {
							nextFish[0] = nr;
							nextFish[1] = nc;
							nextFish[2] = cur[2]+1;
						}else if(nextFish[2] == cur[2]+1) {
							if(nextFish[0] > nr) { // 더 위쪽에 있는거 우선
								nextFish[0] = nr;
								nextFish[1] = nc;
							}else if(nextFish[0] == nr) { // 둘다 같은 위쪽이면
								if(nextFish[1] > nc) { // 더 왼쪽에 있는거 우선 
									nextFish[1] = nc;
								}
							}
						}
					}
				}
				visited[nr][nc] = true;
				q.offer(new int[] {nr,nc,cur[2]+1});
			}
		}
		if(nextFish[0] == -1) return false;
		return true;
	}
	public static boolean check(int r, int c) {
		return r >= 0 && c >= 0 && r < N && c < N;
	}
}