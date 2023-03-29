import java.util.*;
import java.io.*;
public class Main {
	static int N, M, min = Integer.MAX_VALUE;
	static int[] minsik;
	static char[][] map;
	static int[][][] visited;
	static int[] dr = {-1,0,1,0}; // 02
	static int[] dc ={0,-1,0,1}; // 13
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		minsik = new int[] {-1,-1};
		visited = new int[N][M][1<<6];
		map = new char[N][M];
		for (int i = 0; i < N; i++) {
			map[i] = br.readLine().toCharArray();
			if(minsik[0] == -1) {
				for (int j = 0; j < M; j++) {
					if(map[i][j] == '0') {
						minsik[0] = i;
						minsik[1] = j;
					}
				}
			}
		}
		if(bfs()) System.out.println(min);
		else System.out.println(-1);
	}
	private static boolean bfs() {
		Queue<int[]> q = new LinkedList<>();
		// 민식이 위치, 가지고 있는 열쇠 상태, 이동 횟수
		q.offer(new int[] {minsik[0], minsik[1],0,0});
		int[] cur;
		boolean find = false;
		while(!q.isEmpty()) {
			cur = q.poll();
			//System.out.println("현재 위치 : " + cur[0]+" "+cur[1]+" 키 상태 : "+Integer.toBinaryString(cur[2]));
			if(map[cur[0]][cur[1]] == '1') { // 출구 도착
				min = Math.min(min, cur[3]);
				find = true;
				continue;
			}
			for (int i = 0; i < 4; i++) {
				int nr = cur[0]+dr[i];
				int nc = cur[1]+dc[i];
				if(!check(nr,nc)) continue; // 범위 밖
				if(map[nr][nc] == '#') continue; // 벽
				if(isDoor(nr,nc)) { // 문
					if(access(cur[2], map[nr][nc])) { // 문 딸 수 있음
						if(visited[nr][nc][cur[2]] == 0 || visited[nr][nc][cur[2]] > cur[3]+1) {
							visited[nr][nc][cur[2]] = cur[3]+1;
							q.offer(new int[] {nr,nc,cur[2],cur[3]+1});
						}
					}
					continue;
				}
				if(isKey(nr,nc)) { // 열쇠
					int idx = map[nr][nc]-'a';
					if(visited[nr][nc][cur[2]|(1<<idx)] == 0) {
						visited[nr][nc][cur[2]|(1<<idx)] = cur[3]+1;
						q.offer(new int[] {nr,nc,cur[2]|(1<<idx),cur[3]+1});
					}
					else if(visited[nr][nc][cur[2]|(1<<idx)]>cur[3]+1){
						visited[nr][nc][cur[2]|(1<<idx)] = cur[3]+1;
						q.offer(new int[] {nr,nc,cur[2]|(1<<idx),cur[3]+1});
					}
					continue;
				}
				//그냥 이동
				if(visited[nr][nc][cur[2]] == 0 || visited[nr][nc][cur[2]] > cur[3]+1) {
					visited[nr][nc][cur[2]] = cur[3]+1;
					q.offer(new int[] {nr,nc,cur[2],cur[3]+1});
				}
			}
		}
		return find;
	}
	private static boolean access(int key, char door) {
		int idx = door-'A';
		if(((1<<idx) & key) != 0) return true; // 현재 가지고 있는 키와 매칭되는 문임
		return false;
	}
	static boolean isDoor(int r, int c) {
		if(map[r][c] == 'A'|| map[r][c] == 'B'|| map[r][c] == 'C'|| map[r][c] == 'D'
				|| map[r][c] == 'E'|| map[r][c] == 'F') {
			return true;
		}
		return false;
	}
	static boolean isKey(int r, int c) {
		if(map[r][c] == 'a'|| map[r][c] == 'b'|| map[r][c] == 'c'|| map[r][c] == 'd'
				|| map[r][c] == 'e'|| map[r][c] == 'f') {
			return true;
		}
		return false;
	}
	static boolean check(int x, int y) {
		return x >= 0 && x < N &&y>= 0 && y < M;
	}
}