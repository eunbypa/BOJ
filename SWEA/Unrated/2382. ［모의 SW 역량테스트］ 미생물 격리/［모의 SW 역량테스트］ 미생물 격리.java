import java.util.*;
import java.io.*;
//부분집합
public class Solution {
	static class Group{
		int r;
		int c;
		int cnt;
		int d;
		public Group(int r, int c, int cnt, int d) {
			super();
			this.r = r;
			this.c = c;
			this.cnt = cnt;
			this.d = d;
		}
		
	}
	static int N, M,K;
	static List<Group>[][] map;
	static List<Group> groups;
	static int[] dr = {0,-1,1,0,0}; 
	static int[] dc = {0,0,0,-1,1};
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T;
		T=Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		// st = new StringTokenizer(br.readLine());
		// Integer.parseInt(st.nextToken());
		
		for(int test_case = 1; test_case <= T; test_case++)
		{
			groups = new ArrayList<>();
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			map = new ArrayList[N][N];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					map[i][j] = new ArrayList<>();
				}
			}
			int r, c, cnt,d =-1;
			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine());
				r = Integer.parseInt(st.nextToken());
				c=Integer.parseInt(st.nextToken());
				cnt=Integer.parseInt(st.nextToken());
				d=Integer.parseInt(st.nextToken());
				groups.add(new Group(r,c,cnt,d));
			}
			int size;
			Group g;
			int nr, nc, sum, max;
			int ans = 0;
			while(M-->0) {
				size = groups.size();
				//해당 방향으로 1칸 움직이기
				for (int i = size-1; i >= 0; i--) {
					g=groups.get(i);
					groups.remove(i);
					nr = g.r+dr[g.d];
					nc = g.c+dc[g.d];
					//약품 칸에 진입
					if(nr == 0 || nc == 0 || nr == N-1 || nc == N-1) {
						g.cnt = g.cnt/2;
						g.d = changeDirection(g.d);
					}
					g.r = nr;
					g.c = nc;
					if(g.cnt == 0) continue; // 죽은 군집
					map[nr][nc].add(g);
				}
				ans = 0;
				// 두개 이상의 군집이 한 셀에 모이는 경우
				for (int i = 0; i < N; i++) {
					for (int j = 0; j < N; j++) {
						int size2 = map[i][j].size();
						if(size2==0) continue;
						if(size2 >= 2) {
							sum = 0;
							max = 0;
							for (int j2 = size2-1; j2 >= 0; j2--) {
								g = map[i][j].get(j2);
								map[i][j].remove(j2);
								cnt = g.cnt;
								sum+=cnt;
								if(max < cnt) {
									max = cnt;
									d = g.d;
								}
							}
							map[i][j].add(new Group(i,j,sum,d));
						}
						groups.add(map[i][j].get(0));
						ans += map[i][j].get(0).cnt;
						map[i][j].remove(0);
					}
				}
			}
			sb.append("#"+test_case+" "+ans+"\n");
		}
		System.out.println(sb.toString());
	}
	static int changeDirection(int d) {
		if(d == 1) return 2; // 상
		if(d==2) return 1; // 하
		if(d==3) return 4; // 좌
		return 3; // 우
	}

}