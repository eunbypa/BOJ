import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int[][] map;
    static int N, K, L;

    // 상좌하우
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, -1, 0, 1};
    static char[] dir; // 해당 시간의 방향 전환
    static boolean[][] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        K = Integer.parseInt(br.readLine());
        map = new int[N+1][N+1];
        // 사과
        int r, c;
        StringTokenizer st;
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            r = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());
            map[r][c] = 2; // 사과가 있음
        }
        L = Integer.parseInt(br.readLine());
        dir = new char[10001];
        int x;
        for (int i = 0; i < L; i++) {
            st = new StringTokenizer(br.readLine());
            x = Integer.parseInt(st.nextToken());
            dir[x] = st.nextToken().charAt(0);
        }
        // 게임 시작
        System.out.println(play());
    }

    // 게임 소요 시간 반환
    private static int play() {
        int time = 0;
        Queue<int[]> q = new LinkedList<>();
        int d = 3; // 오른쪽 방향
        int length = 1; // 뱀의 길이
        int cr = 1, cc = 1; // 머리 위치
        int nr, nc;
        q.offer(new int[]{1, 1});
        map[1][1] = 1;
        boolean fin = false;
        int[] last;
        while(!fin) {
            time++;
            // 이동
            nr = cr + dr[d];
            nc = cc + dc[d];
            // 벽 or 뱀의 몸에 부딪혔는가
            if(isWall(nr, nc) || isSnake(nr, nc)) {
                fin = true;
                continue;
            }
            // 사과가 있는지
            if(map[nr][nc] == 2) {
                length++;
            }else { // 없음
                last = q.poll();
                // 꼬리가 있던 위치 빈 자리로 변경
                map[last[0]][last[1]] = 0;
            }
            // 뱀이 차지한 자리
            cr = nr;
            cc = nc;
            map[nr][nc] = 1;
            q.offer(new int[]{nr, nc});
            // 방향 변경 되는지
            if(dir[time] == 'L' || dir[time] == 'D') d = changeDir(d, dir[time]);
        }
        return time;
    }

    // 뱀의 몸과 부딪혔는지 여부
    private static boolean isSnake(int r, int c) {
        return map[r][c] == 1;
    }

    // 벽과 부딪혔는지 여부
    private static boolean isWall(int r, int c) {
        return r < 1 || c < 1 || r > N || c > N;
    }

    // 방향 전환
    private static int changeDir(int d, char type) {
        switch (d) {
            case 0:
                return (type == 'L') ? 1 : 3;
            case 1:
                return (type == 'L') ? 2 : 0;
            case 2:
                return (type == 'L') ? 3 : 1;
            case 3:
                return (type == 'L') ? 0 : 2;
            default:
                return d;
        }
    }

}