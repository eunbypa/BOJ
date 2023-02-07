
import java.util.*;
import java.io.*;
public class Main {
	static int white, blue;
	static int[][] board;
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N;
		N = Integer.parseInt(br.readLine());
		board = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		color(0, 0, N);
		System.out.println(white);
		System.out.println(blue);
	}
	//Divide and Conquer
	public static void color(int r, int c, int n) {
		if(n == 1 || isSameColor(r, c, n)) { // 한변이 1이거나 전체 구역이 같은 색이면
			if(board[r][c] == 0) white++;
			else blue++;
			return;
		}
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 2; j++) {
				color(r+n/2*i, c+n/2*j, n/2);
			}
		}
	}
	public static boolean isSameColor(int r, int c, int n) {
		int color = -1;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if(color == -1) color = board[r+i][c+j];
				else {
					if(color != board[r+i][c+j]) return false;
				}
			}
		}
		return true;
	}
}
