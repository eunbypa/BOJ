import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static final int MAX_VALUE = 100000;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] stones = new int[N+1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            stones[i] = Integer.parseInt(st.nextToken());
        }
        int a, b;
        st = new StringTokenizer(br.readLine());
        a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());

        
        System.out.println(getAnswer(N, stones, a, b));
    }
    
    //dp
    static int getAnswer(int N, int[] stones, int a, int b) {
        Queue<Integer> q = new LinkedList<>();
        int[] dp = new int[N+1];
        int cur;
        int l, r;
        init(dp);
        dp[a] = 0;
        q.offer(a);
        while(!q.isEmpty()) {
            cur = q.poll();
            if(cur == b) continue;
            l = cur;
            r = cur;
            while(l >= 1 || r <= N) {
                l -= stones[cur];
                r += stones[cur];
                if(l >= 1 && dp[l] > dp[cur]+1) {
                    dp[l] = dp[cur] + 1;
                    q.offer(l);
                };
                if(r <= N && dp[r] > dp[cur]+1) {
                    dp[r] = dp[cur] + 1;
                    q.offer(r);
                };
            }
        }


        return (dp[b] == MAX_VALUE) ? -1 : dp[b];
    }



    static void init(int[] arr) {
        int l = arr.length;
        for (int i = 0; i < l; i++) {
            arr[i] = MAX_VALUE;
        }
    }

}