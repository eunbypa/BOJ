import java.util.*;
import java.io.*;
public class Main {
	static int[][] map;
	static int[] mr = {1, 0, 1, -1};
	static int[] mc = {0, 1, 1, 1};
	static int winner, ansR, ansC;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//StringBuilder sb = new StringBuilder();
		map = new int[21][21]; // 0, 20 은 테두리를 의미, 1~19가 놓을수 있는 idx
		StringTokenizer st;
		for (int i = 1; i < 20; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j < 20; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		for (int i = 1; i < 20; i++) {
			for (int j = 1; j < 20; j++) {
				if(map[i][j] == 0) continue;
				findWinner(i, j, map[i][j]);
				if(winner != 0) {
					System.out.println(sb.toString());
					return;
				}
			}
		}
		System.out.println(winner);
	}
	public static void findWinner(int r, int c, int color) {
		for (int i = 0; i < 4; i++) {
			int nr = r;
			int nc = c;
			int tot = 1;
			while(true) {
				nr += mr[i];
				nc += mc[i];
				if(nr <= 0 || nc <= 0 || nr >= 20 || nc >= 20) break;
				if(map[nr][nc] != color) break;
				tot++;
				if(tot == 5) { // 5개가 연속으로 놓여있을 때
					if(map[nr+mr[i]][nc+mc[i]] == color) break; // 6개이상 연속인 경우
					if(map[r-mr[i]][c-mc[i]] == color) break; // 6개이상 연속인 경우
					winner = color;
					ansR = r;
					ansC = c;
					sb.append(winner +"\n");
					sb.append(ansR + " " + ansC+ "\n");
					return;
				}
				
			}
		}
	}
}