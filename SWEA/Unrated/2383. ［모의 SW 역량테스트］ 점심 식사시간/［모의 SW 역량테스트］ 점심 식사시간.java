import java.util.*;
import java.io.*;

public class Solution {
	static int N, pNum, min;
	static int[][] map;
	static List<int[]> people;
	static int[] selected;
	static int[][] stairs;
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
			people = new ArrayList<>();
			min = Integer.MAX_VALUE;
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			stairs = new int[2][2];
			int idx = 0;
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if(map[i][j] == 1) {
						people.add(new int[] {i,j});
					}else if(map[i][j] == 0) continue;
					else { // 계단
						stairs[idx][0] = i;
						stairs[idx][1] = j;
						idx++;
					}
				}
			}
			pNum = people.size();
			selected = new int[pNum];
			subset(0);
			sb.append("#"+test_case+" "+min+"\n");
		}
		System.out.println(sb.toString());
	}
	//부분집합으로 각 사람이 어떤 계단을 선택하는지를 정해줌
	static void subset(int idx) {
		if(idx == pNum) {
			min = Math.min(min, calTime());
			return;
		}
		//계단 2개중 하나 선택
		selected[idx] = 0;
		subset(idx+1);
		selected[idx] = 1;
		subset(idx+1);
	}
	//전부 이동 완료까지 걸리는 시간을 반환
	static int calTime() {
		int time = 0;
		int[] arrivedTime = new int[pNum]; // 계단까지의 도착 시간 저장
		boolean[] finished = new boolean[pNum]; // 이동 완료된 사람은 true
		//int[] stairPeople = new int[2]; // 계단에 이동중인 사람 수 저장
		List<int[]>[] stairMovePeople = new ArrayList[2]; // 계단에서 이동중인 사람 저장
		List<int[]>[] stairWaitPeople = new ArrayList[2]; // 계단 이동 대기중인 사람 저장
		stairMovePeople[0] = new ArrayList<>();
		stairMovePeople[1] = new ArrayList<>();
		stairWaitPeople[0] = new ArrayList<>();
		stairWaitPeople[1] = new ArrayList<>();
		int finCnt = 0;
		int[] p;
		int[] tmp;
		for (int i = 0; i < pNum; i++) {
			p = people.get(i);
			arrivedTime[i] = Math.abs(p[0]-stairs[selected[i]][0])+Math.abs(p[1]-stairs[selected[i]][1]);
		}
		//모두 이동 완료할때까지 시간 1씩 증가하면서 진행
		while(finCnt != pNum) {
			time++;
			//계단 이동 단계
			for (int i = 0; i < 2; i++) {
				for (int j = stairMovePeople[i].size()-1; j >= 0; j--) {
					//계단 이용 완료 
					if(stairMovePeople[i].get(j)[2] != time && stairMovePeople[i].get(j)[1] == map[stairs[selected[stairMovePeople[i].get(j)[0]]][0]][stairs[selected[stairMovePeople[i].get(j)[0]]][1]]) {
						finished[stairMovePeople[i].get(j)[0]] = true;
						finCnt++;
						stairMovePeople[i].remove(j);
					}else if(stairMovePeople[i].get(j)[2] != time) {
						tmp =stairMovePeople[i].get(j);
						tmp[1]++;
						stairMovePeople[i].remove(j);
						stairMovePeople[i].add(tmp);
					}
				}
			}
			//계단 대기중인 사람 단계
			for (int i = 0; i < 2; i++) {
				for (int j = stairWaitPeople[i].size()-1; j >=0; j--) {
					if(stairWaitPeople[i].get(j)[1] != time && stairMovePeople[i].size() < 3) { // 계단 이동 시작 가능
						stairMovePeople[i].add(new int[] {stairWaitPeople[i].get(j)[0],1, time});
						stairWaitPeople[i].remove(j);
					}
				}
			}
			//계단 입구까지의 단계
			for (int i = 0; i < pNum; i++) {
				if(finished[i]) continue; //이미 완료한 사람
				if(time < arrivedTime[i]) continue; // 아직 계단 도착시간이 아님
				if(time == arrivedTime[i]) { // 계단 입구 도착
					//계단 이용 가능한 상태
					if(stairMovePeople[selected[i]].size() < 3) {
						stairMovePeople[selected[i]].add(new int[] {i, 0, time});
					}else { // 이용 불가능, 대기중 리스트 삽입
						stairWaitPeople[selected[i]].add(new int[] {i,time});
					}
				}
			}
        }
		return time;
	}
}