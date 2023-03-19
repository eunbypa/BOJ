import java.util.*;
import java.io.*;
public class Main {
	static int N,max, score;
	static int[] p; // 선수들 번호, 1은 제외(0은 1번선수, 1번선수는 반드시 4번째이므로)
	static int[][] eanings; // 각 이닝당 선수가 얻는 결과
	static int[] home; // 0 : 홈, 1: 1루, 2: 2루, 3: 3루
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N= Integer.parseInt(st.nextToken());
		eanings = new int[N][10];
		p = new int[8];
		for (int i = 0; i < 8; i++) {
			p[i] = i+2; // 2번부터 9번까지 차례대로 정렬
		}
		int n;
		for (int i = 0; i <N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j < 10; j++) {
				eanings[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		int curE = 0; // 현재 이닝
		int out = 0; // 현재 누적된 아웃 수
		int next = -1; // 제일 마지막에 한 선수 다음 번호
		boolean fourth = false; // 4번째 순서인 1번 타자에서 아웃되었는지 여부
		do {
			//초기화
			score = 0;
			curE = 0;
			out = 0; 
			next = -1;
			fourth = false; 
			//이닝 시작
			while(curE != N) {
				home = new int[4];
				out = 0;
				while(out != 3) {
					//전 이닝에서 마무리된 선수 다음 선수부터 진행
					if(next != -1) { 
						for (int i = next; i < 8; i++) { 
							if(!fourth && i == 3) { // 4번째는 1번타자이므로 1번타자를 먼저 진행하고 다음 타자 진행
								if(eanings[curE][1] == 0) out++; // 아웃 적립
								else { // 안타,2루타,3루타,홈런
									home[0] = 1;
									go(eanings[curE][1]);
								}
							}
							if(out == 3) { // 이닝 종료
								next = i;
								fourth = true;  // 1번 타자 순서에서 이닝 종료됨
								break;
							}
							if(eanings[curE][p[i]] == 0) out++; // 아웃 적립
							else { // 안타,2루타,3루타,홈런
								home[0] = p[i];
								go(eanings[curE][p[i]]);
							}
							if(fourth) fourth = false;
							if(out == 3) { // 이닝 종료
								next = i+1;
								break;
							}
						}
						if(out == 3) { // 이닝 종료
							break;
						}
						next = -1;
					}
					if(out == 3) { // 이닝 종료
						break;
					}
					for (int i = 0; i < 8; i++) { 
						if(!fourth && i == 3) { // 4번째는 1번타자이므로 1번타자를 먼저 진행하고 다음 타자 진행
							if(eanings[curE][1] == 0) out++; // 아웃 적립
							else { // 안타,2루타,3루타,홈런
								home[0] = 1;
								go(eanings[curE][1]);
							}
						}
						if(out == 3) { // 이닝 종료
							next = i;
							fourth = true; // 1번 타자 순서에서 이닝 종료됨
							break;
						}
						if(eanings[curE][p[i]] == 0) out++; // 아웃 적립
						else { // 안타,2루타,3루타,홈런
							home[0] = p[i];
							go(eanings[curE][p[i]]);
						}
						if(fourth) fourth = false;
						if(out == 3) { // 이닝 종료
							next = i+1;
							break;
						}
					}
				}
				curE++;
			}
			max = Math.max(max, score); // 최대 점수 갱신
		}while(nextPerm());
		System.out.println(max);
	}
	static boolean nextPerm() {
		int i = 7;
		int j = 7;
		while(i > 0 && p[i-1] > p[i]) i--;
		if(i==0) return false;
		while(p[i-1] > p[j]) j--;
		int tmp = p[i-1];
		p[i-1] = p[j];
		p[j] = tmp;
		j = 7;
		while(i < j) {
			tmp = p[i];
			p[i] = p[j];
			p[j] = tmp;
			i++;
			j--;
		}
		return true;
	}
	static void go(int n) { // home에 있는 주자들 n만큼 전진
		for (int i = 3; i >= 0; i--) {
			if(home[i] != 0 && i+n >= 4) { // 득점
				home[i] = 0;
				score++;
			}else if(home[i] != 0 && i+n < 4) {
				home[i+n] = home[i];
				home[i] = 0;
			}
		}
	}
}