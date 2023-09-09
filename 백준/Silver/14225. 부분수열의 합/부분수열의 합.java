import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[] S;
    static int[] cnt;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        S = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            S[i] = Integer.parseInt(st.nextToken());
        }
        int min = 0;
        cnt = new int[2000001];
        subset(0,0);
        for (int i = 1; i < 2000001; i++) {
            if(cnt[i] == 0){
                min = i;
                break;
            }
        }
        System.out.println(min);
    }

    // 부분 수열의 합 모두 구하기
    public static void subset(int cur, int sum) {
        if(cur == N) {
            cnt[sum] = 1;
            return;
        }
        // 선택 하거나
        subset(cur+1, sum+S[cur]);
        // 안 하거나
        subset(cur+1, sum);
    }

}