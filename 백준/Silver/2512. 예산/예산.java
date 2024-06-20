import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

    static final int MAX_VALUE = 100000;
    static int N, M;
    static int[] budget;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        budget = new int[N];
        int tot = 0, maxB = 0;
        for (int i = 0; i < N; i++) {
            budget[i] = Integer.parseInt(st.nextToken());
            maxB = Math.max(maxB, budget[i]);
            tot += budget[i];
        }
        M = Integer.parseInt(br.readLine());

        int ans = 0;
        if(tot <= M) // 총 예산이 각 지방 전체 요청 예산 합보다 큰 경우
            ans = maxB;
        else
            ans = findMaxLimitBudget();
        System.out.println(ans);
    }

    // 이분 탐색
    public static int findMaxLimitBudget() {
        int s = 1, m, e = MAX_VALUE;
        while(s <= e) {
            m = (s + e) / 2;
            if(isSuccess(m)) {
                s = m + 1;
            }else {
                e = m - 1;
            }
        }
        return s-1;
    }

    public static boolean isSuccess(int limit) {
        int sum = 0, tmp;
        for (int i = 0; i < N; i++) {
            tmp = Math.min(budget[i], limit);
            sum += tmp;
        }
        return sum <= M;
    }

}