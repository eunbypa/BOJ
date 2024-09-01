import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[] expArr = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            expArr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(expArr);
        System.out.println(getMaxExpSum(n, k, expArr));
    }

    // 획득 경험치량이 제일 작은 퀘스트부터 수행
    static long getMaxExpSum(int n, int k, int[] expArr) {
        long sum = 0L; // 축적된 경험치 총합
        int activeStoneCnt = 0; // 활성화된 아케인 스톤 개수

        // k개를 활성화할 때까지 축적
        for (int i = 0; i < k; i++) {
            sum += (expArr[i] * (long)activeStoneCnt);
            activeStoneCnt++;
        }
        // k개 활성화 한 이후
        for (int i = k; i < n; i++) {
            sum += (expArr[i] * (long)activeStoneCnt);
        }

        return sum;
    }

}