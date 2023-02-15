import java.util.*;
import java.io.*;
class Point{
	int r;
	int c;
	public Point(int r, int c) {
		this.r = r;
		this.c = c;
	}
}
public class Main {
	static int N,M,R,turns;
	static int[][] nums;
	static int[][] tmp;
	static Point[][] list;
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
		turns = (N > M) ? M/2 : N/2; 
		nums = new int[N][M];
		tmp = new int[N][M];
		list = new Point[N/2][];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				nums[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		setList(0,0,0);
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				sb.append(tmp[i][j]+" ");
			}
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}
	public static void setList(int r, int c, int turn) {
		if(r == turns) return;
		int x = r, y = c;
		int nr=r, nc=c;
		int tot = 2*((N-2*turn)+(M-2*turn))-4;
		list[turn] = new Point[tot];
		int idx = 0;
		list[turn][idx++] = new Point(nr,nc);
		for (int i = 0; i < 4; i++) {
			nr=r;
			nc=c;
			while(idx != tot && check(nr, nc, turn)) {
				//System.out.println("1무한루프");
				nr = nr+dr[i];
				nc = nc+dc[i];
				if(!check(nr, nc,turn)) break;
				list[turn][idx++] = new Point(nr,nc);
				//System.out.println(idx);
				r = nr;
				c = nc;
			}
		}
		int rotateCnt = R % tot; // 주기
		int d = 0;
		nr = x;
		nc = y;
		//System.out.println("현재 턴 : "+turn);
		for (int i = rotateCnt; i < list[turn].length; i++) {
			tmp[list[turn][i].r][list[turn][i].c] = nums[nr][nc];
			//System.out.println(nr + " " + nc +"->"+list[turn][i].r + " " + list[turn][i].c);
			if(!check(nr+dr[d],nc+dc[d],turn)) {
				d++;
				d %= 4;
			}
			nr = nr+dr[d];
			nc = nc+dc[d];
		}
		for (int i = 0; i < rotateCnt; i++) {
			tmp[list[turn][i].r][list[turn][i].c] = nums[nr][nc];
			if(!check(nr+dr[d],nc+dc[d],turn)) {
				d++;
				d %= 4;
			}
			nr = nr+dr[d];
			nc = nc+dc[d];
		}
		setList(x+1,y+1,turn+1);
	}
	public static boolean check(int r, int c, int turn) {
		return r >=turn&& c >= turn && r < N-turn && c < M-turn;
	}
}