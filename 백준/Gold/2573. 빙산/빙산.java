import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static final int[] DR_ARR = {-1, 0, 1, 0};
    static final int[] DC_ARR = {0, -1, 0, 1};

    static int remainIceBergLocCnt = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[][] map = new int[N][M];
        Queue<int[]> icebergLocQ = new LinkedList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 0) continue;
                remainIceBergLocCnt++;
                icebergLocQ.offer(new int[]{i, j});
            }
        }

        System.out.println(getAnswer(N, M, icebergLocQ, map));
    }

    // 구현 + 시뮬레이션
    // bfs로 빙산이 2조각 이상 나뉘었는지 확인
    static int getAnswer(int N, int M, Queue<int[]> icebergLocQ, int[][] map) {
        int[][] seaAdjArr = setSeaAdjArr(N, M, icebergLocQ, map);
        int[][] visited = new int[N][M];
        int year = 0, mark = 1;
        while (!icebergLocQ.isEmpty() && getSeparatedIcebergCnt(icebergLocQ, N, M, mark, map, visited) == 1) {
            year++;
            melt(N, M, icebergLocQ, seaAdjArr, map);
            mark++;
        }

        return (!icebergLocQ.isEmpty()) ? year : 0;
    }

    // 빙산 녹이기
    static void melt(int N, int M, Queue<int[]> icebergLocQ, int[][] seaAdjArr, int[][] map) {
        int[] cur;
        int cnt = 0;

        do {
            cnt++;
            cur = icebergLocQ.poll();
            if(map[cur[0]][cur[1]] - seaAdjArr[cur[0]][cur[1]] <= 0) {
                map[cur[0]][cur[1]] = 0;
            }else
                map[cur[0]][cur[1]] -= seaAdjArr[cur[0]][cur[1]];
            icebergLocQ.offer(cur);
        }
        while (cnt != remainIceBergLocCnt);

        cnt = 0;
        int dec = 0;

        do {
            cnt++;
            cur = icebergLocQ.poll();
            if(map[cur[0]][cur[1]] == 0) {
                updateSeaAdjArr(cur[0], cur[1], N, M, seaAdjArr, map);
                dec++;
                continue;
            }
            icebergLocQ.offer(cur);
        }
        while (cnt != remainIceBergLocCnt);

        remainIceBergLocCnt -= dec;
    }

    // r,c칸이 바다가 됨에 따라 동서남북 칸 중 빙산인 칸의 인접 바다 개수 1 증가됨
    static void updateSeaAdjArr(int r, int c, int N, int M, int[][] seaAdjArr, int[][] map) {
        int nr, nc;

        for (int i = 0; i < 4; i++) {
            nr = r + DR_ARR[i];
            nc = c + DC_ARR[i];

            if(isOut(nr, nc, N, M) || map[nr][nc] == 0) continue;
            seaAdjArr[nr][nc]++;
        }

    }

    static int[][] setSeaAdjArr(int N, int M, Queue<int[]> icebergLocQ, int[][] map) {
        int[][] adjArr = new int[N][M];
        int[] cur;
        int cnt = 0;
        do {
            cnt++;
            cur = icebergLocQ.poll();
            adjArr[cur[0]][cur[1]] = getSeeAdjCnt(cur[0], cur[1], N, M, map);
            icebergLocQ.offer(cur);
        }
        while (cnt != remainIceBergLocCnt);

        return adjArr;
    }

    // r,c 위치의 동서남북에 인접한 바다 영역 개수 반환
    static int getSeeAdjCnt(int r, int c, int N, int M, int[][] map) {
        int nr, nc, cnt = 0;
        for (int i = 0; i < 4; i++) {
            nr = r + DR_ARR[i];
            nc = c + DC_ARR[i];

            if(isOut(nr, nc, N, M)) continue;
            if(map[nr][nc] == 0) cnt++;
        }

        return cnt;
    }

    // 맵 밖으로 벗어나는지 확인
    static boolean isOut(int r, int c, int N, int M) {
        return (r < 0 || c < 0 || r >= N || c >= M);
    }

    // 빙산 조각 나뉘어진 개수 반환
    static int getSeparatedIcebergCnt(Queue<int[]> icebergLocQ, int N, int M, int mark, int[][] map, int[][] visited) {
        int icebergCnt = 0, cnt = 0;
        int[] cur;

        do {
            cnt++;
            cur = icebergLocQ.poll();
            icebergLocQ.offer(cur);
            if(visited[cur[0]][cur[1]] == mark) continue;
            bfs(cur[0], cur[1], mark, N, M, map, visited);
            icebergCnt++;
        }
        while (cnt != remainIceBergLocCnt);

        return icebergCnt;
    }

    static void bfs(int r, int c, int mark, int N, int M, int[][] map, int[][] visited) {
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{r, c});
        visited[r][c] = mark;
        int[] cur;
        int nr, nc;
        while (!q.isEmpty()) {
            cur = q.poll();

            for (int i = 0; i < 4; i++) {
                nr = cur[0] + DR_ARR[i];
                nc = cur[1] + DC_ARR[i];

                if(isOut(nr, nc, N, M) || map[nr][nc] == 0 || visited[nr][nc] == mark) continue;
                visited[nr][nc] = mark;
                q.offer(new int[]{nr, nc});
            }
        }
    }


}