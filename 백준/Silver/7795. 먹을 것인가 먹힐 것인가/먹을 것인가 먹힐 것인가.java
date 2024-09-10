import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int N, M;
        int[] A, B;
        for (int t = 0; t < T; t++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            A = new int[N];
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                A[j] = Integer.parseInt(st.nextToken());
            }

            B = new int[M];
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                B[j] = Integer.parseInt(st.nextToken());
            }

            Arrays.sort(B);

            sb.append(getAGreaterThanBCnt(N, A, M, B)).append("\n");

        }
        System.out.println(sb.toString());
    }

    // A가 B보다 큰 쌍의 개수 구하기
    // 이분탐색 lower bound 활용
    static int getAGreaterThanBCnt(int N, int[] A, int M, int[] B) {
        int tot = 0;
        int cnt;
        for (int i = 0; i < N; i++) {
            // A[i]가 B 배열의 가장 큰값보다 큰 경우 lower bound 생략
            cnt = (A[i] > B[M-1]) ? M : lowerBound(A[i], B);
            tot += cnt;
        }

        return tot;
    }

    // lower bound 알고리즘
    // arr 배열에서 n보다 크거나 같은 숫자가 나오는 처음 index 반환
    static int lowerBound(int n, int[] arr) {
        int idx = 0;
        int s = 0, e = arr.length - 1, m;
        while(s <= e) {
            m = (s + e)/2;
            if(arr[m] >= n) {
                e = m-1;
                idx = m;
            }else {
                s = m + 1;
            }
        }

        return idx;
    }

}