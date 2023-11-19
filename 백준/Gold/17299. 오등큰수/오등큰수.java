import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[] nums;
    static int[] cnt;
    static int[] ans;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        nums = new int[N];
        cnt = new int[1000001];
        ans = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
            cnt[nums[i]]++;
            ans[i] = -1;
        }
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < N; i++) {
            while(!stack.isEmpty() && cnt[nums[stack.peek()]] < cnt[nums[i]]) {
                ans[stack.pop()] = nums[i];
            }
            stack.add(i);
        }
        for (int i = 0; i < N; i++) {
            sb.append(ans[i]);
            if(i == N-1) break;
            sb.append(" ");
        }
        System.out.println(sb.toString());
    }

}