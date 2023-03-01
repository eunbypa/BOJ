import java.util.*;
import java.io.*;

public class Main {
	static int N, maxNum;
	static int[][] map;
	static int[][] copy;
	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,-1,0,1};
	public static void main(String[] args) throws IOException {
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
				maxNum = Math.max(map[i][j], maxNum);
			}
		}
		dfs(0);
		System.out.println(maxNum);
	}
	public static void dfs(int cnt) { // 부분집합 dfs
		if(cnt == 5) { // 최대 5번 이동 가능
			return;
		}
		int[][] curMap = new int[N][]; // 현재 맵 상태 저장용
		for (int i = 0; i < N; i++) {
			curMap[i] = Arrays.copyOf(copy[i], N);
		}
		//상하좌우 중 하나 선택후 이동
		for (int i = 0; i < 4; i++) {
			//System.out.println("현재 이동 방향 : " + i);
			copy = moving(i);
			//print();
			copy = merge(i);
			//print();
			dfs(cnt+1);
			for (int k = 0; k < N; k++) {
				copy[k] = Arrays.copyOf(curMap[k], N);
			}
		}
	}
	/*static void print() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print(copy[i][j]+" ");
			}
			System.out.println();
		}
		System.out.println();
	}*/
	// 해당 방향으로 블록 이동시키기
	public static int[][] moving(int d) {
		int[][] copy2 = new int[N][N];
		if(d == 0 || d == 2) { // 상 하
			for (int i = 0; i < N; i++) {
				int r = (d == 0) ? 0 : N-1;
				int nr = r;
				while(nr >= 0 && nr < N) {
					if(copy[nr][i] != 0) {
						copy2[r][i] = copy[nr][i];
						copy[nr][i] = 0;
						r -= dr[d];
					}
					nr -= dr[d]; // 예를들어 상 방향으로 이동시키면 맨 위에서부터 아래로 이동하면서 0이 아닌 블록을 찾고, 그 블록을 맨위부터 차례대로 쌓음
				}
			}
		}
		if(d == 1 || d == 3) { // 좌 우
			for (int i = 0; i < N; i++) {
				int c = (d == 1) ? 0 : N-1;
				int nc = c;
				while(nc >= 0 && nc < N) {
					if(copy[i][nc] != 0) {
						copy2[i][c] = copy[i][nc];
						copy[i][nc] = 0;
						c -= dc[d];
					}
					nc -= dc[d]; // 예를들어 상 방향으로 이동시키면 맨 위에서부터 아래로 이동하면서 0이 아닌 블록을 찾고, 그 블록을 맨위부터 차례대로 쌓음
				}
			}
		}
		return copy2;
	}
	//숫자가 같은 블록이 연속한 경우 합치기, 합쳐지는 블록이 하나도 없으면 false 반환
	public static int[][] merge(int d) {
		int[][] copy2 = new int[N][N];
		if(d == 0 || d == 2) { // 상 하
			for (int i = 0; i < N; i++) {
				int r = (d == 0) ? 0 : N-1;
				int nr = r;
				while(nr >= 0 && nr < N && copy[nr][i] != 0) {
					if(nr-dr[d] >= 0 && nr-dr[d] < N && copy[nr][i] == copy[nr-dr[d]][i]) {
						copy2[r][i] = 2*copy[nr][i];
						maxNum = Math.max(copy2[r][i], maxNum);
						r -= dr[d];
						nr -= dr[d];
					}else {
						copy2[r][i] = copy[nr][i];
						r -= dr[d];
					}
					nr -= dr[d];
				}
			}
		}
		if(d == 1 || d == 3) { // 좌 우
			for (int i = 0; i < N; i++) {
				int c = (d == 1) ? 0 : N-1;
				int nc = c;
				while(nc >= 0 && nc < N && copy[i][nc] != 0) {
					if(nc-dc[d] >= 0 && nc-dc[d] < N && copy[i][nc] == copy[i][nc-dc[d]]) {
						copy2[i][c] = 2*copy[i][nc];
						maxNum = Math.max(copy2[i][c], maxNum);
						c -= dc[d];
						nc -= dc[d];
					}else {
						copy2[i][c] = copy[i][nc];
						c -= dc[d];
					}
					nc -= dc[d];
				}
			}
		}
		return copy2;
	}
}
