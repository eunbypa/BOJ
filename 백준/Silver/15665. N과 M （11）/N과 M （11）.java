import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N,M;
    static int[] nums;
    static StringBuilder sb;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        nums = new int[N];
        st = new StringTokenizer(br.readLine());
        int max = 0;
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
            max = Math.max(max, nums[i]);
        }
        Arrays.sort(nums);
        sb = new StringBuilder();
        dfs(0, new int[M]);
        System.out.println(sb.toString());
    }

    public static void dfs(int cnt, int[] arr) {
        if(cnt == M) {
            for (int i = 0; i < M; i++) {
                sb.append(arr[i]);
                if(i == M-1) break;
                sb.append(" ");
            }
            sb.append("\n");
            return;
        }

        int b = 0, a = 0;
        for (int i = 0; i < N; i++) {
            a = nums[i];
            if(b != 0 && b == a) continue;
            b = nums[i];
            arr[cnt] = nums[i];
            // cnt 번째에서 이미 같은 숫자 탐색 완료한 경우 생략(중복 방지)
            dfs(cnt + 1, arr);
        }
    }

}