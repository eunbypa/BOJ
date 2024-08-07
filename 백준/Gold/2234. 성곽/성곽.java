import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n,m;

    static int[][] map;


    static int[][][] walls; // r행 c칸 0123 서북동남

    static List<Integer> roomSizeList;

    static int[] dr = {0, -1, 0, 1};
    static int[] dc = {-1, 0, 1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[m][n];
        walls = new int[m][n][4];
        roomSizeList = new ArrayList<>();
        int wall;
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                wall = Integer.parseInt(st.nextToken());
                setWall(wall, i, j);
            }
        }

        StringBuilder sb = new StringBuilder();
        // 방의 개수 구하기
        int roomNo = 1;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if(map[i][j] != 0) continue;
                setRoom(roomNo, i, j);
                roomNo++;
            }
        }
        sb.append((roomNo - 1)).append("\n");

        // 가장 넓은 방의 넓이
        int maxSize = 0;
        for (int i = 1; i < roomNo; i++) {
            maxSize = Math.max(maxSize, roomSizeList.get(i - 1));
        }
        sb.append(maxSize).append("\n");

        // 하나의 벽을 제거하여 얻을 수 있는 가장 넓은 방의 크기
        maxSize = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < 4; k++) {
                    maxSize = Math.max(maxSize, getRoomSizeWhenWallIsDisappeared(k, i, j));
                }
            }
        }
        sb.append(maxSize).append("\n");

        System.out.println(sb.toString());
    }

    // 현재 칸에서 d 방향으로 있는 벽을 제거 시 만들어진 방의 크기 구하기
    public static int getRoomSizeWhenWallIsDisappeared(int d, int r, int c) {
        int nr = r + dr[d];
        int nc = c + dc[d];
        // 벽을 허물었을 때 그 너머의 칸이 맵 바깥인 경우, 같은 방인 경우
        if(!isIn(nr, nc) || map[r][c] == map[nr][nc])
            return roomSizeList.get(map[r][c] - 1);

        return roomSizeList.get(map[r][c] - 1) + roomSizeList.get(map[nr][nc] - 1);


    }

    // 각 칸에서 벽이 있는 곳을 확인해 walls 배열에 표시
    public static void setWall(int wall, int r, int c) {
        int check = 1;
        int nr, nc;
        for (int i = 0; i < 4; i++) {
            if((wall & check) != 0) {
                walls[r][c][i] = 1;
                nr = r + dr[i];
                nc = c + dc[i];
                if(isIn(nr, nc)) {
                    walls[nr][nc][(i+2)%4] = 1;
                }
            }

            check <<= 1;
        }
    }


    // flooding으로 하나의 방을 표시 bfs 사용
    public static void setRoom(int roomNo, int r, int c) {
        Queue<int[]> q = new LinkedList<>();
        int[] cur;
        int nr, nc;
        int size = 1;
        map[r][c] = roomNo;
        q.offer(new int[]{r, c});
        while(!q.isEmpty()) {
            cur = q.poll();
            for (int i = 0; i < 4; i++) {
                // 벽으로 가로막힌 경우
                if(walls[cur[0]][cur[1]][i] == 1) continue;
                nr = cur[0] + dr[i];
                nc = cur[1] + dc[i];
                // 맵 바깥으로 나가는 경우, 이미 방문한 상태인 경우
                if(!isIn(nr, nc) || map[nr][nc] != 0) continue;
                size++;
                map[nr][nc] = roomNo;
                q.offer(new int[]{nr, nc});
            }
        }
        // 계산된 방 크기 리스트에 저장
        roomSizeList.add(size);
    }




    // 맵 밖으로 벗어나지 않는지 검사
    public static boolean isIn(int r, int c) {
        return r >= 0 && c >= 0 && r < m && c < n;
    }



}