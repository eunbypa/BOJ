import java.util.*;
import java.io.*;


public class Main{
	static int N,Q, length;
	static int[][] ice;
	static int[][] copy;
	static boolean[][] visited;
	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,-1,0,1};
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());
		length = 1 << N;
		ice = new int[length][length];
		copy = new int[length][length];
		visited = new boolean[length][length];
		for (int i = 0; i < length; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < length; j++) {
				ice[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		st = new StringTokenizer(br.readLine());
		int stage; // 마법 단계
		for (int i = 0; i < Q; i++) {
			stage = Integer.parseInt(st.nextToken());
			divide(0,0,length,stage);
			/*System.out.println("회전 완료");
			print();*/
			fireStorm();
			/*System.out.println("파이어스톰 완료");
			print();*/
		}
		int max = 0;
		for (int i = 0; i < length; i++) {
			for (int j = 0; j < length; j++) {
				if(ice[i][j] == 0 || visited[i][j]) continue;
				max = Math.max(max, bfs(i,j));
			}
		}
		StringBuilder sb = new StringBuilder();
		sb.append(getIceSum()+"\n");
		sb.append(max+"\n");
		System.out.println(sb.toString());
	}
/*	public static void print() {
		for (int i = 0; i < length; i++) {
			for (int j = 0; j < length; j++) {
				System.out.print(ice[i][j]+" ");
			}
			System.out.println();
		}
		System.out.println();
	}
	public static void print2() {
		for (int i = 0; i < length; i++) {
			for (int j = 0; j < length; j++) {
				System.out.print(copy[i][j]+" ");
			}
			System.out.println();
		}
		System.out.println();
	}*/
	public static void divide(int r, int c, int n, int stage) {
		if(n == (1<<stage)) {
			rotate(r,c,n);
			//System.out.println("회전");
			//print();
			return;
		}
		divide(r,c,n/2,stage);
		divide(r,c+n/2,n/2,stage);
		divide(r+n/2,c,n/2,stage);
		divide(r+n/2,c+n/2,n/2,stage);
	}
	public static void rotate(int r, int c, int n) {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				copy[r+i][c+j] = ice[n-1+r-j][c+i];
				//System.out.println((r+i)+" "+(c+j)+"->"+(n-1+c-j)+" "+(r+i));
			}
		}
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				ice[r+i][c+j] = copy[r+i][c+j];
			}
		}
	}
	public static void fireStorm() {
		for (int i = 0; i < length; i++) {
			for (int j = 0; j < length; j++) {
				if(!isMelted(i,j)) continue; // 파이어스톰 영향을 받지 않음
				if(ice[i][j] != 0) copy[i][j]--;
			}
		}
		for (int i = 0; i < length; i++) {
			for (int j = 0; j < length; j++) {
				ice[i][j] = copy[i][j];
			}
		}
	}
	public static boolean isMelted(int r, int c) {
		int nr, nc;
		int cnt = 0;
		for (int i = 0; i < 4; i++) {
			nr = r+dr[i];
			nc = c+dc[i];
			if(!check(nr,nc)) continue;
			if(ice[nr][nc] != 0) cnt++; // 얼음이 있는 칸과 인접함
		}
		if(cnt >= 3) return false;
		return true;
	}
	public static int getIceSum() {
		int sum = 0;
		for (int i = 0; i < length; i++) {
			for (int j = 0; j < length; j++) {
				sum+=ice[i][j];
			}
		}
		return sum;
	}
	//얼음 덩어리가 차지하는 칸의 수 반환
	public static int bfs(int r, int c) {
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] {r,c});
		visited[r][c] = true;
		int cnt = 1;
		while(!q.isEmpty()) {
			int[] loc = q.poll();
			for (int i = 0; i < 4; i++) {
				int nr = loc[0]+dr[i];
				int nc = loc[1]+dc[i];
				if(!check(nr,nc)) continue;
				if(ice[nr][nc] == 0 || visited[nr][nc]) continue;
				visited[nr][nc] = true;
				cnt++;
				q.offer(new int[] {nr,nc});
			}
		}
		return cnt;
	}
	public static boolean check(int r, int c) {
		return r >= 0 && c >= 0 && r < length && c < length;
	}
}
