import java.util.*;
import java.io.*;
public class Solution {
	//칸의 시작부터 끝까지 해당 범위의 벌통에서 얻을 수 있는 최대의 벌꿀 비용 저장
	static class Honey {
		 int r; // 행 위치
		 int sc; // 시작 열 위치
		 int ec; // 끝 열 위치
		 int costSum; // 전체 벌꿀 비용 합
		public Honey(int r, int sc, int ec, int costSum) {
			super();
			this.r = r;
			this.sc = sc;
			this.ec = ec;
			this.costSum = costSum;
		}
		@Override
		public String toString() {
			return "Honey [r=" + r + ", sc=" + sc + ", ec=" + ec + ", costSum=" + costSum + "]";
		}
		
	}
	static int N, M, C, max;
	static int[][] map;
	static List<Honey> hList;
	static int m; // 해당 범위의 벌꿀에서 산출할 수 있는 최대 비용의 합을 저장하는 용도  
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int T= Integer.parseInt(br.readLine());
		for(int test_case = 1; test_case <= T; test_case++)
		{
			hList = new ArrayList<>();
			st = new StringTokenizer(br.readLine());
			max = 0;
			m = 0;
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			map = new int[N][N];
	
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j]=Integer.parseInt(st.nextToken());
				}
				findHoneyCostSum(i);
			}
			findAnswer();
			sb.append("#"+test_case+" "+max+"\n");
		}
		System.out.println(sb.toString());
	}
	//겹치지 않는 선에서 두 개의 Honey를 선택해 비용의 합이 최대가 되는 경우를 구하자.
	static void findAnswer() {
		Honey h1, h2;
		for (int i = 0; i < hList.size(); i++) {
			for (int j = 0; j < hList.size(); j++) {
				if(i==j) continue;
				h1 = hList.get(i);
				h2 = hList.get(j);
				if((h1.r == h2.r) && (h1.sc <= h2.sc) && (h2.sc <= h1.ec)) continue; //범위가 겹치는 경우
				if((h1.r == h2.r) && (h2.sc <= h1.sc) && (h1.sc <= h2.ec)) continue; //범위가 겹치는 경우
				max = Math.max(max, h1.costSum+h2.costSum);
			}
		}
	}
	//현재 행에서 슬라이딩 윈도우 기법으로 한칸씩 옮겨가며 해당 범위 내 벌꿀 합을 파악하고, 
	//C를 넘어가는 경우 부분집합으로 합이 C를 넘어가지 않는 선에서 최대의 비용을 산출하게 선택하도록 함
	static void findHoneyCostSum(int r) {
		int sum = 0;
		int costSum = 0;
		m = 0; // 초기화
		for (int i = 0; i < M; i++) {
			sum += map[r][i]; // 초기값 세팅
			costSum += map[r][i]*map[r][i]; //수익의 합 구하기
		}
		if(sum > C) { // 벌꿀 합이 C를 넘어가는경우
			subset(r, 0, M, 0,0);
			hList.add(new Honey(r,0,M-1,m)); // 부분집합을 통해 계산한 최대 비용으로 저장
		}else {
			hList.add(new Honey(r,0,M-1,costSum)); // 원래 계산해둔 비용으로 저장
		}
		for (int i = M; i < N; i++) {
			m = 0; // 초기화
			sum -= map[r][i-M]; // 그전값 빼고
			costSum -= map[r][i-M]*map[r][i-M];
			sum += map[r][i]; // 새로운 값 넣기
			costSum += map[r][i]*map[r][i];
			if(sum > C) { // 벌꿀 합이 C를 넘어가는경우
				subset(r, i-M+1, i+1, 0,0);
				hList.add(new Honey(r,i-M+1, i,m)); // 부분집합을 통해 계산한 최대 비용으로 저장
			}else {
				hList.add(new Honey(r,i-M+1, i,costSum)); // 원래 계산해둔 비용으로 저장
			}
		}
	}
	//C를 넘어가는 경우 부분집합으로 합이 C를 넘어가지 않는 선에서 최대의 비용을 산출하게 선택
	static void subset(int r, int s, int e, int sum, int costSum) {
		if(sum > C) return;
		if(s == e) {
			m = Math.max(m,costSum);
			return;
		}
		subset(r,s+1,e, sum+map[r][s], costSum+map[r][s]*map[r][s]); // 해당 벌꿀을 선택하고 넘어감
		subset(r,s+1,e, sum, costSum); // 해당 벌꿀을 선택안하고 넘어감
	}
}
