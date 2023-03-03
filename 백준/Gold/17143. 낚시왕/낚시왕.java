import java.util.*;
import java.io.*;

public class Main {
	static class Shark{
		int r; 
		int c;
		int s;
		int d;
		int z;
		public Shark(int r, int c, int s, int d, int z) {
			this.r = r;
			this.c = c;
			this.s = s;
			this.d = d;
			this.z = z;
		}
	}
	static int time;
	static int R,C,M,max;
	static List<Shark>[][] map;
	static List<Shark> sharks;
	static int[] dr = {0,-1,1,0,0};
	static int[] dc = {0,0,0,1,-1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R= Integer.parseInt(st.nextToken());
		C= Integer.parseInt(st.nextToken());
		M= Integer.parseInt(st.nextToken());
		R++;
		C++;
		map = new ArrayList[R][C];
		sharks = new ArrayList<>();
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				map[i][j] = new ArrayList<>();
			}
		}
		int r,c,s,d,z;
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			r = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			s = Integer.parseInt(st.nextToken());
			d = Integer.parseInt(st.nextToken());
			z = Integer.parseInt(st.nextToken());
			map[r][c].add(new Shark(r,c,s,d,z));
		}
		Shark sh;
		//낚시왕 이동 시작
		for (int i = 1; i < C; i++) {
			time++;
			for (int j = 1; j < R; j++) {
				if(map[j][i].size() != 0) { // 상어가 있다면
					max += map[j][i].get(0).z; // 잡은 상어의 크기 더하기
					map[j][i].remove(0); // 상어 삭제
					break;
				}
			}
			//상어 이동 차례, 일단 각 맵을 순회하며 상어가 있으면 sharks에 추가
			for (int k = 0; k < R; k++) {
				for (int j = 0; j < C; j++) {
					if(map[k][j].size() == 0) continue; //상어없다
					sharks.add(map[k][j].get(0));
					map[k][j].remove(0);
				}
			}
			//현재 칸에 있는 상어들 이동시키기
			for (int j = sharks.size()-1; j >= 0; j--) {
				sharkMoving(sharks.get(j));
				sharks.remove(j);
			}
			//같은 칸에 상어가 2마리 이상 있는 경우
			for (int k = 1; k < R; k++) {
				for (int j = 1; j < C; j++) {
					if(map[k][j].size() < 2) continue; // 관계 없음
					sh = findWinner(k,j);
					map[k][j].add(sh); // 승자만 다시 해당 칸에 추가
				}
			}
		}
		System.out.println(max);
	}
	// 상어 이동 처리
	public static void sharkMoving(Shark sh) {
		int r = sh.r, c = sh.c, d = sh.d,s = sh.s;
		 // 상하 방향이면 속력을 열로 나눈 나머지, 좌우방향 이면 행으로 나눈 나머지
		// 2배한 값으로 나누는 이유는 주기 때문, 2배만큼 움직여야 반복되기 때문
		s = (d == 1 || d == 2) ? s%(2*(R-2)) : s%(2*(C-2));
		int nr = r +dr[d]*s, nc = c+dc[d]*s;
		if(!check(nr,nc)) { // 중간에 방향 한번 바꿔야함, 범위를 벗어날 수 있으므로
			nr = r;
			nc = c;
			while(s != 0) {
				s--;
				if(!check(nr+dr[d],nc+dc[d])) {
					if(d == 1) d = 2;
					else if(d == 2) d = 1;
					else if(d == 3) d = 4;
					else if(d == 4) d = 3;
				}
				nr += dr[d];
				nc += dc[d];
			}
		}
		sh.r = nr;
		sh.c = nc;
		sh.d = d;
		map[nr][nc].add(sh); // 해당 칸으로 상어 이동
	}
	//같은 칸에 상어가 여러마리 있는 경우 상어의 크기가 가장 큰 상어만 살아남는다.
	public static Shark findWinner(int r, int c) {
		Shark sh = null;
		int size = 0;
		for (int i = map[r][c].size()-1; i >= 0; i--) {
			if(map[r][c].get(i).z > size) {
				size = map[r][c].get(i).z;
				sh = map[r][c].get(i);
			}
			map[r][c].remove(i);
		}
		return sh;
	}
	public static boolean check(int r, int c) {
		return r >= 1 && c >= 1 && r < R && c < C;
	}
}