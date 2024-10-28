import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    
    static final int MAX_CNT = 3;
    
    static int maxSum;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[][] preferenceValArr = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                preferenceValArr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        maxSum = 0;
        comb(N, M, preferenceValArr, 0, 0, new int[MAX_CNT]);
        System.out.println(maxSum);
    }

    static void comb(int N, int M, int[][] preferenceValArr, int cur, int cnt, int[] selected) {
        if(cnt == MAX_CNT) {
            maxSum = Math.max(maxSum, getPreferenceSum(N, M, preferenceValArr, selected));
            return;
        }

        for (int i = cur; i < M; i++) {
            selected[cnt] = i;
            comb(N, M, preferenceValArr, i+1, cnt+1, selected);
        }
    }
    
    static int getPreferenceSum(int N, int M, int[][] preferenceValArr, int[] selected) {
        int sum = 0;
        int maxP;
        
        for (int i = 0; i < N; i++) {
            maxP = 0;
            for (int j = 0; j < MAX_CNT; j++) {
                maxP = Math.max(maxP, preferenceValArr[i][selected[j]]);
            }
            sum += maxP;
        }

        return sum;
    }

}