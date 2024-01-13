import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    static int[][] map;
    static int[][] likes;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, -1, 0, 1};

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        likes = new int[N*N+1][4];
        int end = N * N;
        StringTokenizer st;
        int n;
        for (int i = 0; i < end; i++) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            for (int j = 0; j < 4; j++) {
                likes[n][j] = Integer.parseInt(st.nextToken());
            }
            setStudent(n);
        }
        // 만족도 총합 구하기
        int sum = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                sum += getScore(getNearLikes(map[i][j], i, j));
            }
        }
        System.out.println(sum);
    }

    public static int getScore(int n) {
        switch (n) {
            case 0:
                return 0;
            case 1:
                return 1;
            case 2:
                return 10;
            case 3:
                return 100;
            default: // 4
                return 1000;
        }
    }

    // 자리 배정
    public static void setStudent(int n) {
        int max = 0;
        Queue<int[]> q = new LinkedList<>();
        int[] tmp;
        // 1. 비어있는 칸 중에서 좋아하는 학생이 인접한 칸에 가장 많은 칸으로 자리 정하기
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(map[i][j] != 0) continue;
                max = Math.max(max, getNearLikes(n, i, j));
            }
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(map[i][j] != 0) continue;
                if(getNearLikes(n, i, j) == max) q.add(new int[]{i, j});
            }
        }
        if(q.size() == 1) {
            tmp = q.poll();
            map[tmp[0]][tmp[1]] = n;
            return;
        }
        // 2. 비어있는 칸이 인접한 칸에 가장 많은 칸으로 자리 정하기
        int size = q.size();
        max = 0;
        while(size > 0) {
            size--;
            tmp = q.poll();
            max = Math.max(max, getEmpty(tmp[0], tmp[1]));
            q.add(tmp);
        }
        size = q.size();
        while(size > 0) {
            size--;
            tmp = q.poll();
            if(getEmpty(tmp[0], tmp[1]) == max) q.add(tmp);
        }
        if(q.size() == 1) {
            tmp = q.poll();
            map[tmp[0]][tmp[1]] = n;
            return;
        }
        // 3. 행 작은 순
        int min = N;
        size = q.size();
        while(size > 0) {
            size--;
            tmp = q.poll();
            min = Math.min(min, tmp[0]);
            q.add(tmp);
        }
        size = q.size();
        while(size > 0) {
            size--;
            tmp = q.poll();
            if(tmp[0] == min) q.add(tmp);
            q.add(tmp);
        }
        if(q.size() == 1) {
            tmp = q.poll();
            map[tmp[0]][tmp[1]] = n;
            return;
        }
        // 4. 열 작은 순
        min = N;
        int r = 0, c = 0;
        while(!q.isEmpty()) {
            tmp = q.poll();
            if(min > tmp[1]) {
                min = tmp[1];
                r = tmp[0];
                c = tmp[1];
            }
        }
        map[r][c] = n;
    }

    // 인접한 좋아하는 학생 수 구하기
    public static int getNearLikes(int n, int r, int c) {
        int cnt = 0;
        int nr, nc;
        for (int i = 0; i < 4; i++) {
            nr = r+dr[i];
            nc = c+dc[i];
            if(!isInside(nr,nc)) continue;
            if(isLikes(n,map[nr][nc])) cnt++;
        }
        return cnt;
    }

    // 좋아하는 학생인지 확인
    public static boolean isLikes(int a, int b) {
        for (int i = 0; i < 4; i++) {
            if(b == likes[a][i]) return true;
        }
        return false;
    }

    // 인접한 비어있는 칸 개수 구하기
    public static int getEmpty(int r, int c) {
        int cnt = 0;
        int nr, nc;
        for (int i = 0; i < 4; i++) {
            nr = r+dr[i];
            nc = c+dc[i];
            if(!isInside(nr,nc)) continue;
            if(map[nr][nc] == 0) cnt++;
        }
        return cnt;
    }


    // 범위 밖인지 확인
    public static boolean isInside(int r, int c) {
        return r >= 0 && c >= 0 && r < N && c < N;
    }

}