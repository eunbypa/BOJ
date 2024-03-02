import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int[][] sudoku;
    static List<int[]> list;
    static boolean find;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sudoku = new int[9][9];
        list = new ArrayList<>();
        StringTokenizer st;
        for (int i = 0; i < 9; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 9; j++) {
                sudoku[i][j] = Integer.parseInt(st.nextToken());
                if(sudoku[i][j] == 0) list.add(new int[]{i, j});
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
            if(check(cur[0], cur[1])) {
                dfs(cnt+1);
            }
            sudoku[cur[0]][cur[1]] = 0;
        }
    }

    // 수도쿠 규칙에 맞는 숫자인지 확인
    private static boolean check(int r, int c) {
        int[] arr = new int[10];
        // 행 검사
        for (int i = 0; i < 9; i++) {
            arr[sudoku[r][i]]++;
            if(sudoku[r][i] != 0 && arr[sudoku[r][i]] == 2) return false;
        }
        // 열 검사
        arr = new int[10];
        for (int i = 0; i < 9; i++) {
            arr[sudoku[i][c]]++;
            if(sudoku[i][c] != 0 && arr[sudoku[i][c]] == 2) return false;
        }
        // 3*3 검사
        arr = new int[10];
        int sr = (r / 3) * 3;
        int sc = (c / 3) * 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                arr[sudoku[sr+i][sc+j]]++;
                if(sudoku[sr+i][sc+j] != 0 && arr[sudoku[sr+i][sc+j]] == 2) return false;
            }
        }
        return true;
    }
}