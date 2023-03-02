import java.util.*;
import java.io.*;
public class Main {
	static int N, M, min = 11;
	static char[][] map;
	static int[] red = new int[2]; // 빨간 구슬 위치
	static int[] blue = new int[2]; // 파란 구슬 위치
	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,-1,0,1};
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new char[N][];
		
		for (int i = 0; i < N; i++) {
			map[i] = br.readLine().toCharArray();
			for (int j = 0; j < M; j++) {
				if(map[i][j] == 'R') {
					red[0] = i;
					red[1] = j;
				}
				if(map[i][j] == 'B') {
					blue[0] = i;
					blue[1] = j;
				}
			}
		}
		bfs();
		if(min == 11) System.out.println(-1); // 10번 이하로 성공이 불가능하므로 -1 출력
		else System.out.println(min);
	}
	
	public static void bfs() {
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] {red[0], red[1], blue[0], blue[1], 0});
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			if(cur[4] >= min) continue;
			for (int i = 0; i < 4; i++) {
				for (int j = 0; j < 2; j++) {
					red[j] = cur[j];
					blue[j] = cur[j+2];
				}
				//위로 기울일떄, 같은 열에 위치한 경우
				if(i == 0 && red[1] == blue[1]) {
					if(red[0] < blue[0]) {
						red = moving(cur[0],cur[1],i,0); // 빨간 구슬 움직이기
						blue = moving(cur[2],cur[3],i,1); // 파란 구슬 움직이기
					}else {
						blue = moving(cur[2],cur[3],i,1); // 파란 구슬 움직이기
						red = moving(cur[0],cur[1],i,0); // 빨간 구슬 움직이기
					}
				}
				//아래로 기울일떄, 같은 열에 위치한 경우
				else if(i == 2 && red[1] == blue[1]) {
					if(red[0] > blue[0]) {
						red = moving(cur[0],cur[1],i,0); // 빨간 구슬 움직이기
						blue = moving(cur[2],cur[3],i,1); // 파란 구슬 움직이기
					}else {
						blue = moving(cur[2],cur[3],i,1); // 파란 구슬 움직이기
						red = moving(cur[0],cur[1],i,0); // 빨간 구슬 움직이기
					}
				}
				//왼쪽으로 기울일떄, 같은 행에 위치한 경우
				else if(i == 1 && red[0] == blue[0]) {
					if(red[1] < blue[1]) {
						red = moving(cur[0],cur[1],i,0); // 빨간 구슬 움직이기
						blue = moving(cur[2],cur[3],i,1); // 파란 구슬 움직이기
					}else {
						blue = moving(cur[2],cur[3],i,1); // 파란 구슬 움직이기
						red = moving(cur[0],cur[1],i,0); // 빨간 구슬 움직이기
					}
				}
				//오른쪽으로 기울일떄, 같은 행에 위치한 경우
				else if(i == 3 && red[0] == blue[0]) {
					if(red[1] > blue[1]) {
						red = moving(cur[0],cur[1],i,0); // 빨간 구슬 움직이기
						blue = moving(cur[2],cur[3],i,1); // 파란 구슬 움직이기
					}else {
						blue = moving(cur[2],cur[3],i,1); // 파란 구슬 움직이기
						red = moving(cur[0],cur[1],i,0); // 빨간 구슬 움직이기
					}
				}
				else {
					red = moving(cur[0],cur[1],i,0); // 빨간 구슬 움직이기
					blue = moving(cur[2],cur[3],i,1); // 파란 구슬 움직이기
				}
				if(blue[0] == -1) continue; // 파란 구슬이 빠졌으므로 실패
				if(red[0] == -1) {
					min = Math.min(min, cur[4]+1);
					continue; // 빨간 구슬이 빠졌으므로 끝
				}
				//모든 구슬 위치가 이동후에도 변함이 없는 경우 스킵
				if(red[0] == cur[0] && red[1] == cur[1]
						&& blue[0] == cur[2] && blue[1] == cur[3]) continue;
				q.offer(new int[] {red[0], red[1], blue[0], blue[1], cur[4]+1});
			}
		}
	}
	//구슬 움직이기, 구멍에 빠지면 {-1,-1} 반환
	public static int[] moving(int r, int c, int d, int color) {
		int[] other = (color == 0) ? blue : red;
		int nr = r, nc = c;
		while(map[nr][nc] != '#' && !(other[0] == nr && other[1] == nc)) {
			r = nr;
			c = nc;
			if(map[nr][nc] == 'O') return new int[] {-1,-1};
			nr += dr[d];
			nc += dc[d];
		}
		return new int[] {r,c};
	}
}