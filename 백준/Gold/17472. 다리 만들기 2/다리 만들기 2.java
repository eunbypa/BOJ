import java.util.*;
import java.io.*;


public class Main{
	static class Bridge implements Comparable<Bridge>{
		int e;
		int length;
		public Bridge(int e, int length) {
			super();
			this.e = e;
			this.length = length;
		}
		@Override
		public int compareTo(Bridge o) {
			// TODO Auto-generated method stub
			return this.length-o.length;
		}
	}
	static int N, M,num, min;
	static int[][] map;
	static int[][] bridges; // 다리 길이 저장
	static boolean[][] visited;
	static boolean[] visited2;
	static int[] dr = {1,0,-1,0};
	static int[] dc = {0,1,0,-1};
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());;
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		visited = new boolean[N][M];
		num = 1;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				// 섬이 아니거나, 이미 번호 배정이 끝난 섬이면 스킵
				if(map[i][j] == 0 || visited[i][j]) continue;
				bfs(i,j, num);
				num++;
			}
		}
		bridges = new int[num][num];
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				// 섬이 아니거나, 이미 번호 배정이 끝난 섬이면 스킵
				if(map[i][j] == 0) continue;
				setBridge(i, j, map[i][j]);
			}
		}
		visited2 = new boolean[num];
		if(prim(1)) System.out.println(min);
		else System.out.println(-1);
	}
	// 섬의 구역에 따라 번호를 정하기 위한 bfs
	public static void bfs(int r, int c, int n) {
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] {r,c});
		visited[r][c] = true;
		map[r][c] = n;
		int nr, nc;
		int[] loc;
		while(!q.isEmpty()) {
			loc = q.poll();
			for (int i = 0; i < 4; i++) {
				nr = loc[0]+dr[i];
				nc = loc[1]+dc[i];
				if(!check(nr,nc)) continue;
				if(map[nr][nc] == 0 || visited[nr][nc]) continue;
				visited[nr][nc] = true;
				map[nr][nc] = n;
				q.offer(new int[] {nr,nc});
			}
		}
	}
	//현재 지점에서 상하좌우 이동하며 연결된 다리를 찾는다.
	public static void setBridge(int r, int c, int start) {
		boolean findIsland;
		int length; 
		int end = 0; // 이어진 섬
		int nr, nc;
		for (int i = 0; i < 4; i++) {
			findIsland = false;
			length = 0;
			nr = r;
			nc = c;
			while(!findIsland && check(nr+dr[i],nc+dc[i])) {
				// 다리를 건설하기 위해 섬의 가장 끝부분에서 시작해야 한다.
				if(map[nr+dr[i]][nc+dc[i]] == map[r][c]) {
					if(length == 0) {
						nr += dr[i];
						nc += dc[i];
					}else break; // 자기자신과 연결되는 것을 의미함
					
				}else if(map[nr+dr[i]][nc+dc[i]] == 0) {
					length++;
					nr += dr[i];
					nc += dc[i];
				}else {
					end = map[nr+dr[i]][nc+dc[i]];
					findIsland = true;
				}
			}
			//연결된 섬을 찾았고, 다리 길이가 2이상인 경우
			if(findIsland && length >= 2) {
				if(bridges[start][end] == 0) {
					bridges[start][end] = length;
					bridges[end][start] = length;
				}else { // 이미 다리가 연결된 경우 더 짧은 길이로 갱신
					bridges[start][end] = Math.min(bridges[start][end], length);
					bridges[end][start] = Math.min(bridges[end][start], length);
				}
            }
		}
	}
	// start부터 시작해서 전체 섬을 연결하는 최소신장트리를 프림 알고리즘으로 구하기
	public static boolean prim(int start) {
		PriorityQueue<Bridge> q = new PriorityQueue<>();
		q.offer(new Bridge(start, 0));
		int cnt = 0;
		Bridge b;
		while(!q.isEmpty()) {
			b = q.poll();
			if(visited2[b.e]) continue;
			visited2[b.e] = true;
			cnt++;
			min += b.length;
			if(cnt == num-1) {
				return true; // 모든 섬 다 방문한 상태
			}
			for (int i = 0; i < bridges[b.e].length; i++) {
				if(bridges[b.e][i] == 0) continue;
				if(visited2[i]) continue;
				q.offer(new Bridge(i,bridges[b.e][i]));
			}
		}
		return false;
	}
	//범위를 벗어나는지 체크
	public static boolean check(int r, int c) {
		return r >= 0 && c >= 0 && r < N && c < M;
	}
}
