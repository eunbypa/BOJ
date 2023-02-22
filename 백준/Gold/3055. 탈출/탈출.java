import java.util.*;
import java.io.*;
public class Main {
	static int R, C, min = 2500;
	static int wTurn;
	static char[][] map;
	static boolean[][] visited;
	static boolean[][] visited2;
	static int[] dochi; // 고슴도치 위치
	//static int[] water; // 물  시작 위치
	static List<int[]> water;
	static List<int[]> nextWater;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0,-1, 0, 1};
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R =  Integer.parseInt(st.nextToken());
		C =  Integer.parseInt(st.nextToken());
		map = new char[R][C];
		visited = new boolean[R][C];
		visited2 = new boolean[R][C];
		dochi = new int[] {-1,-1};
		water = new ArrayList<>();
		for (int i= 0; i < R; i++) {
			map[i] = br.readLine().toCharArray();
			for (int j = 0; j < C; j++) {
				if(map[i][j] == 'S') {
					dochi[0] = i;
					dochi[1] = j;
				}
				if(map[i][j] == '*') {
					water.add(new int[] {i,j});
				}
			}
		}
		bfs();
		if(min==2500) System.out.println("KAKTUS");
		else System.out.println(min);
	}
	//물 먼저
	public static void waterFlow() {
		nextWater = new ArrayList<>();
		for (int k = water.size()-1; k >= 0; k--) {
			int[] loc = water.get(k);
			visited2[loc[0]][loc[1]] = true;
			water.remove(k);
			for (int i = 0; i < 4; i++) {
				int nr = loc[0]+dr[i];
				int nc = loc[1]+dc[i];
				if(!check(nr,nc)) continue; //범위밖
				if(map[nr][nc] == 'X' || map[nr][nc] == 'D' ) continue; // 돌이거나 비버소굴
				if(visited2[nr][nc]) continue;
				map[nr][nc] = '*';
				visited2[nr][nc] = true;
				nextWater.add(new int[] {nr,nc});
			}
		}
		water = nextWater;
	}
	public static void bfs() {
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] {dochi[0], dochi[1], 0});
		while(!q.isEmpty()) {
			dochi = q.poll();
			visited[dochi[0]][dochi[1]] = true;
			if(wTurn == dochi[2]) {
				wTurn++;
				waterFlow(); // 물이 찰 예정인 지역 파악
			}
/*			for (int i = 0; i < R; i++) {
				for (int j = 0; j < C; j++) {
					System.out.print(map[i][j] +" ");
				}
				System.out.println();
			}
			System.out.println();*/
			if(map[dochi[0]][dochi[1]] == 'D') { //비버소굴 도착
				min = Math.min(min, dochi[2]);
				continue;
			}
			for (int i = 0; i < 4; i++) {
				int nr = dochi[0]+dr[i];
				int nc = dochi[1]+dc[i];
				if(!check(nr,nc)) continue; //범위밖
				if(visited[nr][nc]) continue;
				if(map[nr][nc] == 'X' || map[nr][nc] == '*' ) continue; // 돌이거나 물
				q.offer(new int[] {nr,nc,dochi[2]+1});
				visited[nr][nc] = true;
			}
		}
	}
	public static boolean check(int r, int c) {
		return r >= 0 && c >= 0 && r < R && c < C;
	}
}