import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st;
        int[] A,B,C,D;
        A = new int[n];
        B = new int[n];
        C = new int[n];
        D = new int[n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            A[i] = Integer.parseInt(st.nextToken());
            B[i] = Integer.parseInt(st.nextToken());
            C[i] = Integer.parseInt(st.nextToken());
            D[i] = Integer.parseInt(st.nextToken());
        }
        int[] AB = new int[n*n];
        int[] CD = new int[n*n];
        // A+B 경우의수 탐색
        int idx = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                AB[idx++] = A[i] + B[j];
            }
        }
        idx = 0;
        // C+D 경우의수 탐색
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                CD[idx++] = C[i] + D[j];
            }
        }
        // 오름차순 정렬
        Arrays.sort(AB);
        Arrays.sort(CD);

        // 투포인터
        int s = 0, e = n*n-1;
        int tot = n*n;
        long cnt = 0L, sCnt = 0L, eCnt = 0L;
        while(s < tot && e >= 0) {
            if(AB[s] + CD[e] == 0) {
                int ab = AB[s], cd = CD[e];
                sCnt = 1L;
                while(s+1 < tot && AB[s+1] == ab) {
                    s++;
                    sCnt++;
                }
                eCnt = 1L;
                while(e-1 >= 0 && CD[e-1] == cd) {
                    e--;
                    eCnt++;
                }
                cnt += sCnt * eCnt;
                s++;
            }
            else if(AB[s] + CD[e] > 0) {
                e--;
            }
            else if(AB[s] + CD[e] < 0) {
                s++;
            }
        }

        System.out.println(cnt);
    }

}