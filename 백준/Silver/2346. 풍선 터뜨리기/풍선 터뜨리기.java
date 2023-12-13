import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[] visited;
    static int[] nums;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        nums = new int[N + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }
        visited = new int[N + 1];
        int cnt = 0;
        int cur = 1, tmp;
        int d = 0;
        while(cnt != N) {
            sb.append(cur);
            visited[cur] = 1;
            cnt++;
            if(cnt == N) break;
            sb.append(" ");
            d = (nums[cur] > 0) ? 1 : -1;
            tmp = cur + d;
            while(nums[cur] != 0) {
                if(tmp > N) tmp = 1;
                else if(tmp < 1) tmp = N;
                if(visited[tmp] == 0) {
                    nums[cur] -= d;
                }
                if(nums[cur] == 0) {
                    cur = tmp;
                    break;
                }
                tmp += d;
            }
        }
        System.out.println(sb.toString());
    }



}