import java.util.*;
import java.io.*;

public class Main {
	static char[][] map; // 맵 정보 저장하는 2차원 배열
	static int[][][] dp; // 해당 위치에, 가로 또는 세로 모양으로 진입한 경우 소요한 동작 횟수를 저장하는 배열(메모이제이션)
	static int[] dr = {-1,0,1,0}; // 행 상하좌우 이동
	static int[] dc = {0,-1,0,1}; // 열 상하좌우 이동
	static int[][] shapes = {{0,1},{1,0}}; // 가로 모양, 세로 모양 각 경우 중심점을 기준으로 양 옆칸을 파악하기 위한 배열
	static int N,min; // 맵 한변의 길이, 최소 동작 횟수
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		min = Integer.MAX_VALUE; // 최댓값으로 초기화
		N = Integer.parseInt(br.readLine());
		map = new char[N][N];
		dp = new int[N][N][2]; // 세번째 index에서 0 은 가로 모양, 1 은 세로 모양
		int idx =0, sr, sc,shape = -1; // 기둥 몇번째 지점 탐색중인지 파악하기 위한 index, 중심점 행, 중심점 열, 모양
		int[][] line = new int[3][2]; // 기둥 세지점 나열
		for (int i = 0; i < N; i++) {
			map[i] = (br.readLine()).toCharArray();
			for (int j = 0; j < N; j++) {
				if(map[i][j] == 'B') { // 기둥이 있는 곳을 의미
					line[idx][0] = i;
					line[idx][1] = j;
					idx++;
				}
			}
		}
		sr = line[1][0]; // 중심점 행
		sc = line[1][1]; // 중심점 열
		if(line[0][0] == line[1][0]) shape = 0; // 행이 같으므로 가로 모양
		if(line[0][1] == line[1][1]) shape = 1; // 열이 같으므로 세로 모양
		bfs(sr,sc,shape); // 탐색 시작
		if(min == Integer.MAX_VALUE) min = 0; // 이동이 불가능함을 의미
		System.out.println(min);
	}
	// 중심점 현재 위치, 현재 모양, 움직인 횟수
	static void bfs(int r, int c, int shape) {
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] {r,c,shape,0});
		int[] cur;
		int cnt;
		while(!q.isEmpty()) {
			cur = q.poll();
			r = cur[0];
			c = cur[1];
			shape= cur[2];
			cnt = cur[3];
			if(isGoal(r,c,shape)) { //  도착지점에 잘 도착한 경우
				min = Math.min(min, cnt); // 최소 동작 횟수 갱신
				return;
			}
			// 상하좌우 이동
			for (int i = 0; i < 4; i++) {
				int nr = r + dr[i];
				int nc = c + dc[i];
				if(!check(nr,nc,shape)) continue; // 이동할 수 없으므로 건너뛰기
				// dp에 저장된 값을 가져와서 
				// 아예 방문한 적이 없거나 
				// 현재 방문할 때 소요한 동작 횟수가 기존에 저장된 값보다 작은 경우만 마저 탐색하도록 함
				if(dp[nr][nc][shape] == 0 || dp[nr][nc][shape] > cnt+1) { 
					dp[nr][nc][shape] = cnt+1; // 최솟값 갱신
					q.offer(new int[] {nr,nc,shape,cnt+1});
				}
			}
			// 회전
			if(checkRotate(r,c)) { // 회전할 수 있는 경우
				int nextShape = (shape == 0) ? 1 : 0; // 다음에 취할 모양
				// dp에 저장된 값을 가져와서 
				// 아예 방문한 적이 없거나 
				// 현재 방문할 때 소요한 동작 횟수가 기존에 저장된 값보다 작은 경우만 마저 탐색하도록 함
				if(dp[r][c][nextShape] == 0 || dp[r][c][nextShape] > cnt+1) {
					dp[r][c][nextShape] = cnt+1; // 최솟값 갱신
					q.offer(new int[] {r,c,nextShape,cnt+1});
				}
			}
		}
	}
	// 회전이 가능한지 확인하는 함수
	private static boolean checkRotate(int r, int c) {
		int sr = r-1, sc = c-1; // 3*3 정사각형의 시작점
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				// 범위 밖
				if((sr+i) < 0 || (sr+i) >= N || (sc+j) < 0 || (sc+j)  >= N) return false;
				// 1인 기둥이 존재
				if(map[sr+i][sc+j] == '1') return false;
			}
		}
		return true;
	}
	// 기둥을 범위 안에서 성공적으로 옮길 수 있는지 확인하는 함수
	private static boolean check(int nr, int nc, int shape) {
		// 기둥 길이 3이므로 세 지점 좌표 저장하는 배열
		int[][] points = {{nr-shapes[shape][0],nc-shapes[shape][1]},{nr,nc},{nr+shapes[shape][0],nc+shapes[shape][1]}}; 
		for (int i = 0; i < 3; i++) {
			// 범위를 벗어나는 경우
			if(points[i][0] < 0 || points[i][0] >= N || points[i][1] < 0 || points[i][1] >= N) return false;
			// 이동할 위치에 다른 기둥이 있는 경우 
			if(map[points[i][0]][points[i][1]] == '1') return false;
		}
		return true;
	}
	// 도착지점에 잘 도착했는지 판별하는 함수
	private static boolean isGoal(int r, int c, int shape) {
		if(map[r][c] == 'E' && map[r-shapes[shape][0]][c-shapes[shape][1]] == 'E' && map[r+shapes[shape][0]][c+shapes[shape][1]] == 'E')
			return true;
		return false;
	}
}