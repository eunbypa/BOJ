import java.util.*;
import java.io.*;

public class Main {
	static int N,M,K,turns,min = Integer.MAX_VALUE;
	static int sr, sc, er, ec;
	static int[][] origin;
	static int[][] map;
	static int[][] tmp;
	static int[][] rotations;
	static int[] p;
	static int[] dr = {0,1,0,-1};
	static int[] dc = {1,0,-1,0};
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		origin = new int[N][M];
		map = new int[N][M];
		tmp = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				origin[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		p = new int[K];
		rotations = new int[K][3];
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 3; j++) {
				rotations[i][j] = Integer.parseInt(st.nextToken());
			}
			p[i] = i;
		}
		do {
			for (int k = 0; k < N; k++) { // 복사
				for (int j = 0; j < M; j++) {
					map[k][j] = origin[k][j];
					tmp[k][j] = origin[k][j];
				}
			}
			for (int i = 0; i < K; i++) {
				sr =rotations[p[i]][0]-rotations[p[i]][2]-1;
				sc = rotations[p[i]][1]-rotations[p[i]][2]-1;
				er = rotations[p[i]][0]+rotations[p[i]][2]-1;
				ec = rotations[p[i]][1]+rotations[p[i]][2]-1;
				turns = (er-sr > ec-sc) ? (ec-sc+1)/2 : (er-sr+1)/2;
				rotate(sr,sc,0);
				for (int k = sr; k < er+1; k++) { // 복사
					for (int j = sc; j < ec+1; j++) {
						map[k][j] = tmp[k][j];
					}
				}
			}
			min = Math.min(min, getMinSum());
		}while(np());
		System.out.println(min);
	}
	public static void rotate(int r, int c, int turn) {
		if(turn == turns) return;
		int nr=r, nc=c;
		for (int i = 0; i < 4; i++) {
			nr=r;
			nc=c;
			while(check(nr, nc, turn)) {
				nr = nr+dr[i];
				nc = nc+dc[i];
				if(!check(nr, nc,turn)) break;
				tmp[nr][nc] = map[r][c];
				r = nr;
				c = nc;
			}
		}
		rotate(r+1,c+1,turn+1);
	}
	public static boolean check(int r, int c, int turn) {
		return r >=sr+turn&& c >= sc+turn && r < er+1-turn && c < ec+1-turn;
	}
	public static int getMinSum() {
		int minSum = Integer.MAX_VALUE;
		for (int i = 0; i < N; i++) {
			int sum = 0;
			for (int j = 0; j < M; j++) {
				sum += map[i][j];
			}
			minSum = Math.min(minSum, sum);
		}
		return minSum;
	}
	public static boolean np() {
		int i = K-1;
		int j = K-1;
		while(i > 0 && p[i-1]>p[i])i--;
		if(i == 0) return false;
		while(p[i-1]>p[j])j--;
		int tmp = p[i-1];
		p[i-1] = p[j];
		p[j] = tmp;
		j = K-1;
		while(i<j) {
			tmp = p[i];
			p[i] = p[j];
			p[j] = tmp;
			i++;
			j--;
		}
		return true;
	}
}
