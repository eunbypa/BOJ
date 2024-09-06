import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int S = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        int[] greenOnionLenArr = new int[S];
        for (int i = 0; i < S; i++) {
            greenOnionLenArr[i] = Integer.parseInt(br.readLine());
        }


        System.out.println(getRemainedGreenOnionAmount(S, C, greenOnionLenArr));
    }

    // 라면에 넣을 남은 파의 양 구하기
    static long getRemainedGreenOnionAmount(int S, int C, int[] greenOnionLenArr) {
        int greenOnionInChicken = getMaxGreenOnionInChicken(S, C, greenOnionLenArr);
        long remGreenOnion = 0L, greenOnionSum = 0L;
        for (int i = 0; i < S; i++) {
            greenOnionSum += greenOnionLenArr[i];
        }
        remGreenOnion = greenOnionSum - (greenOnionInChicken * (long) C);

        return remGreenOnion;
    }


    // 파닭에 넣을 파의 최대 양 구하기
    // 이분탐색
    static int getMaxGreenOnionInChicken(int S, int C, int[] greenOnionLenArr) {
        int maxGreenOnionLen = 0;
        for (int i = 0; i < S; i++) {
            maxGreenOnionLen = Math.max(maxGreenOnionLen, greenOnionLenArr[i]);
        }
        int s = 1, e = maxGreenOnionLen, m;
        int maxGreenOnion = 0;
        while(s <= e) {
            m = (s + e) / 2;
            if(isPossible(C, m, greenOnionLenArr)) {
                s = m+1;
                maxGreenOnion = m;
            }else {
                e = m-1;
            }
        }

        return maxGreenOnion;
    }


    // 해당 양으로 파닭을 모두 만들 수 있는지 확인
    static boolean isPossible(int C, int greenOnion, int[] greenOnionLenArr) {
        int cnt = 0, l = greenOnionLenArr.length;
        for (int i = 0; i < l; i++) {
            cnt += greenOnionLenArr[i] / greenOnion;
        }
        return cnt >= C;
    }

}