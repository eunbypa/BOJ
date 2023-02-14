import java.util.*;
import java.io.*;

class Solution
{
	static int H,W,N;
	static char[][] map; // 맵
	static char[] orders; // 명령
	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,-1,0,1,};
	public static void main(String args[]) throws Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T;
		T=Integer.parseInt(br.readLine());
		StringTokenizer st;
		StringBuilder sb= new StringBuilder();
		/*
		   여러 개의 테스트 케이스가 주어지므로, 각각을 처리합니다.
		*/

		for(int test_case = 1; test_case <= T; test_case++)
		{
			st = new StringTokenizer(br.readLine());
			H =  Integer.parseInt(st.nextToken());
			W =  Integer.parseInt(st.nextToken());
			map = new char[H][W];
			int r=-1, c=-1, d = -1;
			for (int i = 0; i < H; i++) {
				map[i] = br.readLine().toCharArray();
				if(r==-1 && c==-1) { // 전차 위치를 아직 못찾은 경우
					for (int j = 0; j < W; j++) {
						if(map[i][j] == '<') {
							r = i;
							c = j;
							d = 1;
						}else if(map[i][j] == '>') {
							r = i;
							c = j;
							d = 3;
						}else if(map[i][j] == 'v') {
							r = i;
							c = j;
							d = 2;
						}else if(map[i][j] == '^') {
							r = i;
							c = j;
							d = 0;
						}
					}
				}
			}
			N =  Integer.parseInt(br.readLine());
			orders = br.readLine().toCharArray();
			
			doAction(r,c,d);
			sb.append("#"+test_case+" ");
			for (int i = 0; i < H; i++) {
				for (int j = 0; j < W; j++) {
					sb.append(map[i][j]);
				}
				sb.append("\n");
			}
		}
		System.out.println(sb.toString());
	}
	public static void doAction(int r, int c, int d) { // 전차 위치, 바라보는 방향
		int cur = 0; //현재 수행하려는 명령어 위치
		int nr, nc;
		while(cur != N) {
			switch(orders[cur]) {
				case 'U':
				case 'D':
				case 'L':
				case 'R':
					d = getIdx(orders[cur]);
					nr = r + dr[d];
					nc = c + dc[d];
					if(check(nr, nc) && canMove(map[nr][nc])) { //이동 및 방향 바꾸기
						map[nr][nc] = getChar(d);
						map[r][c] = '.';
						r = nr;
						c = nc;
					}else { // 방향만 바꾸기
						map[r][c] = getChar(d);
					}
					break;
				case 'S':
					nr = r + dr[d];
					nc = c + dc[d];
					while(check(nr, nc)) {
						if(map[nr][nc] == '#') break; // 벽이 강철이라 못부숨
						else if(map[nr][nc] == '*') { // 부수기 가능
							map[nr][nc] = '.';
							break;
						}
						nr = nr + dr[d];
						nc = nc + dc[d];
					}
					break;
			}
			cur++;
		}
	}
	public static int getIdx(char d) {
		switch(d) {
			case 'U':
				return 0;
			case 'D':
				return 2;
			case 'L':
				return 1;
			default:
				return 3;		
		}
	}
	public static char getChar(int d) {
		switch(d) {
			case 0:
				return '^';
			case 1:
				return '<';
			case 2:
				return 'v';
			default:
				return '>';		
		}
	}
	public static boolean canMove(char c) {
		switch(c) {
			case '.':
				return true;
			default:
				return false;		
		}
	}
	
	public static boolean check(int r, int c) {
		return r >= 0 && c >= 0 && r < H && c < W;
	}
}