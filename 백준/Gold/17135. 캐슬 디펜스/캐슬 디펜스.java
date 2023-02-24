import java.util.*;
import java.io.*;
public class Main {
	static int N,M,D,enemyCnt,max;
	static int[][] map;
	static int[][] copy;
	static int[] archer;
	static int[][] targets = new int[3][2];
	static int[] dr = {0,-1,0,1};
	static int[] dc = {-1,0,1,0};
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N =  Integer.parseInt(st.nextToken());
		M =  Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		copy = new int[N][M];
		archer = new int[3];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j]==1) enemyCnt++;
			}
		}
		combi();
		System.out.println(max);
	}
	public static void combi() {
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < M; j++) {
				for (int j2 = 0; j2 < M; j2++) {
					if(i==j || j == j2 || j2 == i) continue;
					archer[0] = i;
					archer[1] = j;
					archer[2] = j2;
					/*System.out.println("아처 뽑은 상황");
					for (int k = 0; k <3; k++) {
						System.out.print(archer[k]+" ");
					}
					System.out.println();*/
					for (int k = 0; k < N; k++) {
						copy[k]=Arrays.copyOf(map[k], M);
					}
					attack();
				}
			}
		}
	}
	public static void attack() {
		int cnt = 0;
		int eCnt = enemyCnt;
		boolean[] find = new boolean[3];
		while(eCnt != 0) {
			for (int i = 0; i < 3; i++) {
				find[i] = findTarget(i); // 다음에 공격할 적 파악
				//System.out.println(targets[i][0]+" "+targets[i][1]);
			}
			for (int i = 0; i < 3; i++) {
				if(!find[i]) continue; // 공격할 적이 없음
				if(copy[targets[i][0]][targets[i][1]] == 1) {
					//System.out.println("적 제거 -> "+targets[i][0]+" "+targets[i][1]);
					copy[targets[i][0]][targets[i][1]] = 0; // 적 제거
					cnt++;
					eCnt--;
				}
			}
			eCnt = enemyMoving(eCnt);
		}
		max = Math.max(max, cnt);
	}
	//적 움직임
	public static int enemyMoving(int cnt) {
		for (int i = N-1; i >= 0; i--) {
			for (int j = 0; j < M; j++) {
				if(copy[i][j] == 1) { // 적이 존재하는 칸이면 적을 한칸 아래로 전진
					copy[i][j] = 0;
					if(i==N-1) { // 성에 갈수 있는 적이면 그대로 사라짐
						cnt--;
						continue;
					}
					copy[i+1][j] = 1;
				}
			}
		}
		return cnt;
	}
	public static boolean findTarget(int idx) {
		int length = 0;
		int min = 1000;
		boolean find = false;
		//int[] target = new int[] {-1,-1};
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(copy[i][j] == 0) continue;
				length = Math.abs(N-i)+Math.abs(j-archer[idx]);
				if(length <= D && min > length) {
					min = length;
					find = true;
					targets[idx][0] = i;
					targets[idx][1] = j;
				}else if(min == length) {
					if(j < targets[idx][1]) {
						targets[idx][0] = i;
						targets[idx][1] = j;
					}
				}
			}
		}
		if(!find) return false;
		else return true;
	}
	public static boolean check(int r, int c) {
		return r >= 0 && c >= 0 && r < N && c < M;
	}
}