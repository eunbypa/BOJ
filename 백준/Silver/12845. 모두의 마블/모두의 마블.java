import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] L = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        int maxLevelIdx = 0, maxLevel = 0;
        for (int i = 0; i < n; i++) {
            L[i] = Integer.parseInt(st.nextToken());
            if(maxLevel < L[i]) {
                maxLevelIdx = i;
                maxLevel = L[i];
            }
        }

        System.out.println(getMaxGold(n, L, maxLevelIdx, maxLevel));
    }

    // 골드 최댓값 구하기
    // 가장 높은 레벨의 카드만 남겨두는 방식
    static long getMaxGold(int n, int[] cardLevelArr, int maxLevelIdx, int maxLevel) {
        long goldSum = 0L;
        int l = maxLevelIdx - 1, r = maxLevelIdx + 1;
        while(l >= 0 || r < n) {
            if(l >= 0) {
                goldSum += maxLevel + cardLevelArr[l];
                l--;
            }
            if(r < n) {
                goldSum += maxLevel + cardLevelArr[r];
                r++;
            }
        }

        return goldSum;
    }

}