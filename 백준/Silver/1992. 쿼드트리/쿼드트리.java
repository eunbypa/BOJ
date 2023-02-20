import java.util.*;
import java.io.*;
public class Main {
	static int N;
	static char[][] map;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		map = new char[N][N];
		for (int i = 0; i < N; i++) {
			map[i] = br.readLine().toCharArray();
		}
		divide(0,0,N);
		System.out.println(sb.toString());
	}
	public static void divide(int r, int c, int n) {
		if(n == 1 || isSame(r,c,n)) {
			sb.append(map[r][c]);
			return;
		}
		sb.append('(');
		divide(r,c,n/2);
		divide(r,c+n/2,n/2);
		divide(r+n/2,c,n/2);
		divide(r+n/2,c+n/2,n/2);
		sb.append(')');
	}
	public static boolean isSame(int r, int c, int n) {
		char cc = map[r][c];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if(map[r+i][c+j] != cc) return false;
			}
		}
		return true;
	}
}