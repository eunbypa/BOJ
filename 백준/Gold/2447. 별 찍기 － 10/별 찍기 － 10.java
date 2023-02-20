import java.util.*;
import java.io.*;
public class Main {
	static int N;
	static char[][] map;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new char[N][N];
		divide(0,0,N);
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(map[i][j] == 0) sb.append(' ');
				else sb.append('*');
			}
			sb.append('\n');
		}
		System.out.println(sb.toString());
	}
	public static void divide(int r, int c, int n) {
		if(n == 3) {
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					if(i == 1 && j == 1) continue;
					map[r+i][c+j] = 1;
				}
			}
			return;
		}
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if(i == 1 && j == 1) continue;
				divide(r+i*n/3,c+j*n/3,n/3);
			}
		}
	}
}