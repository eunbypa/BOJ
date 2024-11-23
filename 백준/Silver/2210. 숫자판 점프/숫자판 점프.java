import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static Map<String, Integer> numMap = new HashMap<>();
    static int numCnt = 0;

    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, -1, 0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int[][] map = new int[5][5];
        for (int i = 0; i < 5; i++) {
        st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 5; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int[] nums = new int[6];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                nums[0] = map[i][j];
                dfs(i, j, 1, nums, map);
            }
        }

        System.out.println(numCnt);
    }

    static void dfs(int r, int c, int cnt, int[] nums, int[][] map) {
        if(cnt == 6) {
            String num = getNum(nums);
            if(!numMap.containsKey(num)) {
                numMap.put(num, 1);
                numCnt++;
            }
            return;
        }
        int nr, nc;
        for (int i = 0; i < 4; i++) {
            nr = r + dr[i];
            nc = c + dc[i];
            if(nr < 0 || nc < 0 || nr >= 5 || nc >= 5) continue;
            nums[cnt] = map[nr][nc];
            dfs(nr, nc, cnt+1, nums, map);
        }

    }

    static String getNum(int[] nums) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            sb.append(nums[i]);
        }
        return sb.toString();
    }


}