import java.util.*;
import java.io.*;
public class Main {
	static int R,C, max;
	static char[][] map;
	static int[] apb;
	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,-1,0,1,};
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R =  Integer.parseInt(st.nextToken());
		C =  Integer.parseInt(st.nextToken());
		map = new char[R][C];
		apb = new int[26];
		for (int i = 0; i < R; i++) {
			map[i] = br.readLine().toCharArray();
		}
		apb[map[0][0]-'A'] = 1;
		dfs(0,0,1);
		
		System.out.println(max);
	}
	public static void dfs(int r, int c, int cnt) {
		for (int i = 0; i < 4; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];
			if(!check(nr,nc)) continue;
			if(apb[map[nr][nc]-'A'] == 0) { // 아직 방문하지 않은 알파벳인 경우
				apb[map[nr][nc]-'A'] = 1;
				dfs(nr,nc,cnt+1);
				apb[map[nr][nc]-'A'] = 0;
			}
		}
		max = Math.max(max, cnt);
	}
	public static boolean check(int r, int c) {
		return r >= 0 && c >= 0 && r < R && c < C;
	}
}