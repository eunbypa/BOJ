import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n,m;


    static int[] arr;
    static long[] segTree;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        init();

        StringBuilder sb = new StringBuilder();
        int func, a, b;
        long ans;
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            func = Integer.parseInt(st.nextToken());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());

            switch (func) {
                case 0:
                    // 합 구하기
                    ans = (a < b) ? sum(a, b, 1, n, 1) : sum(b, a, 1, n, 1);
                    sb.append(ans).append("\n");
                    break;
                case 1:
                    // 값 수정
                    update(b-arr[a], 1, n, a, 1);
                    break;
            }

        }

        System.out.println(sb.toString());
    }

    // 세그먼트 트리
    static void init() {
        int size = n << 2;
        segTree = new long[size];
        arr = new int[n+1];
    }

    static long sum(int l, int r, int s, int e, int idx) {
        if(l > r || r < l)
            return 0L;

        // 세그 구간이 현재 구하려는 구간에 아예 속하지 않는 경우
        if(l > e || r < s)
            return 0L;

        // 완전히 속하는 경우
        if(l <= s && e <= r)
            return segTree[idx];

        // 일부 속하는 경우
        int m = (s + e) / 2;
        return sum(l, r, s, m, idx*2) + sum(l, r,m+1, e, idx*2+1);
    }

    static void update(int diff, int s, int e, int idx, int idx2) {
        // 변경하려는 지점을 포함하는 구간이 아님
        if(idx < s || e < idx) return;

        // 리프노드
        if(s == e) {
            if(s == idx) {
                arr[idx] += diff;
                segTree[idx2] += diff;
            }
            return;
        }

        // 일부 포함
        segTree[idx2] += diff;
        int m = (s + e) / 2;
        update(diff, s, m, idx, idx2*2);
        update(diff, m+1, e, idx, idx2*2+1);
    }

}