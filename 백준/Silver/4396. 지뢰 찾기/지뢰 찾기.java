import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static final char BOMB = '*';
    static final char ZERO = '0';
    static final char NINE = '9';
    static final char BASE = '.';
    static final char NOT_OPENED = 'x';
    static final int[] dr = {-1, 0, 1, 0, 1, 1, -1, -1};
    static final int[] dc = {0, -1, 0, 1, -1, 1, 1, -1};


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        char[][] gameMap = new char[n][n];
        char[][] recordedMap = new char[n][n];

        for (int i = 0; i < n; i++) {
            gameMap[i] = br.readLine().toCharArray();
        }

        for (int i = 0; i < n; i++) {
            recordedMap[i] = br.readLine().toCharArray();
        }

        setBombCntOnMap(n, gameMap, recordedMap);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                sb.append(recordedMap[i][j]);
            }
            if(i == n-1) break;
            sb.append("\n");
        }

        System.out.println(sb.toString());
    }

    static void setBombCntOnMap(int n, char[][] gameMap, char[][] recordedMap) {
        boolean fail = false;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                // 열리지 않은 칸이면 스킵
                if(!isOpened(recordedMap, i, j))
                    continue;
                else if(isBomb(gameMap, i, j)) {
                    fail = true;
                    continue;
                }
                recordedMap[i][j] = (char) (ZERO + getAroundBombCnt(n, gameMap, i, j));
            }
        }

        if(fail)
            gameFailed(n, gameMap, recordedMap);

    }

    // r,c칸 주위에 있는 지뢰 개수 구하기
    static int getAroundBombCnt(int n, char[][] gameMap, int r, int c) {
        int cnt = 0;
        int nr, nc;
        for (int i = 0; i < 8; i++) {
            nr = r + dr[i];
            nc = c + dc[i];
            if(isIn(nr, nc, n) && isBomb(gameMap, nr, nc)) cnt++;
        }
        return cnt;
    }

    // 칸이 맵 안에 존재하는지 확인
    static boolean isIn(int r, int c, int n) {
        return r >= 0 && c >= 0 && r < n && c < n;
    }

    // 지뢰 칸이 열렸는지 확인
    static boolean isBomb(char[][] gameMap, int r, int c) {
        return gameMap[r][c] == BOMB;
    }

    // 열리지 않은 칸인지 확인
    static boolean isOpened(char[][] recordedMap, int r, int c) {
        return recordedMap[r][c] != BASE;
    }

    // 지뢰 칸을 열어 게임 실패
    // 지뢰 칸은 * 아닌 칸은 . 표시
    static void gameFailed(int n, char[][] gameMap, char[][] recordedMap) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                // 이미 열린 칸인 경우 생략
                if(recordedMap[i][j] >= ZERO && recordedMap[i][j] <= NINE)
                    continue;
                recordedMap[i][j] = (gameMap[i][j] == BOMB) ? BOMB : BASE;
            }
        }
    }



}