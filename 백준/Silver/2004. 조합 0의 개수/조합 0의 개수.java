import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int twoCnt, fiveCnt;
        twoCnt = countTwo(N) - countTwo(N - M) - countTwo(M);
        fiveCnt = countFive(N) - countFive(N - M) - countFive(M);
        System.out.println(Math.min(twoCnt, fiveCnt));
    }

    // 2의 개수 구하기
    public static int countTwo(int n) {
        int cnt = 0;
        while(n > 0) {
            cnt += n/5;
            n /= 5;
        }
        return cnt;
    }

    // 5의 개수 구하기
    public static int countFive(int n) {
        int cnt = 0;
        while(n > 0) {
            cnt += n/2;
            n /= 2;
        }
        return cnt;
    }


}