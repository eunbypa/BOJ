import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static final int MAX_LOC_VALUE = 100000;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] locCntArr = new int[MAX_LOC_VALUE+1]; // 해당 위치에 집이 몇개 있는지 표시 배열
        long locSum = 0L;
        int curLoc, maxLoc = 0;
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            curLoc = Integer.parseInt(st.nextToken());
            maxLoc = Math.max(maxLoc, curLoc);
            locSum += curLoc;
            locCntArr[curLoc]++;
        }

        System.out.println(getAnswer(locSum, N, maxLoc, locCntArr));
    }


    // 안테나와 집 사이 거리의 합이 최소가 되게 하는 법 --> 평균과 제일 가깝게?
    static int getAnswer(long locSum, int N, int maxLoc, int[] locCntArr) {
        int answer = maxLoc;
        // 안테나 위치 기준으로 왼쪽, 안테나 설치 장소, 오른쪽 집의 개수
        int[] lrCntArr = getLowerAndBiggerCnt(maxLoc, locCntArr);
        int lCnt = lrCntArr[0], rCnt = lrCntArr[1];
        int mCnt = locCntArr[maxLoc]; // 안테나 설치 장소 집의 개수
        long minSum = getDiffSum(maxLoc, locCntArr);
        long curSum = minSum;
        for (int i = maxLoc-1; i >= 1; i--) {
            curSum = curSum - lCnt + mCnt + rCnt;
            lCnt -= locCntArr[i];
            rCnt += mCnt;
            mCnt = locCntArr[i];
            if(locCntArr[i] != 0) {
                // 해당 위치에 안테나 설치한다고 가정
                if (curSum < minSum){
                    answer = i;
                    minSum = curSum;
                }else if (curSum == minSum) {
                    answer = Math.min(answer, i);
                }
            }
        }

        return answer;
    }

    // 해당 값을 기준으로 차이 합 반환
    static long getDiffSum(int n, int[] arr) {
        long sum = 0L;
        int l = arr.length;
        for (int i = 0; i < l; i++) {
            sum += (long) Math.abs(n - i) * arr[i];
        }
        return sum;
    }

    // n 기준으로 작은 수 및 큰 수의 개수 구해서 배열 형태로 반환
    // 0 : 작은 수 개수, 1 : 큰 수 개수
    static int[] getLowerAndBiggerCnt(int n, int[] arr) {
        int lCnt = 0, rCnt = 0;
        int l = n, r = n;
        int len = arr.length;
        while(--l >= 0) {
            lCnt += arr[l];
        }
        while(++r < len) {
            rCnt += arr[r];
        }

        return new int[]{lCnt, rCnt};
    }


}