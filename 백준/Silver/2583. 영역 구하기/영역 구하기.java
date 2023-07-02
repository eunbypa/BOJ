import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int M, N, K, cnt;
    static int[][] map;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, -1, 0, 1};
    static List<Integer> areas = new ArrayList<>();
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new int[M][N];
        int x1,y1,x2,y2,sr,sc,er,ec;
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            x1 = Integer.parseInt(st.nextToken()); // 열
            y1 = Integer.parseInt(st.nextToken()); // 행
            x2 = Integer.parseInt(st.nextToken());
            y2 = Integer.parseInt(st.nextToken());
            if(x1 >= x2){
                sc = x2;
                ec = x1;
            }else{
                sc = x1;
                ec = x2;
            }
            if(y1 >= y2){
                sr = y2;
                er = y1;
            }else{
                sr = y1;
                er = y2;
            }
            fill(sr, sc, er, ec);
        }
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if(map[i][j] == 1) continue; // 직사각형 영역
                if(map[i][j] == -1) continue; // 이미 검사한 영역
                cnt++; // 분리된 영역의 개수 1 증가
                areas.add(bfs(i, j)); // 각 분리된 영역의 넓이 저장
            }
        }
        sb.append(cnt + "\n");
        Collections.sort(areas);
        for (int i = 0; i < cnt; i++) {
            sb.append(areas.get(i));
            if(i == cnt-1) continue;
            sb.append(" ");
        }
        System.out.println(sb.toString());
    }

    //직사각형 부분에 해당하는 맵 영역을 1로 채우는 함수
    static void fill(int sr, int sc, int er, int ec){
        for (int i = sr; i < er; i++) {
            for (int j = sc; j < ec; j++) {
                map[i][j] = 1;
            }
        }
    }

    // flood fill
    static int bfs(int r, int c){
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{r, c});
        map[r][c] = -1;
        int area = 1, nr, nc;
        int[] cur;
        while(!q.isEmpty()){
            cur = q.poll();
            for (int i = 0; i < 4; i++) {
                nr = cur[0] + dr[i];
                nc = cur[1] + dc[i];
                // 전체 영역 밖이거나 직사각형 영역이거나 이미 검사한 영역인 경우 스킵
                if(!check(nr,nc) || map[nr][nc] == 1 || map[nr][nc] == -1) continue;
                area++;
                map[nr][nc] = -1; // 영역 확인 시 -1로 바꾸기
                q.offer(new int[]{nr, nc});
            }
        }
        return area;
    }

    // 영역 밖인지 검사하는 함수
    private static boolean check(int nr, int nc) {
        return nr >= 0 && nc >= 0 && nr < M && nc < N;
    }

}