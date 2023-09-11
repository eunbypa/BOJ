import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int R,C,N;
    static char[][] map;
    static int[][] time; // 폭탄이 설치되고 지난 시간 기록
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0,-1, 0, 1};
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        map = new char[R][C];
        time = new int[R][C];
        for (int i = 0; i < R; i++) {
            map[i] = br.readLine().toCharArray();
            for (int j = 0; j < C; j++) {
                if(map[i][j] == 'O'){
                    // 처음 1초 동안 아무것도 하지 않으므로 시작시간을 2로 세팅
                    time[i][j] = 2;
                }
            }
        }

        for (int i = 2; i <= N; i++) {
            // 폭탄 시간 갱신
            updateBomb();
            // 폭탄 설치
            if(i % 2 == 0){
                setBomb();
            }
            // 폭탄 폭발
            if(i % 2 == 1){
                checkBomb();
            }
        }
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if(time[i][j] == 0){
                    sb.append('.');
                }else {
                    sb.append('O');
                }
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }

    // 폭탄 설치
    public static void setBomb() {
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if(time[i][j] == 0){
                    time[i][j]++;
                }
            }
        }
    }

    // 맵에 놓인 폭탄을 확인하고 시간을 1 증가
    public static void updateBomb() {
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if(time[i][j] == 0) continue; // 폭탄 없는 칸
                time[i][j]++;
            }
        }
    }

    // 폭탄 폭발 시간인지 확인
    public static void checkBomb() {
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if(time[i][j] == 4){
                    // 4초면 폭발
                    boom(i,j);
                }
            }
        }
    }

    // 폭탄 폭발
    public static void boom(int r, int c) {
        time[r][c] = 0;
        int nr, nc;
        for (int i = 0; i < 4; i++) {
            nr = r + dr[i];
            nc = c + dc[i];
            if (check(nr, nc) && time[nr][nc] != 4) {
                // 이미 폭발 예정인 폭탄은 제외
                time[nr][nc] = 0;
            }
        }
    }

    // 범위 밖인지 확인
    public static boolean check(int r, int c) {
        return r >= 0 && c >= 0 && r < R && c < C;
    }

}