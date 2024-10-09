import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static final int MAX_VALUE = 1000000000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] jewelCntArr = new int[M];
        int cnt;
        for (int i = 0; i < M; i++) {
            cnt = Integer.parseInt(br.readLine());
            jewelCntArr[i] = cnt;
        }
        System.out.println(getAnswer(N, M, jewelCntArr));
    }

    // 이분탐색
    static int getAnswer(int N, int M, int[] jewelCntArr) {
        int s = 1, e = MAX_VALUE;
        int m;

        while(s <= e) {
            m = (s + e) / 2;
            if(isSuccess(m, N, M, jewelCntArr)) {
                e = m-1;
            }else {
                s = m+1;
            }
        }

        return s;
    }


    // 보석 배분 성공 여부 반환
    static boolean isSuccess(int jewelCnt, int N, int M, int[] jewelCntArr) {
        long num = 0L;

        for (int i = 0; i < M; i++) {
            num += (jewelCntArr[i] % jewelCnt == 0) ? (jewelCntArr[i] / jewelCnt) : (jewelCntArr[i] / jewelCnt)+1;
            if(num > N) // 배분에 필요한 사람 수가 N명을 넘으면 실패
                return false;
        }

        return true;
    }


}