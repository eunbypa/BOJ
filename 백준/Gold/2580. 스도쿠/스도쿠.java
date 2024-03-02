import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int[][] sudoku;
    static int[][][] arr;
    static List<int[]> list;
    static boolean find;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sudoku = new int[9][9];
        arr = new int[3][9][10]; // 0 : 행, 1 : 열, 2 : 3*3
        list = new ArrayList<>();
        StringTokenizer st;
        for (int i = 0; i < 9; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 9; j++) {
                sudoku[i][j] = Integer.parseInt(st.nextToken());
                if(sudoku[i][j] == 0) list.add(new int[]{i, j});
                else {
                    arr[0][i][sudoku[i][j]] = 1;
                    arr[1][j][sudoku[i][j]] = 1;
                    arr[2][(i/3)*3 + (j/3)][sudoku[i][j]] = 1;
                }
            }
        }
        dfs(0);
        System.out.println(sb.toString());
    }

    // 백트래킹
    private static void dfs(int cnt) {
        if(find) return;
        if(cnt == list.size()) {
            // 정답
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    sb.append(sudoku[i][j]);
                    if(j == 8) break;
                    sb.append(" ");
                }
                sb.append("\n");
            }
            find = true;
            return;
        }
        int[] cur = list.get(cnt);
        for (int i = 1; i <= 9; i++) {
            sudoku[cur[0]][cur[1]] = i;
            arr[0][cur[0]][i]++;
            arr[1][cur[1]][i]++;
            arr[2][(cur[0]/3)*3 + (cur[1]/3)][i]++;
            if(check(cur[0], cur[1], i)) {
                dfs(cnt+1);
            }
            sudoku[cur[0]][cur[1]] = 0;
            arr[0][cur[0]][i]--;
            arr[1][cur[1]][i]--;
            arr[2][(cur[0]/3)*3 + (cur[1]/3)][i]--;
        }
    }

    // 수도쿠 규칙에 맞는 숫자인지 확인
    private static boolean check(int r, int c, int n) {
        // 행 검사
        // 열 검사
        // 3*3 검사
        return arr[0][r][n] == 1 && arr[1][c][n] == 1 && arr[2][(r/3)*3 + (c/3)][n] == 1;
    }
}