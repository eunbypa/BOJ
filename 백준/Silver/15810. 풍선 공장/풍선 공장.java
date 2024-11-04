import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static final long MAX_VAL = 1000000000000L;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] timeArr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            timeArr[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(getAnswer(timeArr, N, M));
    }

    // 이분탐색 활용
    static long getAnswer(int[] timeArr, int N, int M) {
        long s = 1L, m, e = MAX_VAL;

        while(s < e) {
            m = (s + e) / 2;
            if(isSuccess(m, timeArr, N, M)) {
                e = m;
            }else {
                s = m+1;
            }
        }

        return s;
    }

    static boolean isSuccess(long m, int[] timeArr, int N, int M) {
        long rem = M;
        for (int i = 0; i < N; i++) {
            rem -= m / timeArr[i];
            if(rem <= 0L) return true;
        }

        return false;
    }


}