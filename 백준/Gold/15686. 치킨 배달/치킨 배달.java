import java.util.*;
import java.io.*;
public class Main {
	static int N,M, cCnt, hCnt, min = Integer.MAX_VALUE;
	static int[][] map;
	static int[][] chickens;
	static int[][] houses;
	static int[] selected;
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		houses = new int[2*N][2];
		chickens = new int[13][2];
		selected = new int[M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 1) {
					houses[hCnt][0] = i;
					houses[hCnt][1] = j;
					hCnt++;
				}else if(map[i][j] == 2) {
					chickens[cCnt][0] = i;
					chickens[cCnt][1] = j;
					cCnt++;
				}
			}
		}
		combi(0,0);
		System.out.println(min);
	}
	public static void combi(int idx, int cnt) {
		if(cnt == M) {
			min = Math.min(getManhattanSum(), min);
			return;
		}
		for (int i = idx; i < cCnt; i++) {
			selected[cnt] = i;
			combi(i+1, cnt+1);
		}
	}
	public static int getManhattanSum() {
		int sum = 0;
		for (int i = 0; i < hCnt; i++) {
			int minDist = 200;
			for (int j = 0; j < M; j++) { // 선택된 치킨집들중 가장 가까운 치킨 거리 구하는 식
				minDist = Math.min(minDist, Math.abs(houses[i][0]-chickens[selected[j]][0]) + 
						Math.abs(houses[i][1]-chickens[selected[j]][1])); // 맨하탄 거리 구하기
			}
			sum += minDist;
		}
		return sum;
	}
}