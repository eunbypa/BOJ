import java.util.*;
import java.io.*;
public class Main {
	static int N,M,R;
	static int[][] nums;
	static int[][] tmp;
	static int[] dr = {1,0,-1,0};
	static int[] dc = {0,1,0,-1};
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb= new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		R=Integer.parseInt(st.nextToken());
		nums = new int[N][M];
		tmp = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				nums[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		for (int k = 0; k < R; k++) {
			rotate(0,0,0);
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					nums[i][j] = tmp[i][j];
				}
			}
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				sb.append(nums[i][j]+" ");
			}
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}
	public static void rotate(int r, int c, int turn) {
		if(r == N/2) return;
		int nr=r, nc=c;
		for (int i = 0; i < 4; i++) {
			nr=r;
			nc=c;
			while(check(nr, nc, turn)) {
				nr = nr+dr[i];
				nc = nc+dc[i];
				if(!check(nr, nc,turn)) break;
				tmp[nr][nc] = nums[r][c];
				r = nr;
				c = nc;
			}
		}
		rotate(r+1,c+1,turn+1);
	}
	public static boolean check(int r, int c, int turn) {
		return r >=turn&& c >= turn && r < N-turn && c < M-turn;
	}
}