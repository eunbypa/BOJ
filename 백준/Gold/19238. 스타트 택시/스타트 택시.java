import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static final int[] DR = {-1, 0, 1, 0};
    static final int[] DC = {0, -1, 0, 1};
    static final int FAIL = -1;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int fuel = Integer.parseInt(st.nextToken());
        int[][] map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        st = new StringTokenizer(br.readLine());
        int[] bj = new int[2];
        for (int i = 0; i < 2; i++) {
            bj[i] = Integer.parseInt(st.nextToken()) - 1;
        }


        int[][] passengerArr = new int[M][4];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 4; j++) {
                passengerArr[i][j] = Integer.parseInt(st.nextToken()) - 1;
            }
        }

        System.out.println(getAnswer(N, M, fuel, bj, map, passengerArr));
    }

    static int getAnswer(int n, int m, int fuel, int[] bj, int[][] map, int[][] passengerArr) {
        int[][] shortestDistArr = new int[n][n];
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                // 최단거리가 동일함
                if(o1[4] == o2[4]) {
                    // 행이 동일함
                    if(o1[0] == o2[0]) {
                        // 열 기준
                        return Integer.compare(o1[1], o2[1]);
                    }
                    // 행 기준
                    return Integer.compare(o1[0], o2[0]);
                }
                // 최단거리 기준
                return Integer.compare(o1[4], o2[4]);
            }
        });

        int ans = 0;
        for (int i = 0; i < m; i++) {
            pq.offer(new int[]{passengerArr[i][0], passengerArr[i][1], passengerArr[i][2], passengerArr[i][3], 0});
        }

        int[] next;
        int reqFuel;

        do {
            // 현재 백준의 위치를 기준으로 맵의 각 칸까지의 최단 거리 설정
            initArr(shortestDistArr, n, n, -1);
            setShortestDistArr(bj, map, shortestDistArr);
            // 우선순위 큐 값 갱신
            updatePQ(pq, m, shortestDistArr);
            // 태울 승객을 선정
            next = pq.poll();
            reqFuel = next[4];
            // 벽으로 가로막혀 이동 불가능하거나 승객 위치까지 이동에 필요한 연료 부족으로 실패
            if(isBlocked(next[4]) || isFuelLessThan(fuel, reqFuel)) return FAIL;
            // 승객 태움
            fuel = updateFuel(fuel, -reqFuel);
            // 승객의 출발지부터 목적지까지의 거리 구하기
            reqFuel = getDistFromStartToEnd(next, map);
            // 벽으로 가로막혀 이동 불가능하거나 승객의 목적지까지 이동에 필요한 연료 부족으로 실패
            if(isBlocked(next[4]) || isFuelLessThan(fuel, reqFuel)) return FAIL;
            // 승객을 목적지에 데려다줌
            fuel = updateFuel(fuel, -reqFuel);
            // 목적지에 데려다줬으므로 소모 연료의 2배 충전
            fuel = updateFuel(fuel, 2 * reqFuel);
            // 백준 현재 위치 갱신
            bj[0] = next[2];
            bj[1] = next[3];
            // 태워야 하는 승객 1 감소
            m--;
        } while (m > 0);

        ans = fuel;
        return ans;
    }

    static boolean isBlocked(int i) {
        return i == FAIL;
    }

    // 출발지 - 도착지 거리 구하기
    static int getDistFromStartToEnd(int[] loc, int[][] map) {
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{loc[0], loc[1], 0});
        int[][] visited = new int[map.length][map.length];
        visited[loc[0]][loc[1]] = 1;
        int[] cur;
        int nr, nc, dist = 0;
        while(!q.isEmpty()) {
            cur = q.poll();
            // 도착지 도착
            if(cur[0] == loc[2] && cur[1] == loc[3]) {
                dist = cur[2];
                break;
            }
            for (int i = 0; i < 4; i++) {
                nr = cur[0] + DR[i];
                nc = cur[1] + DC[i];
                if(isOut(nr, nc, map.length) || isWall(nr, nc, map) || visited[nr][nc] == 1) continue;
                visited[nr][nc] = 1;
                q.offer(new int[]{nr, nc, cur[2]+1});
            }
        }

        return dist;
    }

    static boolean isFuelLessThan(int fuel, int i) {
        return fuel < i;
    }


    static int updateFuel(int fuel, int i) {
        return fuel + i;
    }

    static void updatePQ(PriorityQueue<int[]> pq, int m, int[][] shortestDistArr) {
        int[] cur;
        Queue<int[]> q = new LinkedList<>();
        while(!pq.isEmpty()) {
            q.offer(pq.poll());
        }

        while(!q.isEmpty()) {
            cur = q.poll();
            cur[4] = shortestDistArr[cur[0]][cur[1]];
            pq.offer(cur);
        }

    }


    static void initArr(int[][] arr, int rl, int cl, int val) {
        for (int i = 0; i < rl; i++) {
            for (int j = 0; j < cl; j++) {
                arr[i][j] = val;
            }
        }
    }

    // bfs로 현재 백준 위치에서 모든 칸까지의 최단거리 구하기
    static void setShortestDistArr(int[] loc, int[][] map, int[][] shortestDistArr) {
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{loc[0], loc[1], 0});
        shortestDistArr[loc[0]][loc[1]] = 0;
        int[] cur;
        int nr, nc;
        while(!q.isEmpty()) {
            cur = q.poll();

            for (int i = 0; i < 4; i++) {
                nr = cur[0] + DR[i];
                nc = cur[1] + DC[i];
                if(isOut(nr, nc, map.length) || isWall(nr, nc, map) ||
                        (shortestDistArr[nr][nc] != -1 && shortestDistArr[nr][nc] <= cur[2] + 1)) continue;
                shortestDistArr[nr][nc] = cur[2] + 1;
                q.offer(new int[]{nr, nc, cur[2] + 1});
            }
        }
    }

    static boolean isWall(int nr, int nc, int[][] map) {
        return map[nr][nc] == 1;
    }

    static boolean isOut(int nr, int nc, int l) {
        return nr < 0 || nc < 0 || nr >= l || nc >= l;
    }


}