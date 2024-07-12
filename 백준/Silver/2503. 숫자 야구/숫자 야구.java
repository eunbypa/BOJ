import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[] minHyukNums;
    static int[] strikes;
    static int[] balls;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        minHyukNums = new int[N];
        strikes = new int[N];
        balls = new int[N];

        int minhyukNum, strike, ball;
        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            minhyukNum = Integer.parseInt(st.nextToken());
            strike = Integer.parseInt(st.nextToken());
            ball = Integer.parseInt(st.nextToken());
            minHyukNums[i] = minhyukNum;
            strikes[i] = strike;
            balls[i] = ball;
        }

        int cnt = getPossibleAnswerCnt();
        System.out.println(cnt);
    }

    // 브루트 포스로 100 ~ 999까지 모든 경우의 수에 대하여 답이 될 가능성이 있는 숫자 찾기
    public static int getPossibleAnswerCnt() {
        int cnt = 0;
        boolean pos = true;
        for (int i = 100; i <= 999; i++) {
            if(hasZero(i) || !hasDiffNum(i)) continue;
            pos = true;
            for (int j = 0; j < N; j++) {
                if(!check(i, j)) {
                    pos = false;
                    break;
                }
            }
            if(pos) cnt++;
        }

        return cnt;
    }

    // 0이 포함된 숫자인지 확인
    public static boolean hasZero(int num) {
        for (int i = 0; i < 3; i++) {
            if(num % 10 == 0) return true;
            num /= 10;
        }
        return false;
    }

    // 서로 다른 숫자로 구성된 숫자인지 확인
    public static boolean hasDiffNum(int num) {
        int[] numCnt = new int[10];
        for (int i = 0; i < 3; i++) {
            numCnt[num % 10]++;
            num /= 10;
        }
        for (int i = 1; i < 10; i++) {
            if(numCnt[i] > 1) return false;
        }
        return true;
    }

    // 정답이 될 조건에 부합하는지 확인
    public static boolean check(int num, int idx) {
        // 스트라이크 개수 확인
        int strike = 0;
        // 볼 개수 확인
        int ball = 0;
        int a = num, b = minHyukNums[idx], c;
        for (int i = 0; i < 3; i++) {
            if(a % 10 == b % 10) strike++;
            else {
                c = minHyukNums[idx];
                for (int j = 0; j < 3; j++) {
                    if(a % 10 == c % 10) ball++;
                    c /= 10;
                }
            }
            a /= 10;
            b /= 10;
        }
        return strike == strikes[idx] && ball == balls[idx];
    }

}