// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.StringTokenizer;

public class Main {
    static int N, M, K;
    static int mod = 1000000007;
    static long[] segTree; // 세그먼트 트리
    static int size;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        int height = 0;
        int tmp = N;
        while(tmp > 0) {
            tmp /= 2;
            height++;
        }
        size = (1 << (height+1));
        segTree = new long[size+1];
        long[] arr = new long[N+1];
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        init(arr, 1, 1, N);
        int tot = M + K;
        int a, b, c;
        for (int i = 0; i < tot; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());
            // 변경
            if(a == 1) update(1, b, arr[b], c, 1, N);
            // 곱 구하기
            if(a == 2) sb.append(mul(1, b, c, 1, N)+"\n");
        }
        System.out.println(sb.toString());
    }

    // 세그먼트 트리 초기화
    public static long init(long[] arr, int idx, int s, int e) {
        if(s == e){
            return segTree[idx] = arr[s];
        }
        int m = (s + e) / 2;
        return segTree[idx] = (init(arr, idx * 2, s, m) * init(arr, idx * 2 + 1, m + 1, e)) % mod;
    }

    // 구간 곱 구하기
    public static long mul(int idx, int l, int r, int s, int e) {
        // l, r이 s, e와 전혀 겹치지 않을 때
        if(r < s || e < l) {
            return 1;
        }
        // 완전히 포함될 때
        if(l <= s && e <= r){
            return segTree[idx] % mod;
        }
        // 부분 겹칠 때
        int m = (s + e) / 2;
        return (mul(idx*2, l, r, s, m) * mul(idx*2+1, l, r, m+1, e)) % mod;

    }

    // 세그먼트 트리 업데이트
    public static long update(int idx, int idx2, long oldV, long newV, int s, int e) {
        if(idx2 < s || idx2 > e) return segTree[idx];
        if(s == e) {
            return segTree[idx] = newV;
        }
        int m = (s + e) / 2;
        return segTree[idx] = (update(idx*2, idx2, oldV, newV, s, m) * update(idx*2+1, idx2, oldV, newV, m+1, e)) % mod;
    }

}