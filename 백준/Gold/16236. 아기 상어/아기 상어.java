import java.util.*;
import java.io.*;

public class Main {
	static int N, tot, time;
	static int[][] map;
	static List<int[]>[] fishes = new ArrayList[7]; // 물고기 각 크기당 위치들
	static int[] babyShark = {-1,-1}; // 아기 상어 위치
	static int[] nextFish;
	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,-1,0,1};
	static int size = 2; // 아기 상어 크기
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringTokenizer st;
		map = new int[N][N];
		for (int i = 1; i < 7; i++) {
			fishes[i] = new ArrayList<>();
		}
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 9) {
					babyShark[0] = i;
					babyShark[1] = j;
				}else if(map[i][j] != 0){ // 물고기들
					fishes[map[i][j]].add(new int[] {i,j});
					tot++;
				}
			}
		}
		// 다음으로 먹을 물고기 사이즈, fishes[사이즈]에서의 위치 index, 이동 시 필요한 칸 수
		nextFish = new int[3];
		int[] fish;
		int ate = 0; // 먹은 물고기 수
		while(tot != 0) {
			for (int i = 1; i < size; i++) {
				if(i > 6) break; // 물고기는 최대 크기가 6임
				findCloseFish(i);
			}
			if(nextFish[2] == 0) break;
			tot--;
			time += nextFish[2]; // 이동한 만큼 시간 증가
			//System.out.println("현재 시간 : "+time);
			nextFish[2] = 0;
			fish = fishes[nextFish[0]].get(nextFish[1]); // 현재 먹으려는 물고기 위치
			map[babyShark[0]][babyShark[1]] = 0; // 상어 현재 위치를 0으로 초기화
			babyShark[0] = fish[0]; // 상어 현재 위치 물고기 위치로 갱신
			babyShark[1] = fish[1];
			//System.out.println("현재 위치 : "+Arrays.toString(babyShark));
			map[fish[0]][fish[1]] = 9; // 상어가 해당 위치로 이동 후 물고기 먹음
			fishes[nextFish[0]].remove(nextFish[1]); // 물고기 제거
			ate++; // 먹은 물고기 1 증가
			if(ate == size) { // 크기만큼 먹었으면 크기 1 증가
				ate = 0;
				size++;
			}
			//System.out.println("현재 크기 : "+size);
		}
		System.out.println(time);
	}
	// 해당 사이즈의 가장 가까운 물고기 찾기
	public static void findCloseFish(int s) { // s는 물고기 사이즈
		int cnt = 0;
		for (int i = 0; i < fishes[s].size(); i++) {
			//System.out.println(s+" 크기의 "+i+" 번째 물고기 탐색");
			cnt = bfs(fishes[s].get(i));
			//System.out.println(cnt);
			if(cnt == 0) continue; // 도착할 수 없는 물고기 위치라는 의미
			if(nextFish[2] == 0 || nextFish[2] > cnt) {
				nextFish[0] = s;
				nextFish[1] = i;
				nextFish[2] = cnt;
			}else if(nextFish[2] == cnt) {
				int[] cur = fishes[nextFish[0]].get(nextFish[1]);
				int[] next = fishes[s].get(i);
				if(cur[0] > next[0]) { // 기존에 찾은 물고기보다 더 위쪽에 있다면
					nextFish[0] = s;
					nextFish[1] = i;
				}else if(cur[0] == next[0]) {
					if(cur[1] > next[1]) {// 기존에 찾은 물고기보다 더 왼쪽에 있다면
						nextFish[0] = s;
						nextFish[1] = i;
					}
				}
			}
		}
	}
	// 도착지까지 걸린 칸 수 반환
	public static int bfs(int[] end) {
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] {babyShark[0], babyShark[1], 0});
		int cnt = 0;
		boolean[][] visited = new boolean[N][N];
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			if(cur[0] == end[0] && cur[1] == end[1]) {
				cnt = cur[2];
				break;
			}
			for (int i = 0; i < 4; i++) {
				int nr = cur[0]+dr[i];
				int nc = cur[1]+dc[i];
				if(!check(nr,nc)) continue;
				if(visited[nr][nc]) continue;
				if(size < map[nr][nc]) continue; // 크기가 큰 물고기는 못지나감
				visited[nr][nc] = true;
				q.offer(new int[] {nr,nc,cur[2]+1});
			}
		}
		return cnt;
	}
	public static boolean check(int r, int c) {
		return r >= 0 && c >= 0 && r < N && c < N;
	}
}
