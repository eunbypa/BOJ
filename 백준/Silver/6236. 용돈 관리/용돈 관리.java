import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static final int MAX_VALUE = 2000000000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] moneyArr = new int[N];
        for (int i = 0; i < N; i++) {
            moneyArr[i] = Integer.parseInt(br.readLine());
        }

        System.out.println(getAnswer(N, M, moneyArr));
    }


    // 이분탐색
    static int getAnswer(int N, int M, int[] moneyArr) {
        int s = 1, m, e = MAX_VALUE;
        int ans = 0;
        while(s <= e) {
            m = (s + e) / 2;
            if(isSuccess(m, N, M, moneyArr)) {
                e = m - 1;
                ans = m;
            }else {
                s = m + 1;
            }
        }

        return ans;
    }

    static boolean isSuccess(int withdrawlAmt, int N, int M, int[] moneyArr) {
        int rem = 0;
        for (int i = 0; i < N; i++) {
            if(rem < moneyArr[i]) {
                if(M == 0 || withdrawlAmt < moneyArr[i]) return false;
                rem = withdrawlAmt;
                M--;
            }
            rem -= moneyArr[i];
        }

        return true;
    }


}